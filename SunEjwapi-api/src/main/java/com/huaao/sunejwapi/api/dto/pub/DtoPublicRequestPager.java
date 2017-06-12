package com.huaao.sunejwapi.api.dto.pub;

import org.springframework.util.StringUtils;

import com.huaao.sunejwapi.common.web.ApiDescription;

public class DtoPublicRequestPager extends DtoPublicRequest {
	
	@ApiDescription("查询第几页，默认第一页")
	private Integer page = 1;
	
	@ApiDescription("每页显示多少条数据，默认返回所有,默认最大1000")
	private Integer pageSize = 0;
	 
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		if(page != null){
			this.page = page;
		}
	}
	 
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize != null){
			this.pageSize = pageSize;
		}
		
	}
	 
	
}
