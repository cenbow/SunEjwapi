package com.huaao.sunejwapi.api.dto.pub;

import com.huaao.sunejwapi.common.DemoData;
import com.huaao.sunejwapi.common.web.ApiDemoData;
import com.huaao.sunejwapi.common.web.ApiDescription;

public class DtoCreateNew {

	@ApiDescription("生成数据的ID")
	@ApiDemoData("12101")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
