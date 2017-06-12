package com.huaao.sunejwapi.common.web;

import com.huaao.sunejwapi.common.Constants;

public class SystemException extends Exception{
	private static final long serialVersionUID = -2179251416206110262L;
	private String errorCode ;
	
	private SystemException(String mesage){
		super(mesage);
	}
	
	public static SystemException init(String message){
		return init(Constants.API_CODE_SERVER_ERROR,message);
	}
	
	public static SystemException init(String errorCode , String message){
		SystemException se = new SystemException(message);
		se.errorCode = errorCode;
		return se;
	}

	public String getErrorCode() {
		return errorCode;
	}
	
}
