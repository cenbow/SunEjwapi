package com.huaao.sunejwapi.common;

public class Page {
	
	//当前页
	private int pageNum = 1;
	
	//分页索引
	private int offset = 0;
	
	//每页条数
	private int pageSize = 10;

	//总条数 
	private int totalCount = 0;
	
	private String sortby;
	
	public Page(int pageNum, int pageSize){
		this.pageNum = pageNum<1?1:pageNum;
		this.pageSize = pageSize<1?10:pageSize;
		this.offset = (this.pageNum-1)*this.pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getOffset() {
		return offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getSortby() {
		return sortby;
	}

	public void setSortby(String sortby) {
		this.sortby = sortby;
	}
	
	
}
