package com.huaao.sunejwapi.api.dto.bean;

import java.util.List;

import com.huaao.sunejwapi.common.web.ApiDemoData;
import com.huaao.sunejwapi.common.web.ApiDescription;

public class PageInfo {

	@ApiDemoData("1")
	@ApiDescription("总条数")
	private Integer records = -1;

	@ApiDemoData("1")
	@ApiDescription("当前页")
	private Integer page = 1;

	@ApiDemoData("10")
	@ApiDescription("每页条数")
	private Integer pageSize = 0;
	
	private List _data = null;
	public Integer getRecords() {
		if (records == -1) {
			return _data.size();
		}
		return records;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setData(List list){
		this._data = list;
	}
	
	
}
