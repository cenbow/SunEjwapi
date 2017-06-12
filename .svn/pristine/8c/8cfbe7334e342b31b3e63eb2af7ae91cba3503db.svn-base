package com.huaao.sunejwapi.common.sms;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaao.sunejwapi.common.security.Base64;
import com.huaao.sunejwapi.common.security.MD5;
import com.huaao.sunejwapi.common.util.JSONUtil;


public class SmsUtil {
	
	private static Logger log = LoggerFactory.getLogger(SmsUtil.class);
	
	private static final String restUrl = "https://app.cloopen.com:8883";
	private static final String ACCOUNT_SID = "8a48b5515493a1b70154c2827d572ea8";
	private static final String AUTH_TOKEN = "9d4225f659e94dffa43e4417ce6959a7";
	private static final String appID = "8aaf070855e333c00155e39d8c630169";
	private static final String templateId = "102567";
	
	private final static String rlyReturnCode = "000000";
	
	public static String send(String phone,String num) throws ClientProtocolException, IOException {
		String msg = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try{
			httpClient = HttpClients.createDefault();
			String url = buildSmsTemplateUrl();
			HttpPost post = new HttpPost(url);
			JSONObject json = new JSONObject();
			json.put("to", phone);
			json.put("appId", appID);
			json.put("templateId", templateId);
			json.put("datas", new Object[]{num});
			
			StringEntity entity = new StringEntity(json.toString(),Charset.forName("UTF-8"));//解决中文乱码问题
			setDefault(entity, post);
			
			httpResponse = httpClient.execute(post);
			HttpEntity responseEntity = httpResponse.getEntity();
			if (null != responseEntity){
				msg = EntityUtils.toString(responseEntity);
			}
		}catch(Exception e){
			log.error("send msg fail", e);
		}finally {
			if(httpResponse!=null){
				httpResponse.close();
			}
			if(httpClient!=null){
				httpClient.close();
			}
		}
		return msg;
	}
	
	public static String createSubAccount(String cellphone) throws IOException {
		String msg = null;
		String url = buildSubAccountsUrl();
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try{
			httpClient = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
			JSONObject json = new JSONObject();
			json.put("appId", appID);
			json.put("friendlyName", cellphone);
			StringEntity entity = new StringEntity(json.toString(),Charset.forName("UTF-8"));//解决中文乱码问题
			
			setDefault(entity,post);
			
			httpResponse = httpClient.execute(post);
			HttpEntity responseEntity = httpResponse.getEntity();
			if (null != responseEntity){
				String result = EntityUtils.toString(responseEntity);
				SubAccountResponse subaResponse = JSONUtil.decodeJson(result, SubAccountResponse.class);
				if(subaResponse!=null&&StringUtils.equals(subaResponse.statusCode,rlyReturnCode)) {
					msg = subaResponse.SubAccount.toString();
				}else{
					msg = getSubAccount(cellphone);
				}
			}
		}catch(Exception e){
			log.error("createSubAccount fail", e);
		}finally {
			if(httpResponse!=null){
				httpResponse.close();
			}
			if(httpClient!=null){
				httpClient.close();
			}
		}
		return msg;
	}
	
	public static String getSubAccount(String phone) throws IOException {
		String msg = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try{
		    String url = buildQuerySubAccountByNameUrl();
			httpClient = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
			JSONObject json = new JSONObject();
			json.put("appId", appID);
			json.put("friendlyName", phone);
			
			StringEntity entity = new StringEntity(json.toString(),Charset.forName("UTF-8"));//解决中文乱码问题
			setDefault(entity,post);
			
			httpResponse = httpClient.execute(post);
			HttpEntity responseEntity = httpResponse.getEntity();
			if (null != responseEntity){
				String result = EntityUtils.toString(responseEntity);
				SubAccountResponse subaResponse = JSONUtil.decodeJson(result, SubAccountResponse.class);
				if(subaResponse!=null&&StringUtils.equals(subaResponse.statusCode,rlyReturnCode)) {
					msg = subaResponse.SubAccount.toString();
				}
			}
		}catch(Exception e){
			log.error("getSubAccount fail", e);
		}finally {
			if(httpResponse!=null){
				httpResponse.close();
			}
			if(httpClient!=null){
				httpClient.close();
			}
		}
		return msg;
	}

	private static void setDefault(StringEntity entity , HttpPost post) {
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		post.addHeader("Authorization", buildAuthorization());
		post.addHeader("Accept", "application/json");
		post.addHeader("Content-Type", "application/json;charset=utf-8");
		post.setEntity(entity);
	}
	
	private static String buildAuthorization() {
		String base64 = "";
		String key = ACCOUNT_SID + ":" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		try {
			base64 = Base64.encodeBase64(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return base64;
	}

	private static String buildSmsTemplateUrl() {
		StringBuffer buff = new StringBuffer();
		buff.append(restUrl + "/2013-12-26/Accounts/"+ACCOUNT_SID+"/SMS/TemplateSMS?sig=");
		buff.append(buildSig());
		return buff.toString();
	}
	
	private static String buildSubAccountsUrl() {
		StringBuffer buff = new StringBuffer();
		buff.append(restUrl + "/2013-12-26/Accounts/"+ACCOUNT_SID+"/SubAccounts?sig=");
		buff.append(buildSig());
		return buff.toString();
	}
	
	private static String buildQuerySubAccountByNameUrl() {
		StringBuffer buff = new StringBuffer();
		buff.append(restUrl + "/2013-12-26/Accounts/"+ACCOUNT_SID+"/QuerySubAccountByName?sig=");
		buff.append(buildSig());
		return buff.toString();
	}

	private static Object buildSig() {
		String key = ACCOUNT_SID + AUTH_TOKEN + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return MD5.encryptionMD5(key).toUpperCase();
	}	
}
