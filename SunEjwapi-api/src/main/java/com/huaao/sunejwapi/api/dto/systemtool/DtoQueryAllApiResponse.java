package com.huaao.sunejwapi.api.dto.systemtool;

import java.util.List;

import com.huaao.sunejwapi.api.dto.pub.DtoPublicResponse;

public class DtoQueryAllApiResponse extends DtoPublicResponse{

	private List<String> data;

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}
	
	
	
}
