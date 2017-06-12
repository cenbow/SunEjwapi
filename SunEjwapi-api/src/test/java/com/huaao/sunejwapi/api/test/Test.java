package com.huaao.sunejwapi.api.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

import com.huaao.sunejwapi.api.dto.pub.DtoPublicResponse;
import com.huaao.sunejwapi.api.dto.pub.DtoPublicResponseObj;
import com.huaao.sunejwapi.api.dto.pub.DtoPublicResponsePager;
import com.huaao.sunejwapi.common.DemoData;
import com.huaao.sunejwapi.common.util.DateTool;
import com.huaao.sunejwapi.common.util.FileUtil;
import com.huaao.sunejwapi.common.util.FuncTool;
import com.huaao.sunejwapi.common.util.JsonFormatter;
import com.huaao.sunejwapi.common.web.ApiDemoData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class Test {
	private static JsonConfig jsonDateConfig = new JsonConfig();   
	static {
		jsonDateConfig.registerJsonValueProcessor(Date.class , new JsonValueProcessor() {
			@Override
			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
				if(value != null && value instanceof Date){  
					return ((Date)value).getTime();
				}
				return value;
			}
			
			@Override
			public Object processArrayValue(Object value, JsonConfig jsonConfig) {
				return value;
			}
		}); 
	}
	
	public static void main(String[] args) throws Exception{
		String logPath = "E:/createDoc/";
		//打印比较结果
		List<String> files = FileUtil.getAllFile(logPath);
		Collections.sort(files);
		Collections.reverse(files);
		if(files.size() > 0 ){
			String content = FileUtil.loadFile(files.get(0), "utf-8") ;
			JSONObject oldJo = JSONObject.fromObject(content);
			Set<String> list = getJsonKey(null,oldJo);
			for(String item : list){
				System.out.println(item);;
			}
		}
	}
	
	private static Set<String> getJsonKey(String head,JSONObject jsonObj){
		Set<String> filterKey = new HashSet<>();
		filterKey.add("_desc");
		filterKey.add("_type");
		filterKey.add("_notNull");
		filterKey.add("_catalog");
		filterKey.add("_method");
		filterKey.add("_demoData");
		
		Set<String> keys = new TreeSet();
        Set<Entry<String, Object>> set =  jsonObj.entrySet();
        Iterator<Entry<String, Object>> iter = set.iterator();
        while (iter.hasNext()) {
            Entry<String, Object> entry = iter.next();
            String key = entry.getKey();
            if(filterKey.contains(key)){
            	continue;
            }
            Object value = entry.getValue();

            keys.add(head==null?key:head+key);
            //判断value类型
            if (value instanceof JSONObject) {
            	Set<String> list = getJsonKey(head==null?key+"-":head+key+"-", (JSONObject) value);
                keys.addAll(list);
            }else if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                if (jsonArray.size()!=0) {
                    Object obj = jsonArray.get(0);
                    //对array中的类型进行判断
                    if (obj instanceof JSONObject) {
                    	Set<String> list = getJsonKey(head==null?key+"-":head+key+"-", (JSONObject)jsonArray.get(0));
                        keys.addAll(list);
                    }else{
                        keys.add(head==null?key:head+key);
                    }
                }
            }
        }
        return keys;
    }
	
	public static <T> T createDemoData(Class<T> clazz, Class genClass){
		T o = null;
		try {
			o = clazz.newInstance();
			List<Field> listFileds = new ArrayList<>();
			FuncTool.getClassField(clazz, listFileds);
			for (Field filed : listFileds) {
				if(FuncTool.FILTER_FILED.contains(filed.getName())){
					continue;
				}
				filed.setAccessible(true);
				ApiDemoData filedDemoData = filed.getAnnotation(ApiDemoData.class);
				if (filedDemoData != null && !StringUtils.isEmpty(filedDemoData.value())) {
					if(Integer.class.equals(filed.getType())){
						setFiledValue(o,filed,Integer.parseInt( filedDemoData.value()));
					}else if(Double.class.equals(filed.getType())){
						setFiledValue(o,filed,Double.parseDouble( filedDemoData.value()));
					}else if(Float.class.equals(filed.getType())){
						setFiledValue(o,filed,Float.parseFloat( filedDemoData.value()));
					}else if(Date.class.equals(filed.getType())){
						setFiledValue(o,filed,new Date());
					}else if(Boolean.class.equals(filed.getType())){
						setFiledValue(o,filed,true);
					}else if(Long.class.equals(filed.getType())){
						setFiledValue(o,filed,Long.parseLong( filedDemoData.value()));
					}else if(Byte.class.equals(filed.getType())){
						setFiledValue(o,filed,Byte.parseByte( filedDemoData.value()));
					}else if(String.class.equals(filed.getType())){
						setFiledValue(o,filed,filedDemoData.value());
					}
				}else{
					if(Integer.class.equals(filed.getType())){
						setFiledValue(o,filed,1);
					}else if(Double.class.equals(filed.getType())){
						setFiledValue(o,filed,12121);
					}else if(Float.class.equals(filed.getType())){
						setFiledValue(o,filed,12221);
					}else if(Date.class.equals(filed.getType())){
						setFiledValue(o,filed,new Date());
					}else if(Boolean.class.equals(filed.getType())){
						setFiledValue(o,filed,true);
					}else if(Long.class.equals(filed.getType())){
						setFiledValue(o,filed,Long.parseLong(DemoData.STMAP));
					}else if(Byte.class.equals(filed.getType())){
						setFiledValue(o,filed,Byte.parseByte("1"));
					}else if(String.class.equals(filed.getType())){
						setFiledValue(o,filed,"");
					}
				}
				
				//list 
				if(List.class.equals(filed.getType()) ){
					List list = new ArrayList<>();
					System.out.println(filed.getGenericType().toString());
					if(filed.getGenericType().toString().contains("List<T>")){
						Object o1 = createDemoData(genClass, null);
						list.add(o1);
					}else{
						Type fc = filed.getGenericType();
						Class c = null;
						try {
							ParameterizedType pt = (ParameterizedType) fc;
							c = (Class)pt.getActualTypeArguments()[0];
						} catch (Exception e) {}
						if(c != null){
							String pname = c.getPackage().toString();
							if(pname.contains("api.dto")){
								Object o1 = createDemoData(c, null);
								list.add(o1);
							}
							if(String.class.equals(c)){
								if (filedDemoData != null){
									String[] str = filedDemoData.value().split(";");
									for(String item : str){
										list.add(item);	
									}
								}
							}else if(Integer.class.equals(c)){
								if (filedDemoData != null){
									String[] str = filedDemoData.value().split(";");
									for(String item : str){
										list.add(Integer.parseInt(item));	
									}
								}
							}
						}
					}
					setFiledValue(o,filed,list);
				}
				//obj
				if("T".equals(filed.getGenericType().toString()) && genClass != null){
					String pname = clazz.getPackage().toString();
					if(pname.contains("api.dto")){
						setFiledValue(o,filed,createDemoData(genClass, null));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	private static void setFiledValue(Object o , Field field, Object value ){
		try {
			Method m = o.getClass().getMethod("set"+StringUtils.capitalize(field.getName()) , field.getType());
			m.invoke(o, value);
			System.out.println(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void test() throws Exception {
		Field f = DtoPublicResponseObj.class.getDeclaredField("data");
		Field f1 = DtoPublicResponse.class.getDeclaredField("code");
		
		Class c = f.getType();
		Type t = f.getGenericType();
		System.out.println(t.toString());
		Class c1 = f1.getType(); 
		Type t1 = f1.getGenericType();
		System.out.println(c.getName());
	}
}
