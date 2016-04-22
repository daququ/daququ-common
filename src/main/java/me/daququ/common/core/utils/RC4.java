package me.daququ.common.core.utils;

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

public class RC4 {
	private static String algorithm = "RC4";
	public static byte[] encrypt(String toEncrypt, String key) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
	      // create a binary key from the argument key (seed)
	      SecureRandom sr = new SecureRandom(key.getBytes("utf8"));
	      KeyGenerator kg = KeyGenerator.getInstance(algorithm);
	      kg.init(sr);
	      SecretKey sk = kg.generateKey();
	  
	      // create an instance of cipher
	      Cipher cipher = Cipher.getInstance(algorithm);
	  
	      // initialize the cipher with the key
	      cipher.init(Cipher.ENCRYPT_MODE, sk);
	  
	      // enctypt!
	      byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
	  
	      return encrypted;
	   }
	  
	   public static String decrypt(byte[] toDecrypt, String key) throws NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException  {
	      // create a binary key from the argument key (seed)
	      SecureRandom sr = new SecureRandom(key.getBytes());
	      KeyGenerator kg = KeyGenerator.getInstance(algorithm);
	      kg.init(sr);
	      SecretKey sk = kg.generateKey();
	  
	      // do the decryption with that key
	      Cipher cipher = Cipher.getInstance(algorithm);
	      cipher.init(Cipher.DECRYPT_MODE, sk);
	      byte[] decrypted = cipher.doFinal(toDecrypt);
	  
	      return new String(decrypted);
	   }
}