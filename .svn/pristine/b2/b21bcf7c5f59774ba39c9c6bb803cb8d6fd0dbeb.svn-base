package com.huaao.sunejwapi.common.security;

import org.apache.commons.lang.StringUtils;

/**
 * Hash证书加密
 * 
 * @author qiyang
 * 
 */
public abstract class HashCredentialsDigest {
	
	public static final int HASH_INTERATIONS = 1024;

	public String digest(String plainCredentials, byte[] salt) {
		if (StringUtils.isBlank(plainCredentials)) {
			return null;
		}
		//如果密码不加密 可直接返回
		//return plainCredentials;
		byte[] hashPassword = digest(Cryptos.utf8encode(plainCredentials), salt);
		return Encodes.encodeHex(hashPassword);
	}

	public boolean matches(String credentials, String plainCredentials, byte[] salt) {
		if (StringUtils.isBlank(credentials) && StringUtils.isBlank(plainCredentials)) {
			return true;
		}
		if(credentials.equals(plainCredentials)){//用明文密码登录
			return true;
		}
		return StringUtils.equals(credentials,digest(plainCredentials, salt));
	}

	protected abstract byte[] digest(byte[] input, byte[] salt);
}
