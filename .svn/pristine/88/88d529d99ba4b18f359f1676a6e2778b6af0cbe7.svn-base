package com.huaao.sunejwapi.common.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaao.sunejwapi.common.security.Base64;
import com.huaao.sunejwapi.common.security.MD5;
import com.huaao.sunejwapi.common.util.DateTool;
import com.huaao.sunejwapi.common.util.GzipUtils;
import com.huaao.sunejwapi.common.util.HttpClientUtils;
import com.huaao.sunejwapi.common.util.JSONUtil;
import com.huaao.sunejwapi.common.util.ReadExcelUtils;

import io.netty.util.internal.StringUtil;


public class EmsUtils {
	private static Logger logger = LoggerFactory.getLogger(EmsUtils.class);

	private static final String DEFAULTCHARSET = "UTF-8";
	
	private static final String EMS_METHOD_CREATE_ORDER = "hbgv.jw.order.create";

	private static final String EMS_METHOD_TRACK_ORDER = "govern.mail.track";
	
	private static final String EMS_API_VERSION = "1.0";
	//正式appkey
	private static final String EMS_APP_KEY = "govern_hbvjw_app";
	//正式授权码
	private static final String EMS_CODE = "8ads797lk43lk43";
	//正式下单URL
	private static final String EMS_URL = "http://114.251.185.200:8183/govern-hbvjw-dock/api/getAway";
	
	
	//测试URL: 
	private static final String EMS_TEST_URL = "http://114.251.185.200:8183/wh-govern-hbvjw-dock/api/testAway";
	//测试授权码：
	private static final String EMS_TEST_CODE = "36578332432";
	//测试appKey：
	private static final String EMS_TEST_APP_KEY = "ems-govern-appkey";
	
	//EMS支付测试appkey
    public static final String EMS_TEST_PAY_APP_KEY = "02caf5852371b2d0ba35c3e839778955";
    //EMS支付测试appsecret
    private static final String EMS_TEST_PAY_APP_SECRET = "f64f2dbe2338ce7970d9e29642e7499a";
    
    //EMS支付正式appkey
    public static final String EMS_PAY_APP_KEY = "18842e0c5bce486e15963492fa673439";
    //EMS支付正式appsecret
    private static final String EMS_PAY_APP_SECRET = "7883300ca9624cbf4ac876a8adeb1269";
		

	public static String getSortParams(Map<String, ?> params) {
		// 删掉sign参数
		params.remove("sign");
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
		for (int i = 0; i < keyList.size(); i++) {
			String key = keyList.get(i);
			contnt += key + params.get(key);
		}
		return contnt;
	}
	
	public static String trackMail(String data){
		return callEmsAPI(data, EMS_METHOD_TRACK_ORDER);
	}
	
