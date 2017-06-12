package com.huaao.sunejwapi.api.dto.pub;

import java.util.ArrayList;
import java.util.List;

import com.huaao.sunejwapi.api.dto.bean.PageInfo;

public class DtoPublicResponsePager <T> extends DtoPublicResponse {

	private List<T> data = new ArrayList<T>();
	
	private PageInfo pageInfo = new PageInfo();
	
	public DtoPublicResponsePager(){
		this.pageInfo.setData(data);
	}
	
	public void setRecords(Integer records) {
		this.pageInfo.setRecords(records);
	}
	 
	public void setPage(String page) {
		if(page == null){
			this.pageInfo.setPage(1);
		}
		try{
			this.pageInfo.setPage(Integer.parseInt(page));
		}catch(Exception e){
			this.pageInfo.setPage(1);
		}
	}
	public void setPage(Integer page) {
		if(page == 0) {
			page = 1;
		}
		this.pageInfo.setPage(page);
	}
	
	public void setPageSize(int pageSize) {
		this.pageInfo.setPageSize(pageSize);
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> rows) {
		this.data = rows;
		this.pageInfo.setData(rows);
	}

	public void addData(T row){
		this.data.add(row);
	}
	
	public PageInfo getPageInfo(){
		return this.pageInfo;
	}

}
