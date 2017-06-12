package com.huaao.sunejwapi.api.test;

public class ProfessionItem {
	private String pr_name;
	private String pr_id;
	private String state;
	private String pr_info;	
	private String pr_course;
	public String getPr_name() {
		return pr_name;
	}
	public void setPr_name(String pr_name) {
		this.pr_name = pr_name;
	}
	public String getPr_id() {
		return pr_id;
	}
	public void setPr_id(String pr_id) {
		this.pr_id = pr_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "ProfessionItem [pr_name=" + pr_name + ", pr_id=" + pr_id + ", state=" + state + ", pr_info=" + pr_info
				+ ", pr_course=" + pr_course + "]";
	}
	public String getPr_info() {
		return pr_info;
	}
	public void setPr_info(String pr_info) {
		this.pr_info = pr_info;
	}
	public String getPr_course() {
		return pr_course;
	}
	public ProfessionItem(String pr_name, String pr_id, String state, String pr_info, String pr_course) {
		super();
		this.pr_name = pr_name;
		this.pr_id = pr_id;
		this.state = state;
		this.pr_info = pr_info;
		this.pr_course = pr_course;
	}
	public void setPr_course(String pr_course) {
		this.pr_course = pr_course;
	}

	
}
