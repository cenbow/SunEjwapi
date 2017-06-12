package com.huaao.sunejwapi.common.sms;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class SubAccount implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String voipAccount;
	public String dateCreated;
	public String voipPwd;
	public String subToken;
	public String subAccountSid;
	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("voipAccount", voipAccount);
		json.put("dateCreated", dateCreated);
		json.put("voipPwd", voipPwd);
		json.put("subToken", subToken);
		json.put("subAccountSid", subAccountSid);
		return json.toString();
	}
	public String getVoipAccount() {
		return voipAccount;
	}
	public void setVoipAccount(String voipAccount) {
		this.voipAccount = voipAccount;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getVoipPwd() {
		return voipPwd;
	}
	public void setVoipPwd(String voipPwd) {
		this.voipPwd = voipPwd;
	}
	public String getSubToken() {
		return subToken;
	}
	public void setSubToken(String subToken) {
		this.subToken = subToken;
	}
	public String getSubAccountSid() {
		return subAccountSid;
	}
	public void setSubAccountSid(String subAccountSid) {
		this.subAccountSid = subAccountSid;
	}
}
