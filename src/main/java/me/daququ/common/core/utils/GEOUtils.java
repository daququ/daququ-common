package me.daququ.common.core.utils;

import java.text.DecimalFormat;

public class GEOUtils {
 
	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为千米米
	 * @param src_wd 原纬度
	 * @param src_jd 原经度
	 * @param dist_wd 目标纬度
	 * @param dist_jd 目标经度
	 * @return
	 */
	 public static double getDistance(double src_wd,double src_jd,double dist_wd,double dist_jd)
	  {
	      double x,y,out;
	      double PI=Math.PI;
	      double R=6.378137*1e6;

	      x=(dist_wd-src_wd)*PI*R*Math.cos( ((src_jd+dist_jd)/2) *PI/180)/180;
	      y=(dist_jd-src_jd)*PI*R/180;
	      out=Math.hypot(x,y);
	      return out/1000;
	  }
	 
	 public static String beautifulDistenace(double src_jd,double src_wd,double dist_jd,double dist_wd,int max_km){
		 double dist = getDistance(src_jd,src_wd,dist_jd,dist_wd);
		 if(dist>max_km){
			 return "";
		 }
		 String ret = dist+"";
		 if( dist>1){
			 DecimalFormat nf = new DecimalFormat("#.#km");
			 ret = nf.format(dist);
		 } else {
			 DecimalFormat nf = new DecimalFormat("###.#");
			 ret = nf.format(dist*1000);
		 }
		return ret;
	 }
	 
	 public static String buildfulDistance(double no,int max_km){
		 if(no>max_km){
			 return "";
		 }
		 String ret = no+"";
		 if( no>1){
			 DecimalFormat nf = new DecimalFormat("#.#km");
			 ret = nf.format(no);
		 } else {
			 DecimalFormat nf = new DecimalFormat("###.#");
			 ret = nf.format(no*1000);
		 }
		 return ret;
	 }
}
