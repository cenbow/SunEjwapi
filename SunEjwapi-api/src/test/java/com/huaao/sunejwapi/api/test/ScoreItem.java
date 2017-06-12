package com.huaao.sunejwapi.api.test;

public class ScoreItem {
	private String line_time;
	private String line_area;
	private String line_type;
	private String line_batch;
	private String lowest_grade;
	public ScoreItem(String line_time, String line_area, String line_type, String line_batch, String lowest_grade) {
		super();
		this.line_time = line_time;
		this.line_area = line_area;
		this.line_type = line_type;
		this.line_batch = line_batch;
		this.lowest_grade = lowest_grade;
	}
	@Override
	public String toString() {
		return "ScoreItem [line_time=" + line_time + ", line_area=" + line_area + ", line_type=" + line_type
				+ ", line_batch=" + line_batch + ", lowest_grade=" + lowest_grade + "]";
	}
	public String getLine_time() {
		return line_time;
	}
	public void setLine_time(String line_time) {
		this.line_time = line_time;
	}
	public String getLine_area() {
		return line_area;
	}
	public void setLine_area(String line_area) {
		this.line_area = line_area;
	}
	public String getLine_type() {
		return line_type;
	}
	public void setLine_type(String line_type) {
		this.line_type = line_type;
	}
	public String getLine_batch() {
		return line_batch;
	}
	public void setLine_batch(String line_batch) {
		this.line_batch = line_batch;
	}
	public String getLowest_grade() {
		return lowest_grade;
	}
	public void setLowest_grade(String lowest_grade) {
		this.lowest_grade = lowest_grade;
	}
}
