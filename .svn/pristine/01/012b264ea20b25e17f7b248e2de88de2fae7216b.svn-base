package com.huaao.sunejwapi.api.control;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaao.sunejwapi.api.dto.bean.DictInfo;
import com.huaao.sunejwapi.api.dto.pub.DtoPublicResponseList;
import com.huaao.sunejwapi.api.dto.pub.DtoQueryDictRequest;
import com.huaao.sunejwapi.api.dto.pub.DtoQueryDictResponse;
import com.huaao.sunejwapi.common.util.FuncTool;
import com.huaao.sunejwapi.common.web.ApiDescription;
import com.huaao.sunejwapi.common.web.SystemException;
import com.huaao.sunejwapi.dao.SpsSDictionary;
import com.huaao.sunejwapi.service.DictService;

@ApiDescription("数据字典")
@Controller
@RequestMapping("apis-sun/dict")
public class DictController {

	@Autowired
	private DictService dictService;
	
	@ApiDescription("数据字典查询;负责人:yuhai")
	@RequestMapping("queryByParent")
	@ResponseBody
	public DtoPublicResponseList<DtoQueryDictResponse> queryByParent(@Valid DtoQueryDictRequest dtoRequest) throws Exception {
		DtoPublicResponseList<DtoQueryDictResponse> list = new DtoPublicResponseList<>();
		SpsSDictionary sd = dictService.findByCode(dtoRequest.getParentCode());
		if(sd == null) {
			throw SystemException.init("code: " + dtoRequest.getParentCode() + "; 不存在");
		}
		List dictList = FuncTool.copyPropertiesList(dictService.findByParent(sd.getId()), DtoQueryDictResponse.class);
		list.setData(dictList);
		return list;
	}
	
}
