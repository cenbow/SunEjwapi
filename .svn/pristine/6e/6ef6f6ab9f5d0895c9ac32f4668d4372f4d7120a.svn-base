package com.huaao.sunejwapi.common.security;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt{
	
	  private static final String password = "_(xds_[#%]&34}";
	  
	  public static void main(String[] args){
	    String content = "4028876d5a9360c5015a93718abd0000";
	    
	    System.out.println("加密前" + content);
	    byte[] encryptResult = encrypt(content);
	    String encryptResultStr = parseByte2HexStr(encryptResult);
	    System.out.println("加密后" + encryptResultStr);
	    
	    byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
	    byte[] decryptResult = decrypt(decryptFrom);
	    System.out.println("解密后" + new String(decryptResult));
	  }
	  
	  public static byte[] parseHexStr2Byte(String hexStr)
	  {
	    if (hexStr.length() < 1) {
	      return null;
	    }
	    byte[] result = new byte[hexStr.length() / 2];
	    for (int i = 0; i < hexStr.length() / 2; i++){
	      int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
	      int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
	      result[i] = ((byte)(high * 16 + low));
	    }
	    return result;
	  }
	  
	  public static String parseByte2HexStr(byte[] buf)
	  {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < buf.length; i++){
	      String hex = Integer.toHexString(buf[i] & 0xFF);
	      if (hex.length() == 1) {
	        hex = '0' + hex;
	      }
	      sb.append(hex.toUpperCase());
	    }
	    return sb.toString();
	  }
	  
	  public static byte[] encrypt(String content){
	    try{
	      KeyGenerator kgen = KeyGenerator.getInstance("AES");
	      kgen.init(128, new SecureRandom(password.getBytes()));
	      SecretKey secretKey = kgen.generateKey();
	      byte[] enCodeFormat = secretKey.getEncoded();
	      SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
	      Cipher cipher = Cipher.getInstance("AES");
	      byte[] byteContent = content.getBytes("utf-8");
	      cipher.init(1, key);
	      return cipher.doFinal(byteContent);
	    }catch (NoSuchAlgorithmException e){
	      e.printStackTrace();
	    }catch (NoSuchPaddingException e){
	      e.printStackTrace();
	    }catch (InvalidKeyException e){
	      e.printStackTrace();
	    }catch (UnsupportedEncodingException e){
	      e.printStackTrace();
	    }catch (IllegalBlockSizeException e){
	      e.printStackTrace();
	    }catch (BadPaddingException e){
	      e.printStackTrace();
	    }
	    return null;
	  }
	  
	  public static byte[] decrypt(byte[] content){
	    try
	    {
	      KeyGenerator kgen = KeyGenerator.getInstance("AES");
	      kgen.init(128, new SecureRandom(password.getBytes()));
	      SecretKey secretKey = kgen.generateKey();
	      byte[] enCodeFormat = secretKey.getEncoded();
	      SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
	      Cipher cipher = Cipher.getInstance("AES");
	      cipher.init(2, key);
	      return cipher.doFinal(content);
	    }catch (NoSuchAlgorithmException e){
	      e.printStackTrace();
	    }catch (NoSuchPaddingException e){
	      e.printStackTrace();
	    }catch (InvalidKeyException e){
	      e.printStackTrace();
	    }catch (IllegalBlockSizeException e){
	      e.printStackTrace();
	    }catch (BadPaddingException e){
	      e.printStackTrace();
	    }
	    return null;
	  }
}