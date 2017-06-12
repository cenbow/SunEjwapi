package com.huaao.sunejwapi.common.yuntu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.huaao.sunejwapi.common.util.HttpClientUtils;



public class YunTuApi {
	
	private static final String url = "http://yuntuapi.amap.com/datamanage/data/create";
	
	private static final String key = "9b5fc4b54aada032211000047d875f65";
	
	private static final String tableId = "58b6399aafdf520ea84159cf";
	
	public static Map<String, String> getYuntu(){
		Map<String, String> yuntu = new HashMap<String, String>();
		yuntu.put("key", key);
		yuntu.put("tableid", tableId);
		return yuntu;
	}
	
	
	public static String dataCreate(Map<String,String> user) throws ClientProtocolException, IOException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("key", key);
		params.put("tableid", tableId);
		params.put("data", buildData(user));
		Map<?, ?> respMap= HttpClientUtils.httpPostToMap(url ,params);
		if(respMap != null && respMap.containsKey("_id")){
			return respMap.get("_id").toString();
		}
		return null;
	}

	private static String buildData(Map<String,String> user) {
		JSONObject json = new JSONObject();
		json.put("_name", user.get("username"));
		json.put("_location", "0,0");
		json.put("userId", user.get("id"));
		return json.toString();
	}
	
}
