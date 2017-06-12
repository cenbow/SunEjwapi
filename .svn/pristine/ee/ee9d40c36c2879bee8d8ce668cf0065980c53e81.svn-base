package com.huaao.sunejwapi.common.security;

/**
 * SHA1证书加密
 * 
 * @author qiyang
 * 
 */
public class SHA1CredentialsDigest extends HashCredentialsDigest {
	
	@Override
	protected byte[] digest(byte[] input, byte[] salt) {
		return Digests.sha1(input, salt, HASH_INTERATIONS);
	}
}
