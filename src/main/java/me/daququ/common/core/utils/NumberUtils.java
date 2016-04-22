package me.daququ.common.core.utils;

import java.text.DecimalFormat;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils{

	 /**
	  * 超过1万时，进行简化
	  * 1.2万
	  * @param no
	  * @return
	  */
	 public static String beautifulInteger(int no){
		 
		 if(no == 0 ) return "";
		 String ret =  no+"";
		 if(  no>10000){
			 DecimalFormat nf = new DecimalFormat("#.#万");
			 ret = nf.format(no/10000f);
		 }  
		return ret;
	 }
	 
	 
	 /**
	  *浮点数 1.3
	  * @param no
	  * @return
	  */
	 public static String beautifulInteger(float no){
		 String ret =  no+"";
			 DecimalFormat nf = new DecimalFormat("#.#");
			 ret = nf.format(no);
		return ret;
	 }
	
	public static int parseInt(Object val){
		int r = 0;
		try {
			r= Integer.parseInt(String.valueOf(val));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public static long parseLong(Object val){
		long r = 0;
		try {
			r= Long.parseLong(String.valueOf(val));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	  * 判断字符串是否是整数
	  */
	 public static boolean isInteger(String value) {
	  try {
	   Integer.parseInt(value);
	   return true;
	  } catch (NumberFormatException e) {
	   return false;
	  }
	 }

	 /**
	  * 判断字符串是否是浮点数
	  */
	 public static boolean isDouble(String value) {
	  try {
	   Double.parseDouble(value);
	   if (value.contains("."))
	    return true;
	   return false;
	  } catch (NumberFormatException e) {
	   return false;
	  }
	 }

	 /**
	  * 判断字符串是否是数字
	  */
	 public static boolean isNumber(String value) {
	  return isInteger(value) || isDouble(value);
	 }
}
