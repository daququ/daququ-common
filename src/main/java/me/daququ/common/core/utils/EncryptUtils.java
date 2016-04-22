package me.daququ.common.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @description
 * @version 1.0
 * @author daququ
 * @date 2015年10月27日
 */
public class EncryptUtils {
	
	
	   static final String KEY_SHA = "SHA";     
	      static final String KEY_MD5 = "MD5";   
	      
	      
	      
	      public static final String KEY_ALGORITHM = "RSA";     
	      public static final String SIGNATURE_ALGORITHM = "MD5withRSA";     
	    
	      private static final String PUBLIC_KEY = "RSAPublicKey";     
	      private static final String PRIVATE_KEY = "RSAPrivateKey";     
	    
	      private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
	  			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	  	/**
	  	 * 转换字节数组为16进制字串
	  	 * 
	  	 * @param b
	  	 *            字节数组
	  	 * @return 16进制字串
	  	 */

	  	public static String byteArrayToHexString(byte[] b) {
	  		StringBuffer resultSb = new StringBuffer();
	  		for (int i = 0; i < b.length; i++) {
	  			resultSb.append(byteToHexString(b[i]));
	  		}
	  		return resultSb.toString();
	  	}

	  	private static String byteToHexString(byte b) {
	  		int n = b;
	  		if (n < 0)
	  			n = 256 + n;
	  		int d1 = n / 16;
	  		int d2 = n % 16;
	  		return hexDigits[d1] + hexDigits[d2];
	  	}

	  	public static String MD5Encode(String origin) throws NoSuchAlgorithmException {
	  		String resultString = null;

	  		 
	  			resultString = new String(origin);
	  			MessageDigest md = MessageDigest.getInstance("MD5");
	  			resultString = byteArrayToHexString(md.digest(resultString
	  					.getBytes()));
	  		return resultString;
	  	}
	      
	      
	         
	    
	      
	    
	      
	    
	         
	    
	      
	    
	      
	    
	      
	      
	      public  static class RSA{

	    	  /**  
		       * 校验数字签名  
		       *    
		       * @param data  
		       *            加密数据  
		       * @param publicKey  
		       *            公钥  
		       * @param sign  
		       *            数字签名  
		       *    
		       * @return 校验成功返回true 失败返回false  
		       * @throws Exception  
		       *    
		       */   
		      public static boolean verify(byte[] data, String publicKey, String sign)     
		              throws Exception {     
		    
		          // 解密由base64编码的公钥     
		          byte[] keyBytes = decryptBASE64(publicKey);     
		    
		          // 构造X509EncodedKeySpec对象     
		          X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);     
		    
		          // KEY_ALGORITHM 指定的加密算法     
		          KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);     
		    
