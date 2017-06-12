package com.huaao.sunejwapi.common.web;


/**
 * 用于构造地图中的坐标点
 * **/
public class Point {
	
	private double lat;// 纬度
	private double lng;// 经度
	
	public Point() {
	}
	
	/**
	 * 根据经纬度字符串构造 经度,纬度
	 * @param position  114.42507755353,30.463929127601
	 */
	public Point(String position){
		double lng = Double.parseDouble(position.split(",")[0]);
		double lat = Double.parseDouble(position.split(",")[1]);
		this.lng = lng;
		this.lat = lat;
	}
	
	public Point(double lng, double lat) {
		this.lng = lng;
		this.lat = lat;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point bmapPoint = (Point) obj;
			return (bmapPoint.getLng() == lng && bmapPoint.getLat() == lat) ? true : false;
		} else {
			return false;
		}
	}
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	@Override
	public String toString() {
		return "Point [lat=" + lat + ", lng=" + lng + "]";
	}
	
}