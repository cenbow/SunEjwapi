package com.huaao.sunejwapi.common.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.huaao.sunejwapi.common.util.HttpClientUtils;
import com.huaao.sunejwapi.common.util.JSONUtil;

import io.netty.util.internal.StringUtil;

public class CoordConvert {

	//coords=114.4192126092,%2030.4630951657&from=3&to=5&ak=
	private static String BAIDU_URL = "http://api.map.baidu.com/geoconv/v1/";
	
	private static String BAIDU_AK = "pYyi0ezfhpE9Exceq26QAcge";
	
	private static String AMAP_AK = "9b5fc4b54aada032211000047d875f65";
	
	private static String AMAP_URL = "http://restapi.amap.com/v3/assistant/coordinate/convert";
	
	private static String AMAP_SECRET = "2d9a69995c1fe02b2e5669bc4eb4eefd";
	
	public static Point Amap2Baidu(double lat, double lng){
		String loc =  Amap2Baidu(lat+","+lng);
		return new Point(loc);
	}
	
	public static String getSortParams(Map<String, ?> params) {
		// 删掉sign参数
		params.remove("sig");
		String contnt = "";
		Set<String> keySet = params.keySet();
		List<String> keyList = new ArrayList<String>();
		for (String key : keySet) {
			String value = (String)params.get(key);
			// 将值为空的参数排除
			if (!StringUtil.isNullOrEmpty(value)) {
				keyList.add(key);
			}
		}
		Collections.sort(keyList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				int length = Math.min(o1.length(), o2.length());
				for (int i = 0; i < length; i++) {
					char c1 = o1.charAt(i);
					char c2 = o2.charAt(i);
					int r = c1 - c2;
					if (r != 0) {
						// char值小的排前边
						return r;
					}
				}
				// 2个字符串关系是str1.startsWith(str2)==true
				// 取str2排前边
				return o1.length() - o2.length();
			}
		});
		//将参数和参数值按照排序顺序拼装成字符串
		int len = keyList.size();
		for (int i = 0; i < len; i++) {
			String key = keyList.get(i);
			contnt += key +"="+ params.get(key);
			if(i > 0 && i < len -1){
				contnt += "&";
			}
		}
		return contnt;
	}
	
	public static String genAmapSign(Map<String, String> params){
		String content =  getSortParams(params)+AMAP_SECRET;
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			return new String(md5.digest(content.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Point Baidu2Amap(double lat, double lng){
		return new Point(Baidu2Amap(lat+","+lng));
	}
	
	public static String Baidu2Amap(String location){
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", AMAP_AK);
		map.put("output", "json");
		map.put("coordsys", "baidu");
		map.put("locations", location);
		map.put("sig", genAmapSign(map));
		String content = HttpClientUtils.httpGet(AMAP_URL, map);
		Map<String, Object> result = JSONUtil.decodeJsonToMap(content);
		String loc = (String) result.get("locations");
		return loc;
	}
	
	public static void main(String[] args){
		String alertPoint = CoordConvert.Amap2Baidu("114.418504,30.458276");
		System.out.println(alertPoint.toString());
		String p = Baidu2Amap(alertPoint);
		System.out.println(p.toString());
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
		String amap = batchBaidu2Amap(xyz);
		System.out.println(amap);
		System.out.println(batchAmap2Baidu(amap));
	}

	public static String Amap2Baidu(String location) {
		Map<String, String> map = new HashMap();
		map.put("coords", location);
		map.put("from", "3");
		map.put("to", "5");
		map.put("ak", BAIDU_AK);
		String content = HttpClientUtils.httpGet(BAIDU_URL, map);
		Map<String, Object> result = JSONUtil.decodeJsonToMap(content);
		List<Object> list = (List)result.get("result");
		String loc = "";
		for(Object obj: list){
			Map<String, Object> corrd = (Map)(obj);
			loc += corrd.get("x")+","+corrd.get("y")+";";
		}
		return loc.substring(0, loc.length()-2);
	}
	
	/**
	 * 批量转换高德坐标为百度坐标，API一次最多100个，坐标之间;分号分隔，经纬度,逗号分隔
	 * @param locations
	 * @return
	 */
	public static String batchAmap2Baidu(String locations){
		String[] locs = locations.split(";");
		int h = 1;
		int begin = (h-1)*100;
		int end = h*100 > locs.length ? locs.length - 1 : h*100 -1;
		String result = "";
		while(begin < locs.length){
			String tmp = "";
			for(int i = begin; i<=end; i++){
				tmp += locs[i];
				if(i < end){
					tmp += ";";
				}
			}
			result += Amap2Baidu(tmp) + ";";
			h++;
			begin = (h-1)*100;
			end = h*100 > locs.length ? locs.length - 1 : h*100 -1;
		}
		return result.substring(0, result.length() - 2);
	}
	
	/**
	 * 批量转换百度坐标为高德坐标，API一次最多40个，坐标之间;分号分隔，经纬度,逗号分隔
	 * @param locations
	 * @return
	 */
	public static String batchBaidu2Amap(String locations){
		String[] locs = locations.split("\\|");
		int h = 1;
		int begin = (h-1)*40;
		int end = h*40 > locs.length ? locs.length - 1 : h*40 -1;
		String result = "";
		while(begin < locs.length){
			String tmp = "";
			for(int i = begin; i<=end; i++){
				tmp += locs[i];
				if(i < end){
					tmp += "|";
				}
			}
			result += Baidu2Amap(tmp) + ";";
			h++;
			begin = (h-1)*40;
			end = h*40 > locs.length ? locs.length - 1 : h*40 -1;
		}
		return result.substring(0, result.length() - 2);
	}
	
}
