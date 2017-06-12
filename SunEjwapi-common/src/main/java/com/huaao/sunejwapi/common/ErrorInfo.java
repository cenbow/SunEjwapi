package com.huaao.sunejwapi.common;

public class ErrorInfo {

	private Integer errorCode;
	
	private String errorMsg;
	
	public ErrorInfo(int code, String msg){
		this.errorCode = code;
		this.errorMsg = msg;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
}
