package me.daququ.common.core.utils;

import sun.misc.BASE64Decoder;

public class Base64 {
	public static String encode(String s) {
		if (s == null)
			return null;
		return encode(s.getBytes());
	}
	
	public static String encode(byte[] buff){
		if (buff == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(buff);
	}

	public static String decoder(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
}