	public static String createNewOrder(String data){
		return callEmsAPI(data, EMS_METHOD_CREATE_ORDER);
	}

	
	/**
	 * EMS订单接口仅支持二进制流方式提交数据
	 * @param data
	 * @param method
	 * @return
	 */
	public static String callEmsAPI(String data, String method){
		//set default params
		Map<String, String> params = new HashMap<String, String>();
		params.put("timestamp", DateTool.dateToString(new Date(), "yyyyMMddHHmmss"));
		params.put("v", EMS_API_VERSION);
		params.put("app_key", EMS_APP_KEY);
		params.put("method", method);
		String content = EMS_CODE+getSortParams(params)+data;
		//System.out.println(content);
		String signstr = MD5.encryptionMD5(content).toUpperCase();
		//System.out.println(signstr);
		params.put("sign", signstr);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(HTTP.CONTENT_TYPE, "application/octet-stream");
		try {
			byte[] gz = GzipUtils.gZip(data.getBytes("UTF-8"));
			String result = HttpClientUtils.httpPostBody(EMS_URL, params, headers, Base64.encodeBase64(gz));
			System.out.println(result);
			byte[] bt = GzipUtils.unGZip(Base64.decodeBase64(result));
			String sbody = new String(bt,"UTF-8");
			logger.info("ems interface "+method+" result "+sbody);
            return sbody;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		Map<String, List<EmsOrder>> params = new HashMap<String, List<EmsOrder>>();
		EmsOrder order = new EmsOrder();
		order.setOrderGuid("GNEMSadc9ac79c78a537d");
		order.setReceiveAddr("安徽省合肥市瑶海区");
		order.setReceiveDistCode("340102");
		order.setReceiveLinker("收件人A");
		order.setReceiveLinkMobile("15256635531");
		order.setPoliceCode("342974");
//		order.setPaymentAccount("20.9");
//		order.setWeight("10.8");
//		order.setInsuredAccount("60");
//		order.setInsuranceAccount("10");
//		order.setSplitInfo("分账信息(备用)");
		EmsOrder orderb = new EmsOrder();
		orderb.setOrderGuid("12345678");
		orderb.setReceiveAddr("安徽省合肥市瑶海区");
		orderb.setReceiveDistCode("340102");
		orderb.setReceiveLinker("收件人B");
		orderb.setReceiveLinkMobile("15256635531");
		orderb.setPoliceCode("342974");
//		orderb.setPaymentAccount("20.9");
//		orderb.setWeight("10.8");
//		orderb.setInsuredAccount("60");
//		orderb.setInsuranceAccount("10");
//		orderb.setSplitInfo("分账信息(备用)");
		List<EmsOrder> orders = new ArrayList<EmsOrder>();
		orders.add(order);
		orders.add(orderb);
		params.put("data", orders);
		System.out.println(createNewOrder(JSONUtil.jsonEncode(params)));
		Map<String, String> mail = new HashMap<String, String>();
		mail.put("mailNo", "1144843832109");
		System.out.println(trackMail(JSONUtil.jsonEncode(mail)));
		
	}
	
	private static final String EMS_API_STATUS_ERROR_INFO = "{\"10\": {\"11\": \"\u4ed6\u4eba\u6536\", \"10\": \"\u672c\u4eba\u6536\", \"13\": \"\u672a\u51fa\u53e3\u9000\u56de\u59a5\u6295\", \"12\": \"\u5355\u4f4d\u6536\u53d1\u7ae0\", \"name\": \"\u59a5\u6295\", \"14\": \"\u9000\u56de\u59a5\u6295\"}, \"00\": {\"name\": \"\u6536\u5bc4\"}, \"20\": {\"122\": \"\u67e5\u65e0\u6b64\u5355\u4f4d\", \"126\": \"\u8fc1\u79fb\u65b0\u5740\u4e0d\u660e\", \"127\": \"\u903e\u671f\u672a\u9886\", \"115\": \"\u6536\u4ef6\u4eba\u8981\u6c42\u81ea\u53d6\", \"114\": \"\u56e0\u4e0d\u53ef\u6297\u529b\u539f\u56e0\uff0c\u90ae\u4ef6\u672a\u6295\u51fa\", \"108\": \"\u6536\u5230\u65f6\u7834\u635f\uff0c\u65e0\u6cd5\u6295\u51fa\", \"109\": \"\u7981\u5bc4\u7269\u54c1\", \"111\": \"\u5f85\u6536\u8d39\u540e\", \"110\": \"\u9650\u5bc4\u7269\u54c1\", \"113\": \"\u65e0\u6cd5\u627e\u5230\u6536\u4ef6\u4eba\", \"112\": \"\u65e0\u4eba\u8ba4\u9886\", \"102\": \"\u67e5\u65e0\u6b64\u4eba\", \"103\": \"\u6536\u4ef6\u4eba\u4e0d\u5728\u6307\u5b9a\u5730\u5740\", \"100\": \"\u6b63\u5728\u6295\u9012\u4e2d\", \"101\": \"\u6536\u4ef6\u4eba\u540d\u5740\u6709\u8bef\", \"106\": \"\u8bef\u6295\", \"107\": \"\u90ae\u4ef6\u9519\u53d1\", \"104\": \"\u62d2\u6536\u9000\u56de\", \"105\": \"\u6536\u4ef6\u4eba\u8981\u6c42\u5ef6\u8fdf\u6295\u9012\", \"120\": \"\u5b89\u6392\u6295\u9012\", \"123\": \"\u5730\u5740\u4e0d\u7965\uff0c\u65e0\u7535\u8bdd\uff0c\u7535\u8bdd\u4e0d\u5bf9\", \"name\": \"\u672a\u59a5\u6295\", \"121\": \"\u6b63\u5728\u6295\u9012\", \"119\": \"\u6536\u4ef6\u4eba\u6709\u4fe1\u7bb1\", \"130\": \"\u5176\u5b83\", \"117\": \"\u90ae\u4ef6\u4e22\u5931\", \"118\": \"\u4eba\u5df2\u4ed6\u5f80\", \"116\": \"\u6cd5\u5b9a\u5047\u65e5\uff0c\u65e0\u6cd5\u6295\u9012\", \"129\": \"\u903e\u671f\u6295\u9012\u5458\u6536\u56de\", \"128\": \"\u6295\u9012\u5230\u5305\u88f9\u7ad9\", \"124\": \"\u5730\u5740\u4e0d\u8be6\", \"125\": \"\u64a4\u56de\"}, \"30\": {\"name\": \"\u7ecf\u8f6c\u8fc7\u7a0b\u4e2d\"}, \"51\": {\"name\": \"\u6b63\u5728\u6295\u9012\"}, \"50\": {\"name\": \"\u5b89\u6392\u6295\u9012\"}, \"40\": {\"name\": \"\u79bb\u5f00\u5904\u7406\u4e2d\u5fc3\"}, \"41\": {\"name\": \"\u5230\u8fbe\u5904\u7406\u4e2d\u5fc3\"}}";
	private static final String EMS_API_GOT_ERROR_INFO = "{\"99\": \"\u5176\u4ed6\u9519\u8bef\", \"20\": \"\u9884\u7ea6\u65f6\u95f4\u5c0f\u4e8e\u5f53\u524d\u65f6\u95f4\u6216\u7528\u6237\u53d6\u6d88\u8ba2\u5355\", \"21\": \"\u9650\u5bc4\u7269\u54c1\", \"11\": \"\u65e5\u671f\u683c\u5f0f\u9519\u8bef\", \"13\": \"\u8054\u7cfb\u7535\u8bdd\u4e3a\u7a7a\u6216\u8d85\u957f\", \"12\": \"\u8054\u7cfb\u4eba\u4e3a\u7a7a\u6216\u8d85\u957f\", \"15\": \"\u5bc4\u4ef6-\u5e02\u884c\u653f\u533a\u5212\u4ee3\u7801\u4e3a\u7a7a\u6216\u8d85\u957f\", \"14\": \"\u5bc4\u4ef6-\u7701\u884c\u653f\u533a\u5212\u4ee3\u7801\u4e3a\u7a7a\u6216\u8d85\u957f\", \"17\": \"\u5bc4\u4ef6\u5730\u5740\u4ee3\u7801\u8d85\u957f\", \"16\": \"\u5bc4\u4ef6\u8be6\u7ec6\u5730\u5740\u4e3a\u7a7a\u6216\u8d85\u957f\", \"19\": \"\u5ba2\u6237\u59d3\u540d\u4e3a\u7a7a\u6216\u8d85\u957f\", \"18\": \"\u8ba2\u5355\u5907\u6ce8\u8d85\u957f\"}";
	private static final String EMS_API_GOTDESC_ERROR_INFO = "{\"11\": \"\u5ba2\u6237\u672a\u5907\u9f50\u8d27\u54c1\", \"10\": \"\u6076\u610f\u4e0b\u5355\", \"12\": \"\u56e0\u8d44\u8d39\u539f\u56e0\u653e\u5f03\", \"15\": \"\u7533\u8bf7\u6539\u6d3e\", \"14\": \"\u5ba2\u6237\u8981\u6c42\u5ef6\u65f6\", \"17\": \"\u5df2\u56de\u73ed\u6216\u5176\u4ed6\u539f\u56e0\u65e0\u6cd5\u5904\u7406\u8ba2\u5355\", \"16\": \"\u5df2\u8054\u7cfb\u5ba2\u6237\u5e76\u786e\u8ba4\u91cd\u590d\u4e0b\u5355\", \"19\": \"\u5ba2\u6237\u81ea\u4ea4\u5bc4\", \"18\": \"\u5ba2\u6237\u8981\u6c42\u9884\u7ea6\u63fd\u6536\", \"20\": \"\u5ba2\u6237\u8f6c\u4ea4\u5176\u4ed6\u516c\u53f8\", \"1\": \"\u4e0d\u8be6\u4e14\u65e0\u6cd5\u8054\u7cfb\u5230\u5ba2\u6237\", \"3\": \"\u6d3e\u6bb5\u9519\u8bef\", \"2\": \"\u7535\u8bdd\u6709\u8bef\u4e14\u8be5\u5740\u65e0\u6b64\u4eba\", \"5\": \"\u64a4\u9500\", \"4\": \"\u5ba2\u6237\u4e0d\u5728\u63fd\u6536\u5730\u70b9\", \"7\": \"\u8d85\u8303\u56f4\", \"6\": \"\u5ba2\u6237\u672a\u4e0b\u8ba2\u5355\", \"9\": \"\u8d85\u89c4\u683c\", \"8\": \"\u8d85\u8d85\u7981\u9650\", \"21\": \"\u5176\u4ed6\u539f\u56e0\"}";
	
	/**
	 * 根据action Code 和 msg Code 获取物流描述信息
	 * @param actionCode
	 * @param msgCode 获取action的描述，此字段传值为name
	 * @return
	 */
	public static String getEmsStatusMessage(String actionCode, String msgCode){
		Map<String, Object> msg = JSONUtil.decodeJsonToMap(EMS_API_STATUS_ERROR_INFO);
		if(msg.containsKey(actionCode)){
			Map<String, Object> descMap = (Map<String, Object>) msg.get(actionCode);
			return (String) descMap.get(msgCode);
		}
		
		return null;
	}
	
	public static String getEmsMessage(String statusCode){
		Map<String, Object> msg = JSONUtil.decodeJsonToMap(EMS_API_GOT_ERROR_INFO);
		if(msg.containsKey(statusCode)){
			return (String) msg.get(statusCode);
		}
		
		return null;
	}
	
	public static String getEmsDesc(String descCode){
		Map<String, Object> msg = JSONUtil.decodeJsonToMap(EMS_API_GOTDESC_ERROR_INFO);
		if(msg.containsKey(descCode)){
			return (String) msg.get(descCode);
		}
		
		return null;
	}

	public static String generatorPaySign(String data) {
		// TODO Auto-generated method stub
		Map<String, Object> params = JSONUtil.decodeJsonToMap(data);
		//params.put("app_key", EMS_PAY_APP_KEY);
		String content = getSortParams(params)+EMS_PAY_APP_SECRET;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md52 = md5.digest(content.getBytes());
			return Base64.encodeBase64(md52);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
