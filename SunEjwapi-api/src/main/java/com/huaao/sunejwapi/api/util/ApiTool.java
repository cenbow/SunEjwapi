package com.huaao.sunejwapi.api.util;

import java.util.List;

import com.huaao.sunejwapi.api.dto.pub.DtoPublicRequestPager;
import com.huaao.sunejwapi.api.dto.pub.DtoPublicResponsePager;
import com.huaao.sunejwapi.common.Page;

public class ApiTool {

	public static Page convert(DtoPublicRequestPager dtoRequest){
		if(dtoRequest.getPageSize() == 0){
			Page p = new Page(dtoRequest.getPage(), 1000);
			p.setSortby(dtoRequest.getSortby());
			return p;
		}else{
			Page p = new Page(dtoRequest.getPage(), dtoRequest.getPageSize());
			p.setSortby(dtoRequest.getSortby());
			return p;
		}
	}
	
	public static  void fillPagerData(Page p, DtoPublicResponsePager pager){
		if(p != null){
			pager.setPage(p.getPageNum());
			pager.setPageSize(p.getPageSize());
			pager.setRecords(p.getTotalCount());
		}
	}
	
}
