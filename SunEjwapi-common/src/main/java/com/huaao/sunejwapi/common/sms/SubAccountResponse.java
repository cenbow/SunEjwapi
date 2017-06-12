package com.huaao.sunejwapi.common.sms;

import java.io.Serializable;

public class SubAccountResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String statusCode;
	
	public SubAccount SubAccount;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public SubAccount getSubAccount() {
		return SubAccount;
	}

	public void setSubAccount(SubAccount subAccount) {
		SubAccount = subAccount;
	}
}
