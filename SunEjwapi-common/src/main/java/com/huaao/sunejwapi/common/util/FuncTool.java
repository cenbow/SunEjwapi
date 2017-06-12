package com.huaao.sunejwapi.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.huaao.sunejwapi.common.DemoData;
import com.huaao.sunejwapi.common.web.ApiDemoData;
import com.huaao.sunejwapi.common.web.ApiDescription;
import com.huaao.sunejwapi.common.web.ApiLoop;

import net.sf.json.JSONObject;

public class FuncTool {

	public static Set<String> FILTER_FILED = new HashSet<>();
	static {
		FILTER_FILED.add("serialVersionUID");
		FILTER_FILED.add("_uid");
		FILTER_FILED.add("_data");
	}
	
	public static synchronized String createUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static void copyProperties(Object source, Object target) {
		BeanUtils.copyProperties(source, target);
		copyPropertiesDate(source, target);
	}
	
	/**
	 * 属性复制扩展，包含int转boolean  
	 * @param source
	 * @param target
	 */
	public static void copyPropertiesExt(Object source, Object target) {
		if(source == null){
			return;
		}
		BeanUtils.copyProperties(source, target);
		copyPropertiesDateExt(source, target);
	}
	
	public static void copyPropertiesDateExt(Object source, Object target) {
		Method[] sourceM = source.getClass().getMethods();
		Method[] targetM = target.getClass().getMethods();
		try {
			for (Method sItem : sourceM) {
				for (Method tItem : targetM) {
					String sName = sItem.getName();
					String tName = tItem.getName();
					if (sName.startsWith("get") && tName.startsWith("set")
							&& sName.substring(3).equals(tName.substring(3))) {
						Class[] sParamsType = sItem.getParameterTypes();
						Class sReturnType = sItem.getReturnType();
						Class[] tParamsType = tItem.getParameterTypes();
						if (sParamsType.length == 0 && tParamsType.length == 1) {
							try {
								if (sReturnType.equals(Date.class) && tParamsType[0].equals(String.class)) {
									SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Object o = sItem.invoke(source);
									if (o != null) {
										Date date = (Date) o;
										if (date != null) {
											tItem.invoke(target, dateformat1.format(date));
										}
									}
								}

								if (sReturnType.equals(String.class) && tParamsType[0].equals(Date.class)) {
									Object o = sItem.invoke(source);
									if (o != null) {
										Date datetime = DateTool.parseDateTime(o.toString());
										tItem.invoke(target, datetime);
									}
								}
								
								if (sReturnType.equals(Integer.class) && tParamsType[0].equals(Boolean.class)) {
									Object o = sItem.invoke(source);
									if (o != null) {
										Integer val = (Integer) o;
										if (val != null) {
											boolean retVal = false;
											if(val.intValue() == 1){
												retVal = true;
											}
											tItem.invoke(target, retVal);
										}
									}
								}
							} catch (Exception e) {
							}
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List copyPropertiesListExt(List source, Class c) {
		try {
			if (source == null) {
				return null;
			}
			List list = new ArrayList<>();
			for (Object o : source) {
				Object t = c.newInstance();
				BeanUtils.copyProperties(o, t);
				copyPropertiesDateExt(o, t);
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public static <T> T copyPropertiesClass(Object source, Class<T> c) {
		try {
			if (source == null) {
				return null;
			}
			Object o = c.newInstance();
			BeanUtils.copyProperties(source, o);
			copyPropertiesDate(source, o);
			return (T) o;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public static List copyPropertiesList(List source, Class c) {
		try {
			if (source == null) {
				return null;
			}
			List list = new ArrayList<>();
			for (Object o : source) {
				Object t = c.newInstance();
				BeanUtils.copyProperties(o, t);
				copyPropertiesDate(o, t);
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public static void fillCreaterAndUpdater(Object obj, String uid) {
		CreaterUpdater cu = new CreaterUpdater();
		cu.setCreateTime(new Date());
		cu.setUpdateTime(new Date());
		cu.setCreatorId(uid);
		cu.setUpdaterId(uid);
		BeanUtils.copyProperties(cu, obj);
	}

	public static void fillUpdater(Object obj, String uid) {
		Updater cu = new Updater();
		cu.setUpdateTime(new Date());
		cu.setUpdaterId(uid);
		BeanUtils.copyProperties(cu, obj);
	}

	public static void copyPropertiesDate(Object source, Object target) {
		Method[] sourceM = source.getClass().getMethods();
		Method[] targetM = target.getClass().getMethods();
		try {
			for (Method sItem : sourceM) {
				for (Method tItem : targetM) {
					String sName = sItem.getName();
					String tName = tItem.getName();
					if (sName.startsWith("get") && tName.startsWith("set")
							&& sName.substring(3).equals(tName.substring(3))) {
						Class[] sParamsType = sItem.getParameterTypes();
						Class sReturnType = sItem.getReturnType();
						Class[] tParamsType = tItem.getParameterTypes();
						if (sParamsType.length == 0 && tParamsType.length == 1) {
							try {
								if (sReturnType.equals(Date.class) && tParamsType[0].equals(String.class)) {
									SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Object o = sItem.invoke(source);
									if (o != null) {
										Date date = (Date) o;
										if (date != null) {
											tItem.invoke(target, dateformat1.format(date));
										}
									}
								}

								if (sReturnType.equals(String.class) && tParamsType[0].equals(Date.class)) {
									Object o = sItem.invoke(source);
									if (o != null) {
										Date datetime = DateTool.parseDateTime(o.toString());
										tItem.invoke(target, datetime);
									}
								}
							} catch (Exception e) {
							}
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> copyPropertiesByName(List list, String propertyName) {
		List<String> myList = new ArrayList<>();
		Set<Object> myset = new HashSet<>();
		if (list != null) {
			for (Object o : list) {
				try {
					Method m = o.getClass().getMethod("get" + StringUtils.capitalize(propertyName));
					Object returnO = m.invoke(o);
					if (m != null && !StringUtils.isEmpty(returnO) && !myset.contains(returnO)) {
						myList.add(String.valueOf(returnO));
						myset.add(returnO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return myList;
	}

	public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
	
	public static String getErrorInfoFromException(Exception e) {  
        try {  
            StringWriter sw = new StringWriter();  
            PrintWriter pw = new PrintWriter(sw);  
            e.printStackTrace(pw);  
            return sw.toString() ;  
        } catch (Exception e2) {  
            return "bad getErrorInfoFromException";  
        }  
    }  
	
	public static void getClassField(Class aClazz, List<Field> list) {
		if (aClazz == null) {
			return;
		}
		
		Class superclass = aClazz.getSuperclass();
		if (superclass != Object.class) {// 简单的递归一下
			getClassField(superclass, list);
		}
		
		Field[] declaredFields = aClazz.getDeclaredFields();
		for (Field field : declaredFields) {
			list.add(field);
		}
		
		
	}
	
	public static void readDtoFiledToJson(Class clazz, JSONObject dataJson, Class genClass , String parentName,boolean isLoop) {
		if(parentName == null || "".equals(parentName)){
			parentName = "";
		}else {
			parentName = parentName + ".";
		}
 		List<Field> listFileds = new ArrayList<>();
		getClassField(clazz, listFileds);
		if(genClass != null &&  !genClass.isPrimitive() && !String.class.equals(genClass)){
			getClassField(genClass, listFileds);
		}
		for (Field filed : listFileds) {
			if(FILTER_FILED.contains(filed.getName())){
				continue;
			}
			filed.setAccessible(true);
			String desc = "";
			ApiDescription filedDesc = filed.getAnnotation(ApiDescription.class);
			if (filedDesc != null) {
				desc = filedDesc.value();
			}
			if (!List.class.equals(filed.getType())) {
				JSONObject jo = new JSONObject();
				jo.put("_desc", desc);
				String name = filed.getType().getName();
				jo.put("_type", name.substring(name.lastIndexOf(".")+1));
				if(filed.getAnnotation(NotBlank.class) != null || filed.getAnnotation(NotEmpty.class) != null || filed.getAnnotation(NotNull.class) != null){
					jo.put("_notNull", "是");
				}else{
					jo.put("_notNull", "否");
				}
				dataJson.put(parentName + filed.getName(), jo);
				if(filed.getType().getPackage().toString().contains("com.huaao") && !isLoop){
					readDtoFiledToJson(filed.getType(), dataJson,null,parentName+filed.getName() , filed.getAnnotation(ApiLoop.class) != null);
				}
				
			}else{
				JSONObject jo = new JSONObject();
				jo.put("_desc", desc);
				jo.put("_type", "List");
				Type fc = filed.getGenericType();
				Class c = null;
				try {
					ParameterizedType pt = (ParameterizedType) fc;
					c = (Class)pt.getActualTypeArguments()[0];
				} catch (Exception e) {
 				}
				dataJson.put(parentName + filed.getName(), jo);
				if(c != null){
					if(c.getPackage().toString().contains("com.huaao") && !isLoop){
						readDtoFiledToJson(c, dataJson,null,parentName + filed.getName(),  filed.getAnnotation(ApiLoop.class) != null);
					}
				}
			}
		}
	}
	
	public static <T> T createDemoData(Class<T> clazz, Class genClass,boolean isLoop){
		try {
			T o = null;
			o = clazz.newInstance();
			createDemoData(clazz, genClass, o,isLoop);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void createDemoData(Class clazz, Class genClass, Object o,boolean isLoop){
		try {
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
				if(filed.getType().getPackage().toString().contains("com.huaao") && !isLoop){
					Object o1 =createDemoData(filed.getType(), null,filed.getAnnotation(ApiLoop.class) != null);
					setFiledValue(o,filed,o1);
				}
				//list 
				if(List.class.equals(filed.getType()) ){
					List list = new ArrayList<>();
					if(filed.getGenericType().toString().contains("List<T>") && !isLoop){
						Object o1 = createDemoData(genClass, null,filed.getAnnotation(ApiLoop.class) != null);
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
							if(pname.contains("com.huaao") && !isLoop){
								Object o1 = createDemoData(c, null,filed.getAnnotation(ApiLoop.class) != null);
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
					String pname = genClass.getPackage().toString();
					if(pname.contains("com.huaao") && !isLoop){
						setFiledValue(o,filed,createDemoData(genClass, null,filed.getAnnotation(ApiLoop.class) != null));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void setFiledValue(Object o , Field field, Object value ){
		try {
			Method m = o.getClass().getMethod("set"+StringUtils.capitalize(field.getName()) , field.getType());
			m.invoke(o, value);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 驼峰形式转换成下划线形式
	 */
	public static String underscoreName(String str) {
		if(StringUtils.isEmpty(str))
			return str;
		List record =new ArrayList();
        for(int i=0;i<str.length();i++){
            char tmp =str.charAt(i);
            if((tmp<='Z')&&(tmp>='A')){
                record.add(i);//记录每个大写字母的位置
            }
        }
        if(record.size() > 0){
            str= str.toLowerCase();
            char[] charofstr = str.toCharArray();
            String[] t =new String[record.size()];
            for(int i=0;i<record.size();i++){
                t[i]="_"+charofstr[(int)record.get(i)];//
            }
            String result ="";
            int flag=0;
            for(int i=0;i<str.length();i++){
                if((flag<record.size())&&(i==(int)record.get(flag))){
                    result+=t[flag];
                    flag++;
                }
                else{
                    result+=charofstr[i];
                }
            }
            return result;
        }
        return str;
	}
 
	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
	 * 例如：HELLO_WORLD->HelloWorld
	 * @param name 转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String camelName(String name) {
	    StringBuilder result = new StringBuilder();
	    // 快速检查
	    if (name == null || name.isEmpty()) {
	        // 没必要转换
	        return "";
	    } else if (!name.contains("_")) {
	        // 不含下划线，仅将首字母小写
	        return name.substring(0, 1).toLowerCase() + name.substring(1);
	    }
	    // 用下划线将原始字符串分割
	    String camels[] = name.split("_");
	    for (String camel :  camels) {
	        // 跳过原始字符串中开头、结尾的下换线或双重下划线
	        if (camel.isEmpty()) {
	            continue;
	        }
	        // 处理真正的驼峰片段
	        if (result.length() == 0) {
	            // 第一个驼峰片段，全部字母都小写
	            result.append(camel.toLowerCase());
	        } else {
	            // 其他的驼峰片段，首字母大写
	            result.append(camel.substring(0, 1).toUpperCase());
	            result.append(camel.substring(1).toLowerCase());
	        }
	    }
	    return result.toString();
	}
}
 