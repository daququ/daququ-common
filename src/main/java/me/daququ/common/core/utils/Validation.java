package me.daququ.common.core.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// 网站域名 联系电话 手机号码 邮政编码 邮箱
public class Validation {
	private static Logger logger = LoggerFactory.getLogger(Validation.class);//Logger.getLogger();

	public static final String GEO_PATTER_WITH_EMPTY = "^[0-9]+([.]{1}[0-9]+){0,1}|\\s*$";
	public static final String DATE_PATTER_YYYY_MM_DD_HH_MM_SS = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
	public static final String EMAIL_PATTER = "";
	
	public static final String SCORE_PATTER = "^[0-9]+([.]{1}[0-9]+){0,1}$";
	public static final String PRICE_PATTER = "^[0-9]+([.]{1}[0-9]+){0,1}$";
	
	public static boolean isChinaeseOrEngilistOrNumber(String str){
		Pattern regex = Pattern.compile("[\\-  \u4e00-\u9fa5a-zA-Z0-9]+");
		Matcher matcher = regex.matcher(str);
		return matcher.matches();
	}
 
	/**
	 * 正则验证方法
	 *
	 * @param regexstr
	 * @param str
	 * @return
	 */
	public static boolean Match(String regexstr, String str) {
		Pattern regex = Pattern.compile(regexstr, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher matcher = regex.matcher(str);
		return matcher.matches();
	}


	/**
	 * 邮箱验证,最大程度50
	 *
	 * @param mail
	 * @return
	 */
	public static boolean MatchMail(String mail) {
		if(mail.length()>50)
			return false;
		String mailregex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return Match(mailregex, mail);
	}

	/**
	 * 手机验证
	 *
	 * @param mobile
	 * @return
	 */
	public static boolean MatchMobile(String mobile) {
		String mobileregex = "^(13[4,5,6,7,8,9]|15[0,8,9,1,7]|188|187)\\d{8}$";
		return Match(mobileregex, mobile);
	}

	/**
	 * 电话验证
	 *
	 * @param Tel
	 * @return
	 */
	public static boolean MatchTel(String Tel) {
		String telregex = "(^[0-9]{3,4}-[0-9]{7,8}-[0-9]{3,4}$)|(^[0-9]{3,4}-[0-9]{7,8}$)|(^[0-9]{7,8}-[0-9]{3,4}$)|(^[0-9]{7,15}$)";
		return Match(telregex, Tel);
	}

	public static boolean Webdomain(String webdomain) {
		String webdomainregex = "http://([^/]+)/*";
		return Match(webdomainregex, webdomain);
	}

	public static boolean ZipCode(String zipcode) {
		String zipcoderegex = "^[0-9]{6}$";
		return Match(zipcoderegex, zipcode);
	}
	
	
	public static boolean is_Date_YYYY_MM_DD(String date){
		try {
			Date a = DateUtils.parseDate( date, DateUtils.DATE_PATTERN_YYYY_MM_DD);
			return true;
		} catch (ParseException e) {
		}
		return false;

	}

	public static boolean IdCardNo(String idcard) {
		HashMap<Integer, String> area = new HashMap<Integer, String>();
		area.put(11, "北京");
		area.put(12, "天津");
		area.put(13, "河北");
		area.put(14, "山西");
		area.put(15, "内蒙古");
		area.put(21, "辽宁");
		area.put(22, "吉林");
		area.put(23, "黑龙江");
		area.put(31, "上海");
		area.put(32, "江苏");
		area.put(33, "浙江");
		area.put(34, "安徽");
		area.put(35, "福建");
		area.put(36, "江西");
		area.put(37, "山东");
		area.put(41, "河南");
		area.put(42, "湖北");
		area.put(43, "湖南");
		area.put(44, "广东");
		area.put(45, "广西");
		area.put(46, "海南");
		area.put(50, "重庆");
		area.put(51, "四川");
		area.put(52, "贵州");
		area.put(53, "云南");
		area.put(54, "西藏");
		area.put(61, "陕西");
		area.put(62, "甘肃");
		area.put(63, "青海");
		area.put(64, "宁夏");
		area.put(65, "新疆");
		area.put(71, "台湾");
		area.put(81, "香港");
		area.put(82, "澳门");
		area.put(91, "国外");
		if (StringUtils.isBlank(idcard))
			return false;
		logger.info("身份证所在地："
				+ area.get(Integer.parseInt(idcard.substring(0, 2))));
		if (area.get(Integer.parseInt(idcard.substring(0, 2))) == null)
			return false;
		if (!(idcard.length() == 15 || idcard.length() == 18))
			return false;
		if (idcard.length() == 15) {
			// 老身份证
			int year = Integer.parseInt(idcard.substring(2, 6)) + 1900;
			String ereg;
			if (year % 4 == 0 || (year % 100 == 0 && year % 4 == 0)) {
				ereg = "^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$";// 测试出生日期的合法性
			} else {
				ereg = "^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$";// 测试出生日期的合法性
			}
			if (Match(ereg, idcard))
				return true;
			else
				return false;

		} else if (idcard.length() == 18) {
			// 新省份证
			// 18位身份号码检测
			// 出生日期的合法性检查
			// 闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
			// 平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
			int year = Integer.parseInt(idcard.substring(2, 6)) + 1900;
			String ereg;
			if (year % 4 == 0 || (year % 100 == 0 && year % 4 == 0)) {
				ereg = "^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$";// 闰年出生日期的合法性正则表达式
			} else {
				ereg = "^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$";// 平年出生日期的合法性正则表达式
			}
			if (Match(ereg, idcard)) {// 测试出生日期的合法性
				// 计算校验位
				int[] idcards = new int[18];
				for (int i = 0; i < idcard.length(); i++) {
					idcards[i] = Integer.parseInt(idcard.substring(i, i + 1));
				}
				int S = (idcards[0] + idcards[10]) * 7
						+ (idcards[1] + idcards[11]) * 9
						+ (idcards[2] + idcards[12]) * 10
						+ (idcards[3] + idcards[13]) * 5
						+ (idcards[4] + idcards[14]) * 8
						+ (idcards[5] + idcards[15]) * 4
						+ (idcards[6] + idcards[16]) * 2 + idcards[7] * 1
						+ idcards[8] * 6 + idcards[9] * 3;
				int Y = S % 11;
				String M = "F";
				String JYM = "10X98765432";
				M = JYM.substring(Y, Y + 1);// 判断校验位
				if (StringUtils.equalsIgnoreCase(M, String.valueOf(idcards[17])))
					return true; // 检测ID的校验位
				else
					return false;
			} else
				return false;
		}
		return false;
	}

	//part 2
	public static boolean startCheck(String reg,String string)
    {
        boolean tem=false;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher=pattern.matcher(string);
        tem=matcher.matches();
        return tem;
    }

	/**
     * 检验用户名
     * 取值范围为a-z,A-Z,0-9,"_",汉字
     * 最少一位字符，最大字符位数无限制，不能以"_"结尾
     * */
	public static boolean checkUsername(String username,int min,int max)
    {
		if(username == null) return false;
        String regex="[\\w\u4e00-\u9fa5]{"+min+","+max+"}(?<!_)";
        return startCheck(regex,username);
    }

	/**
     * 验证国内身份证号码：15或18位，由数字组成，不能以0开头
     * */
    public static boolean checkIdCard(String idNr)
    {
        String reg="^[1-9](\\d{14}|\\d{17})";
        return startCheck(reg,idNr);
    }

    /**
     * 验证中文姓名,长度2-4
     * @param name	姓名
     * @return
     * 		true:	正确
     * 		false:	错误
     */
    public static boolean checkChinaName(String name){
    	if(name.length()<2||name.length()>10){
    		return false;
    	}
    	String reg="^[\u4e00-\u9fa5]{1,10}[·.]{0,1}[\u4e00-\u9fa5]{1,10}$";
        return startCheck(reg,name);
    }

    /**
     * 手机号码验证,11位，不知道详细的手机号码段，只是验证开头必须是1和位数
     * */
    public static boolean checkCellPhone(String cellPhoneNr){
        String reg="^[1][\\d]{10}";
        return startCheck(reg,cellPhoneNr);
    }
    /**
     * 判断是不是正常ipv4地址
     * @param ip
     * @return
     */
    public static boolean isIpAddr(String ip){
    	String reg ="^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";
    	return startCheck(reg,ip);
    }
    
    public static boolean isFloat(String fot){
    	boolean r = false;
    	try {
			float f = Float.parseFloat(fot);
			r = true;
		} catch (NumberFormatException e) {
			r = true;
		}
    	return r;
    }
    
//    public final  static String DATA_REG = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";    
    
    
    /**
     * 2014-12-12 true <br />
     * 2014.12.12 false <br />
     * 14-12-12   true <br />
     * 2014-12-12 12:12:13 false  <br />
     * @param date
     * @return
     */
//    public static boolean isDateStr(String date){
//    	 Pattern p = Pattern.compile(DATA_REG);         
//         Matcher m = p.matcher(date);         
//       return  m.matches();
//    }
   
}