		          // 取公钥匙对象     
		          PublicKey pubKey = keyFactory.generatePublic(keySpec);     
		    
		          Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);     
		          signature.initVerify(pubKey);     
		          signature.update(data);     
		    
		          // 验证签名是否正常     
		          return signature.verify(decryptBASE64(sign));     
		      }     
	    	  
	    	  /**  
		       * 用私钥对信息生成数字签名  
		       *    
		       * @param data  
		       *            加密数据  
		       * @param privateKey  
		       *            私钥  
		       *    
		       * @return  
		       * @throws Exception  
		       */   
		      public static String sign(byte[] data, String privateKey) throws Exception {     
		          // 解密由base64编码的私钥     
		          byte[] keyBytes = decryptBASE64(privateKey);     
		    
		          // 构造PKCS8EncodedKeySpec对象     
		          PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);     
		    
		          // KEY_ALGORITHM 指定的加密算法     
		          KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);     
		    
		          // 取私钥匙对象     
		          PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);     
		    
		          // 用私钥对信息生成数字签名     
		          Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);     
		          signature.initSign(priKey);     
		          signature.update(data);     
		    
		          return encryptBASE64(signature.sign());     
		      }  
	    	  
	    	  
	    	  /**  
		       * 加密<br>  
		       * 用私钥加密  
		       *    
		       * @param data  
		       * @param key  
		       * @return  
		       * @throws Exception  
		       */   
		      public static byte[] encryptByPrivateKey(byte[] data, String key)     
		              throws Exception {     
		          // 对密钥解密     
		          byte[] keyBytes = decryptBASE64(key);     
		    
		          // 取得私钥     
		          PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);     
		          KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);     
		          Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);     
		    
		          // 对数据加密     
		          Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());     
		          cipher.init(Cipher.ENCRYPT_MODE, privateKey);     
		    
		          return cipher.doFinal(data);     
		      }     
	    	  
	    	  
	    	  /**  
		       * 解密<br>  
		       * 用私钥解密 h
		       * @param data  
		       * @param key  
		       * @return  
		       * @throws Exception  
		       */   
		      public static byte[] decryptByPrivateKey(byte[] data, String key)     
		              throws Exception {     
		          // 对密钥解密     
		          byte[] keyBytes = decryptBASE64(key);     
		    
		          // 取得私钥     
		          PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);     
		          KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);     
		          Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);     
		    
		          // 对数据解密     
		          Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());     
		          cipher.init(Cipher.DECRYPT_MODE, privateKey);     
		    
		          return cipher.doFinal(data);     
		      }     
		    
		      /** *//**  
		       * 解密<br>  
		       * 用私钥解密  
		       *    
		       * @param data  
		       * @param key  
		       * @return  
		       * @throws Exception  
		       */   
		      public static byte[] decryptByPublicKey(byte[] data, String key)     
		              throws Exception {     
		          // 对密钥解密     
		          byte[] keyBytes = decryptBASE64(key);     
		    
		          // 取得公钥     
		          X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);     
		          KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);     
		          Key publicKey = keyFactory.generatePublic(x509KeySpec);     
		    
		          // 对数据解密     
		          Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());     
		          cipher.init(Cipher.DECRYPT_MODE, publicKey);     
		    
		          return cipher.doFinal(data);     
		      }   
	    	  
	    	 /**  
		       * 加密<br>  
		       * 用公钥加密  
		       *    
		       * @param data  
		       * @param key  
		       * @return  
		       * @throws Exception  
		       */   
		      public static byte[] encryptByPublicKey(byte[] data, String key)     
		              throws Exception {     
		          // 对公钥解密     
		          byte[] keyBytes = decryptBASE64(key);     
		    
		          // 取得公钥     
		          X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);     
		          KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);     
		          Key publicKey = keyFactory.generatePublic(x509KeySpec);     
		    
		          // 对数据加密     
		          Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());     
		          cipher.init(Cipher.ENCRYPT_MODE, publicKey);     
		    
		          return cipher.doFinal(data);     
		      }  
	    	  
	    	  /** *//**  
		       * 取得私钥  
		       *    
		       * @param keyMap  
		       * @return  
		       * @throws Exception  
		       */   
		      public static String getPrivateKey(Map<String, Object> keyMap)     
		              throws Exception {     
		          Key key = (Key) keyMap.get(PRIVATE_KEY);     
		    
		          return encryptBASE64(key.getEncoded());     
		      }     
	    	  
	    	  /** *//**  
		       * 取得公钥  
		       *    
		       * @param keyMap  
		       * @return  
		       * @throws Exception  
		       */   
		      public static String getPublicKey(Map<String, Object> keyMap)     
		              throws Exception {     
		          Key key = (Key) keyMap.get(PUBLIC_KEY);     
		    
		          return encryptBASE64(key.getEncoded());     
		      }     
		      
	    	  
		      /** *//**  
		       * 初始化密钥  
		       *    
		       * @return  
		       * @throws Exception  
		       */   
		      public static Map<String, Object> initKey() throws Exception {     
		          KeyPairGenerator keyPairGen = KeyPairGenerator     
		                  .getInstance(KEY_ALGORITHM);     
		          keyPairGen.initialize(1024);     
		    
		          KeyPair keyPair = keyPairGen.generateKeyPair();     
		    
		          // 公钥     
		          RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();     
		    
		          // 私钥     
		          RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();     
		    
		          Map<String, Object> keyMap = new HashMap<String, Object>(2);     
		    
		          keyMap.put(PUBLIC_KEY, publicKey);     
		          keyMap.put(PRIVATE_KEY, privateKey);     
		          return keyMap;     
		      } 
	      }
    /**   
     * MD5加密   
     *    
     * @param data   
     * @return   
     * @throws Exception   
     */    
    public static byte[] encryptMD5(byte[] data) throws Exception {     
    
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);     
        md5.update(data);     
    
        return md5.digest();     
    
    }     
    
    /**   
     * SHA加密   
     *    
     * @param data   
     * @return   
     * @throws Exception   
     */    
    public static byte[] encryptSHA(byte[] data) throws Exception {     
    
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);     
        sha.update(data);     
    
        return sha.digest();     
    
    }   
	
	/**
	 * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
	 * 
	 * @param strSrc
	 *            要加密的字符串
	 * @param encName
	 *            加密类型
	 * @return
	 */
	public static byte[] Encrypt(String strSrc, String encName) throws NoSuchAlgorithmException {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();

		if (encName == null || encName.equals("")) {
			encName = "SHA-256";
		}
		md = MessageDigest.getInstance(encName);
		md.update(bt);
		return md.digest();
	}

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	public static String HmacSHA256(String key, String data)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
		Mac mac = Mac.getInstance("HmacSHA256");
		byte[] secretByte = key.getBytes("UTF-8");
		byte[] dataBytes = data.getBytes("UTF-8");
		SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");

		mac.init(secret);

		byte[] doFinal = mac.doFinal(dataBytes);
		mac.reset();
		return Base64.encode(doFinal).replaceAll("=*$", "");
	}

	// public static void main(String[] args) {
	// try {
	// String uid ="10000";
	// String base = "0123456789abc";//RandomUtils.getRandomStr(12);
	// String hash = HmacSHA256("rocpeng&leaf&likewed",uid+base);
	//
	//
	//
	//
	// System.err.println(String.format("%s.%s.%s", uid,base,hash).length());
	// } catch (InvalidKeyException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (NoSuchAlgorithmException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

}
