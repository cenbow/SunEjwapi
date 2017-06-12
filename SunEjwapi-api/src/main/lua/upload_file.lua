local resty_sha1 = require "resty.sha1"
local upload = require "resty.upload"
local cjson = require "cjson"
--local cform = require "resty.cform"

local chunk_size = 4096
local form = upload:new(chunk_size)

if not form then
    ngx.log(ngx.ERR, "failed to new upload: ", err)
    ngx.exit(ngx.HTTP_INTERNAL_SERVER_ERROR)
end


local sha1 = resty_sha1:new()
local file
local upload_path = "/alidata/server/tomcat-01/upload/"

function file_exists(path)
  local file = io.open(path, "rb")
  if file then file:close() end
  return file ~= nil
end

function getFileName(str)
    local idx = str:match(".+()%.%w+$")
    if(idx) then
        return str:sub(1, idx-1)
    else
        return str
    end
end

function split(str, delimiter)
	if str==nil or str=='' or delimiter==nil then
		return nil
	end
	
    local result = {}
    for match in (str..delimiter):gmatch("(.-)"..delimiter) do
        table.insert(result, match)
    end
    return result
end

function trim(s)
    return (s:gsub("^%s*(.-)%s*$", "%1"))
end

--获取扩展名
function getExtension(str)
    return str:match(".+%.(%w+)$")
end

local retTable = {}
retTable["code"] = 0
retTable["msg"] = ""

local file_path
if string.find(ngx.var.uri, "image.do") then
    file_path = "images/"
elseif string.find(ngx.var.uri, "images.do") then
    file_path = "images/"
    retTable["data"] = {}
elseif string.find(ngx.var.uri, "file.do") then
    file_path = "files/"
elseif string.find(ngx.var.uri, "files.do") then
    file_path = "files/"
    retTable["data"] = {}
end

local today = os.date("%Y-%m-%d", os.time())

if not file_exists(upload_path..file_path..today) then
    os.execute("mkdir "..upload_path..file_path..today)
end



while true do
    local typ, res, err = form:read()
    local fp = file_path..today.."/"
    --ngx.log(ngx.INFO, "header data: ", cjson.encode({typ, res}))
    if not typ then
         ngx.say(cjson.encode([[{"code": 20501, "msg": "请求参数不合法,文件上传失败！"}]]))
         return
    end

    if typ == "header" then
        local key = res[1]
        local value = res[2]
        --ngx.log(ngx.INFO, "header data: ", key, "  -- ", value)
        if key == "Content-Disposition" then
            local kvlist = split(value, ";")
            for _, kv in ipairs(kvlist) do
                local seg = trim(kv)
                if seg:find("filename") then
                    local kvfile = split(seg, "=")
                    local filename = string.sub(kvfile[2], 2, -2)
                    if not filename then
                        ngx.say(cjson.encode([[{"code": 20502, "msg": "文件格式错误,文件上传失败！"}]]))
                        return
                    end
                    ngx.log(ngx.INFO, "filename in form-data: ", filename)
                    fp = fp..getFileName(filename).."_"..os.time().."."..getExtension(filename)
                    ngx.log(ngx.INFO, "real file path: ", upload_path..fp)
                    file = io.open(upload_path..fp, "w+")
                    if not file then
                        ngx.say(cjson.encode([[{"code": 20503, "msg": "无法创建文件，请联系管理员，谢谢"}]]))
                        return
                    else
                        if retTable["data"] then
                            --local len = table.getn(retTable["data"])
                            if type(retTable["data"]) == "string" then
                                ngx.say(cjson.encode([[{"code": 20504, "msg": "单文件接口无法上传多个文件，请使用其他接口，谢谢"}]]))
                                return
                            end
                            local result = {}
                            result[getFileName(filename)] = fp
                            table.insert(retTable["data"], result)
                        else
                            retTable["data"] = fp
                        end
                        break
                    end
                end
            end
        end
    elseif typ == "body" then
        if file then
            file:write(res)
            sha1:update(res)
        end

    elseif typ == "part_end" then
        if file then
            file:close()
            file = nil
            local sha1_sum = sha1:final()
            sha1:reset()
        end

    elseif typ == "eof" then
        break

    else
        -- do nothing
    end
end


ngx.say(cjson.encode(retTable))
