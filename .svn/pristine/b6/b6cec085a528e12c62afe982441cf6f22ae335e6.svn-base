package com.huaao.sunejwapi.common.util;

import com.huaao.sunejwapi.common.Constants;
import com.huaao.sunejwapi.common.security.Encrypt;

public class TokenUtil {
	
	public static String createToken(String uid){
		StringBuffer buff = new StringBuffer();
		buff.append("id_"+uid);
		buff.append("version_" + Constants.Version);
		byte[] encryptResult = Encrypt.encrypt(buff.toString());
		String encryptResultStr = Encrypt.parseByte2HexStr(encryptResult);
		return encryptResultStr;
	}
}
