package com.huaao.sunejwapi.api.dto.pub;

import org.hibernate.validator.constraints.NotBlank;

import com.huaao.sunejwapi.common.web.ApiDescription;

public class DtoQueryDictRequest extends DtoPublicRequest{

	@ApiDescription("数据字典父CODE,落户申报材料的样图和地址 :residenceSummary,落户申报派出所列表:residenceStation,落户申报类型:residenceType ")
	@NotBlank
	private String parentCode;

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
}
