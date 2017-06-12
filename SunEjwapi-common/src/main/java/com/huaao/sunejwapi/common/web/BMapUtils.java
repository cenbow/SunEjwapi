package com.huaao.sunejwapi.common.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BMapUtils {
	
	public static boolean isPtInPoly(double ALon, double ALat, List<Point> APoints) {  
	    int iCount = 0;  
	    if (APoints.size() < 3) return false;  
        //System.out.println(ALon+","+ALat);
	    iCount = APoints.size();  
	    boolean result = false;
	    for(int i = 0, j = iCount - 1; i < iCount; i++)
        {
           Point p1 = APoints.get(i);
           Point p2 = APoints.get(j);
           boolean isInside = (p1.getLat() > ALat) !=  (p2.getLat() > ALat);
           //System.out.println(isInside);
           double polygon = (p2.getLng() - p1.getLng()) * (ALat - p1.getLat()) /(p2.getLat() - p1.getLat()) +p1.getLng(); 
           //System.out.println(polygon);
           if( isInside && polygon > ALon ){
        	   result = !result;
           }
           j = i;
        }
        return result;
	}  
	
	public static double calcDistance(Point p1, Point p2){
		double lon1 = (Math.PI / 180) * p1.getLng();  
        double lon2 = (Math.PI / 180) * p2.getLng();  
        double lat1 = (Math.PI / 180) * p1.getLat();  
        double lat2 = (Math.PI / 180) * p2.getLat();  
  
        // double Lat1r = (Math.PI/180)*(gp1.getLatitudeE6()/1E6);  
        // double Lat2r = (Math.PI/180)*(gp2.getLatitudeE6()/1E6);  
        // double Lon1r = (Math.PI/180)*(gp1.getLongitudeE6()/1E6);  
        // double Lon2r = (Math.PI/180)*(gp2.getLongitudeE6()/1E6);  
  
        // 地球半径  
        double R = 6371;  
  
        // 两点间距离 km，如果想要米的话，结果*1000就可以了  
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;  
  
        return d * 1000;  
	}
	
	public static List<Point> mockNearByPoint(Point point, double raduis){
		int size = 6000;
		double h = 2*raduis/(size-1);
		List<Point> list = new ArrayList<Point>();
		for(int i=2;i<size;i++){
			double lng = point.getLng() + raduis * Math.cos((i-1)*h);
			double lat = point.getLat() + raduis * Math.sin((i-1)*h);
		    Point p = new Point(lng, lat);
		    list.add(p);
		}
		return list;
	}
	
	public static void main(String[] args){
		//114.42243,30.463128|114.423203,30.462226|114.427084,30.462233|114.42667,30.47021|114.418622,30.469089
		List<Point> pts = new ArrayList<>();
		String xyz = "114.387617,30.439201|"
				+ "114.392324,30.439372|"
				+ "114.400054,30.441093|"
				+ "114.402484,30.441669|"
				+ "114.404047,30.443724|"
				+ "114.407802,30.446541|"
				+ "114.413947,30.451398|"
				+ "114.417935,30.453391|"
				+ "114.427147,30.454667|114.427003,30.470543|114.423037,30.470574|"
				+ "114.415132,30.468768|114.416498,30.484953|114.393429,30.485015|"
				+ "114.380997,30.487754|114.375607,30.473437|114.369957,30.465555|"
				+ "114.371933,30.46101|114.373154,30.458706|114.375706,30.45469|"
				+ "114.376496,30.447125|114.374592,30.447343|"
				+ "114.372723,30.446627|114.373285,30.442961|114.384797,30.443513|114.386234,30.439715";
		for(String loc : xyz.split("\\|")){
		  double lng = Double.parseDouble(loc.split(",")[0]);
		  double lat = Double.parseDouble(loc.split(",")[1]);
		  pts.add(new Point(lng, lat));
		}
		
		Point alertPoint = CoordConvert.Amap2Baidu(114.418504,30.458276);
		Point pt = new Point(114.418504,30.458276);
		System.out.println(alertPoint.toString() + pt.toString());
		System.out.println(BMapUtils.isPtInPoly(pt.getLng(), pt.getLat(), pts));
		System.out.println(BMapUtils.isPtInPoly(alertPoint.getLng(), alertPoint.getLat(), pts));
		Point p1 = new Point("114.431715,30.478595");
		Point p2 = new Point("114.504058,30.554827");

		System.out.println(calcDistance(p1, p2));
		
	    for(Point p: mockNearByPoint(alertPoint, 4.4785)){
	    	System.out.println(p.toString());
	    }
	}
}
