package com.huaao.sunejwapi.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {
	
	
	/*** 
     * encode by Base64 
     */  
    public static String encodeBase64(byte[]input) throws Exception{  
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("encode", byte[].class);  
        mainMethod.setAccessible(true);  
         Object retObj=mainMethod.invoke(null, new Object[]{input});  
         return (String)retObj;  
    }  
    /*** 
     * decode by Base64 
     */  
    public static byte[] decodeBase64(String input) throws Exception{  
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("decode", String.class);  
        mainMethod.setAccessible(true);  
         Object retObj=mainMethod.invoke(null, input);  
         return (byte[])retObj;  
    }  
	
	public static void main(String[] args) {
		
		String base64 = GetImageStr("D:\\hct\\chinanews\\3\\1080209969.jpg");
		System.out.println(base64);
		
		GenerateImage(base64, "d:\\hct\\111.jpg");
	}
	
	public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
		byte[] data = null;  
		try {
			InputStream in = new FileInputStream(imgFilePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		  
		// 对字节数组Base64编码  
		BASE64Encoder encoder = new BASE64Encoder();  
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串  
	}
		  
	public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片  
		if (imgStr == null) // 图像数据为空  
			return false;  
			BASE64Decoder decoder = new BASE64Decoder();  
		try {  
			// Base64解码  
			byte[] bytes = decoder.decodeBuffer(imgStr);  
			for (int i = 0; i < bytes.length; ++i) {  
				if (bytes[i] < 0) {// 调整异常数据  
					bytes[i] += 256;  
				}  
			}  
			// 生成jpeg图片  
			OutputStream out = new FileOutputStream(imgFilePath);  
			out.write(bytes);  
			out.flush();  
			out.close();  
			return true;  
		} catch (Exception e) {  
			return false;  
		}  
	}  
}
