package com.huaao.sunejwapi.api.dto.pub;

import java.io.Serializable;

import com.huaao.sunejwapi.common.Constants;
import com.huaao.sunejwapi.common.DemoData;
import com.huaao.sunejwapi.common.util.FuncTool;
import com.huaao.sunejwapi.common.web.ApiDemoData;
import com.huaao.sunejwapi.common.web.ApiDescription;

import net.sf.json.JSONObject;

public class DtoPublicResponse implements Serializable{

	private static final long serialVersionUID = -3783199459021802877L;

	@ApiDescription("错误描述")
	@ApiDemoData("操作成功")
	private String msg = "";
	
	//错误码
	@ApiDescription("错误代码")
	@ApiDemoData("A00000")
	private String code = Constants.API_CODE_SUCCESS;
	
	@ApiDescription("请求序列，每次请求会产生唯一编号,接口错误时请记录该编号")
	@ApiDemoData("RQ"+DemoData.ID)
	private String reqId=null;
	
	public String getMsg() {
		return msg;
	}
	public DtoPublicResponse setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	public DtoPublicResponse addMsg(String msg){
		this.msg += msg;
		return this;
	}
	
	public String getCode() {
		return code;
	}
	public DtoPublicResponse setCode(String code) {
		this.code = code;
		return this;
	}
	
	public static DtoPublicResponse init(){
		return new DtoPublicResponse();
	}
	public static DtoPublicResponse init(String message){
		DtoPublicResponse dr = new DtoPublicResponse();
		dr.setMsg(message);
		return dr;
	}
	
	public static DtoPublicResponse fail(String message){
	    return fail(Constants.API_CODE_SERVER_ERROR, message);
	}
	
	public static DtoPublicResponse fail(String code, String message){	
		DtoPublicResponse dr = new DtoPublicResponse();
		dr.setCode(code);
		dr.setMsg(message);
		return dr;
	}
	
	public void setFail(String message){
		this.setCode(Constants.API_CODE_SERVER_ERROR);
		this.setMsg(message);
	}
	public String toJson(){
		JSONObject jo = JSONObject.fromObject(this);
		return jo.toString();
	}
	
	public void initDemoData(Class genClass){
		FuncTool.createDemoData(this.getClass(), genClass ,this,false); 
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	
	
}
