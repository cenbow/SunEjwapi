package com.huaao.sunejwapi.common;


public class Constants {
	public static final String Version = "1.0";
	
	public static final String MENU_ID = "menuId";
	
	public static final String CURRENT_USER = "user";
    
    public static final String CACHE_MENU_URL = "menuUrlCache";
    
    public static final String CACHE_API_TOKEN = "apiTokenCache";
    
    public static final String apiUserToken = "token";
    
    public static final String REQUEST_ID = "_reqid";
    
    public static final String ERROR_TRACE = "_errorTrace";
    
    public static final String REQUEST_TIME = "_reqtime";
    
    public static final String CACHE_SMS_CODE = "smsCode";
    
    public static final String userId = "_uid";
    
   
    
    public final static int ROOTLEVEL = 1;
    
    public final static int formErrorCode = 502;
    
    public final static String formErrorMsg = "表单不合法";
    
    public final static String fieldPrefixSys = "sys_";
    public final static String fieldPrefixCus = "cus_";
    public final static String fieldPrefixClob = "clob_";
    
    
    public static final String API_CODE_SUCCESS = "A00000";
    public static final String API_CODE_FAIL = "E50000";
    public static final String API_CODE_TOKEN_INVALID = "E00001";
    public static final String API_CODE_SERVER_ERROR = "E50002";
    public static final String API_CODE_PARAMS_INVALID = "E00003";
    public static final String API_CODE_SQL_GRAMMAR = "E50001";
    
    public static int convertReturnCode(String code){
    	if(API_CODE_SUCCESS.equals(code)){
    		return 0;
    	}
    	if(API_CODE_FAIL.equals(code)){
    		return 500;
    	}
    	if(API_CODE_TOKEN_INVALID.equals(code)){
    		return 10001;
    	}
    	if(API_CODE_SERVER_ERROR.equals(code)){
    		return 10500;
    	}
    	if(API_CODE_PARAMS_INVALID.equals(code)){
    		return 10004;
    	}
    	if(API_CODE_SQL_GRAMMAR.equals(code)){
    		return 10005;
    	}
    	return 0;
    }
}
