package me.daququ.common.core.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	/** 空字符串。 */
	public static final String EMPTY_STRING = "";

	public static final String[] empty = new String[]{};
	
	
	public static boolean isNull(Object obj){
		if(obj ==null || obj.toString().trim() == ""){
			return true;
		} 
		return false;
	}
	
	/**
	 * 删除里面的空
	 * @param chars
	 * @param separator
	 * @return
	 */
	public static String[] toArray(String chars,String separator){
		if(isEmpty(chars)){
			return empty;
		}
		return Lists.newArrayList(Splitter.onPattern(separator).trimResults().split(chars).iterator()).toArray(empty);
	}
	
	/**
	 * 删除里面的空序列
	 * @param chars
	 * @param separator
	 * @return
	 */
	public static List<String> toList(String chars,String separator){
		if(isEmpty(chars)){
			return ImmutableList.of();
		}
		return Lists.newArrayList(Splitter.on(separator).trimResults().split(chars));
	}
	
	/**
	 * 检查字符串是否为<code>null</code>或空字符串<code>""</code>
	 * @param s
	 * @return 如果为空，则返回<code>true</code>
	 */
	public static boolean isEmpty(String s) {
		
		return (s == null) || (s.length() == 0);
	}

	/**
	 * 检查字符串是否为null, 空串，或纯空白字符
	 * @param s
	 * @return
	 */
	public static boolean isBlank(String s) {
		if ((null == s) || (s == "") || (s.length()==0)) {
			return true;
		} else if (s.matches("\\s+")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 如果字符串是<code>null</code>或空白字符，则返回空字符串<code>""</code>，否则返回字符串本身
	 * @param s
	 * @return 字符串本身或空字符串<code>""</code>
	 */
	public static String defaultIfBlank(String s) {
		return isBlank(s) ? EMPTY_STRING : s;
	}

	/**
	 * 如果字符串是<code>null</code>或空白字符，则返回指定默认字符串，否则返回字符串本身
	 * @param str
	 * @param defaultStr
	 * @return 字符串本身或指定的默认字符串
	 */
	public static String defaultIfBlank(String str, String defaultStr) {
		return isBlank(str) ? defaultStr : str;
	}

	/**
	 * 除去字符串头尾部的空白，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。</p>
	 * 
	 * @param str  要处理的字符串
	 * @return 除去空白的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String trim(String str) {
		return trim(str, null, 0);
	}

	/**
	 * 除去字符串头尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * 
	 * @param str  要处理的字符串
	 * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
	 * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String trim(String str, String stripChars) {
		return trim(str, stripChars, 0);
	}

	public static String trimStart(String str) {
		return trim(str, null, -1);
	}

	/**
	 * 除去字符串头部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * 
	 * @param str 要处理的字符串
	 * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
	 * 
	 * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String trimStart(String str, String stripChars) {
		return trim(str, stripChars, -1);
	}

	/**
	 * 除去字符串尾部的空白，如果字符串是<code>null</code>，则返回<code>null</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。 </p>
	 * @param str 要处理的字符串
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回 <code>null</code>
	 */
	public static String trimEnd(String str) {
		return trim(str, null, 1);
	}

	/**
	 * 除去字符串尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * 
	 * @param str 要处理的字符串
	 * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
	 * 
	 * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String trimEnd(String str, String stripChars) {
		return trim(str, stripChars, 1);
	}

	/**
	 * 除去字符串头尾部的空白，如果结果字符串是空字符串<code>""</code>，则返回<code>null</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
	 * </p>
	 * 
	 * @param str  要处理的字符串
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回  <code>null</code>
	 */
	public static String trimToNull(String str) {
		return trimToNull(str, null);
	}

	/**
	 * 除去字符串头尾部的空白，如果结果字符串是空字符串<code>""</code>，则返回<code>null</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
	 * </p>
	 * 
	 * @param str  要处理的字符串
	 * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回 <code>null</code>
	 */
	public static String trimToNull(String str, String stripChars) {
		String result = trim(str, stripChars);

		if ((result == null) || (result.length() == 0)) {
			return null;
		}

		return result;
	}

	/**
	 * 除去字符串头尾部的空白，如果字符串是<code>null</code>，则返回空字符串<code>""</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。</p>
	 * 
	 * @param str 要处理的字符串
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回 <code>null</code>
	 */
	public static String trimToEmpty(String str) {
		return trimToEmpty(str, null);
	}

	/**
	 * 除去字符串头尾部的空白，如果字符串是<code>null</code>，则返回空字符串<code>""</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。 </p>
	 * 
	 * @param str 要处理的字符串
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回 <code>null</code>
	 */
	public static String trimToEmpty(String str, String stripChars) {
		String result = trim(str, stripChars);

		if (result == null) {
			return EMPTY_STRING;
		}
		return result;
	}

	/**
	 * 除去字符串头尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * @param str  要处理的字符串
	 * @param stripChars 要除去的字符，如果为<code>null</code>表示除去空白字符
	 * @param mode
	 *            <code>-1</code>表示trimStart，<code>0</code>表示trim全部，
	 *            <code>1</code>表示trimEnd
	 * 
	 * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	private static String trim(String str, String stripChars, int mode) {
		if (str == null) {
			return null;
		}

		int length = str.length();
		int start = 0;
		int end = length;

		// 扫描字符串头部
		if (mode <= 0) {
			if (stripChars == null) {
				while ((start < end)
						&& (Character.isWhitespace(str.charAt(start)))) {
					start++;
				}
			} else if (stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end)
						&& (stripChars.indexOf(str.charAt(start)) != -1)) {
					start++;
				}
			}
		}

		// 扫描字符串尾部
		if (mode >= 0) {
			if (stripChars == null) {
				while ((start < end)
						&& (Character.isWhitespace(str.charAt(end - 1)))) {
					end--;
				}
			} else if (stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end)
						&& (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
					end--;
				}
			}
		}

		if ((start > 0) || (end < length)) {
			return str.substring(start, end);
		}

		return str;
	}

	/**
	 * 比较两个字符串（大小写敏感）。
	 * 
	 * @param str1   要比较的字符串1
	 * @param str2   要比较的字符串2
	 * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
	 */
	public static boolean equals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equals(str2);
	}

	/**
	 * 比较两个字符串（大小写不敏感）。
	 * 
	 * @param str1   要比较的字符串1
	 * @param str2   要比较的字符串2
	 * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equalsIgnoreCase(str2);
	}

	/**
	 * 判断字符串是否只包含unicode字母。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * @param str 要检查的字符串
	 * @return 如果字符串非<code>null</code>并且全由unicode字母组成，则返回<code>true</code>
	 */
	public static boolean isAlpha(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isLetter(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode字母和空格<code>' '</code>。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * @param str 要检查的字符串
	 * @return 如果字符串非<code>null</code>并且全由unicode字母和空格组成，则返回<code>true</code>
	 */
	public static boolean isAlphaSpace(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isLetter(str.charAt(i)) && (str.charAt(i) != ' ')) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode字母和数字。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * @param str  要检查的字符串
	 * @return 如果字符串非<code>null</code>并且全由unicode字母数字组成，则返回<code>true</code>
	 */
	public static boolean isAlphanumeric(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isLetterOrDigit(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode字母数字和空格<code>' '</code>。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * @param str   要检查的字符串
	 * @return 如果字符串非<code>null</code>并且全由unicode字母数字和空格组成，则返回<code>true</code>
	 */
	public static boolean isAlphanumericSpace(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isLetterOrDigit(str.charAt(i))
					&& (str.charAt(i) != ' ')) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode数字。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * @param str  要检查的字符串
	 * @return 如果字符串非<code>null</code>并且全由unicode数字组成，则返回<code>true</code>
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode数字和空格<code>' '</code>。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isNumericSpace(null)   = false
	 * StringUtil.isNumericSpace(&quot;&quot;)     = true
	 * StringUtil.isNumericSpace(&quot;  &quot;)   = true
	 * StringUtil.isNumericSpace(&quot;123&quot;)  = true
	 * StringUtil.isNumericSpace(&quot;12 3&quot;) = true
	 * StringUtil.isNumericSpace(&quot;ab2c&quot;) = false
	 * StringUtil.isNumericSpace(&quot;12-3&quot;) = false
	 * StringUtil.isNumericSpace(&quot;12.3&quot;) = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果字符串非<code>null</code>并且全由unicode数字和空格组成，则返回<code>true</code>
	 */
	public static boolean isNumericSpace(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isDigit(str.charAt(i)) && (str.charAt(i) != ' ')) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode空白。
	 * 
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isWhitespace(null)   = false
	 * StringUtil.isWhitespace(&quot;&quot;)     = true
	 * StringUtil.isWhitespace(&quot;  &quot;)   = true
	 * StringUtil.isWhitespace(&quot;abc&quot;)  = false
	 * StringUtil.isWhitespace(&quot;ab2c&quot;) = false
	 * StringUtil.isWhitespace(&quot;ab-c&quot;) = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果字符串非<code>null</code>并且全由unicode空白组成，则返回<code>true</code>
	 */
	public static boolean isWhitespace(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 大小写转换。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 将字符串转换成大写。
	 * 
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.toUpperCasing(null)  = null
	 * StringUtil.toUpperCasing(&quot;&quot;)    = &quot;&quot;
	 * StringUtil.toUpperCasing(&quot;aBc&quot;) = &quot;ABC&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String toUpperCasing(String str) {
		if (str == null) {
			return null;
		}
		return str.toUpperCase();
	}

	/**
	 * 将字符串转换成小写。
	 * 
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.toLowerCasing(null)  = null
	 * StringUtil.toLowerCasing(&quot;&quot;)    = &quot;&quot;
	 * StringUtil.toLowerCasing(&quot;aBc&quot;) = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String toLowerCasing(String str) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase();
	}

	/**
	 * 将字符串的首字符转成大写（<code>Character.toTitleCase</code>），其它字符不变。
	 * 
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.capitalize(null)  = null
	 * StringUtil.capitalize(&quot;&quot;)    = &quot;&quot;
	 * StringUtil.capitalize(&quot;cat&quot;) = &quot;Cat&quot;
	 * StringUtil.capitalize(&quot;cAt&quot;) = &quot;CAt&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 首字符为大写的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String capitalize(String str) {
		int strLen;

		if ((str == null) || ((strLen = str.length()) == 0)) {
			return str;
		}

		return new StringBuilder(strLen)
				.append(Character.toTitleCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * 将字符串的首字符转成小写，其它字符不变。
	 * 
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.uncapitalize(null)  = null
	 * StringUtil.uncapitalize(&quot;&quot;)    = &quot;&quot;
	 * StringUtil.uncapitalize(&quot;Cat&quot;) = &quot;cat&quot;
	 * StringUtil.uncapitalize(&quot;CAT&quot;) = &quot;cAT&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 首字符为小写的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String uncapitalize(String str) {
		int strLen;

		if ((str == null) || ((strLen = str.length()) == 0)) {
			return str;
		}

		return new StringBuilder(strLen)
				.append(Character.toLowerCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * 反转字符串的大小写。
	 * 
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.swapCasing(null)                 = null
	 * StringUtil.swapCasing(&quot;&quot;)                   = &quot;&quot;
	 * StringUtil.swapCasing(&quot;The dog has a BONE&quot;) = &quot;tHE DOG HAS A bone&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * 
	 * @return 大小写被反转的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String swapCasing(String str) {
		int strLen;

		if ((str == null) || ((strLen = str.length()) == 0)) {
			return str;
		}

		StringBuilder builder = new StringBuilder(strLen);

		char ch = 0;

		for (int i = 0; i < strLen; i++) {
			ch = str.charAt(i);

			if (Character.isUpperCase(ch)) {
				ch = Character.toLowerCase(ch);
			} else if (Character.isTitleCase(ch)) {
				ch = Character.toLowerCase(ch);
			} else if (Character.isLowerCase(ch)) {
				ch = Character.toUpperCase(ch);
			}

			builder.append(ch);
		}

		return builder.toString();
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 字符串分割函数。 */
	/*                                                                              */
	/* 将字符串按指定分隔符分割。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 将字符串按空白字符分割。
	 * 
	 * <p>
	 * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.split(null)       = null
	 * StringUtil.split(&quot;&quot;)         = []
	 * StringUtil.split(&quot;abc def&quot;)  = [&quot;abc&quot;, &quot;def&quot;]
	 * StringUtil.split(&quot;abc  def&quot;) = [&quot;abc&quot;, &quot;def&quot;]
	 * StringUtil.split(&quot; abc &quot;)    = [&quot;abc&quot;]
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要分割的字符串
	 * 
	 * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String[] split(String str) {
		return split(str, null, -1);
	}

	public static String[] splitExact(String str) {
		return splitExact(str, null, -1);
	}
	/**
	 * 将字符串按指定字符分割。
	 * 
	 * <p>
	 * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.split(null, *)         = null
	 * StringUtil.split(&quot;&quot;, *)           = []
	 * StringUtil.split(&quot;a.b.c&quot;, '.')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
	 * StringUtil.split(&quot;a..b.c&quot;, '.')   = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
	 * StringUtil.split(&quot;a:b:c&quot;, '.')    = [&quot;a:b:c&quot;]
	 * StringUtil.split(&quot;a b c&quot;, ' ')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要分割的字符串
	 * @param separatorChar
	 *            分隔符
	 * @param exact
	 * 			  是否保留空串(即分隔符之间没有其他字符的情况)
	 * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String[] split(String str, char separatorChar, boolean exact) {
		if (str == null) {
			return null;
		}

		int length = str.length();

		if (length == 0) {
			return new String[0];
		}

		List<String> list = new ArrayList<String>();
		int i = 0;
		int start = 0;
		boolean match = false;

		while (i < length) {
			if (str.charAt(i) == separatorChar) {
				if (match) {
					list.add(str.substring(start, i));
					match = false;
				}

				start = ++i;
				continue;
			}

			match = true;
			i++;
		}

		if (match) {
			list.add(str.substring(start, i));
		}

		return list.toArray(new String[list.size()]);
	}

	public static String[] split(String str, char separatorChar) {
		return split(str, separatorChar, false);
	}

	public static String[] splitExact(String str, char separatorChar) {
		return split(str, separatorChar, true);
	}

	/**
	 * 将字符串按指定字符分割。
	 * 
	 * <p>
	 * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.split(null, *)                = null
	 * StringUtil.split(&quot;&quot;, *)                  = []
	 * StringUtil.split(&quot;abc def&quot;, null)        = [&quot;abc&quot;, &quot;def&quot;]
	 * StringUtil.split(&quot;abc def&quot;, &quot; &quot;)         = [&quot;abc&quot;, &quot;def&quot;]
	 * StringUtil.split(&quot;abc  def&quot;, &quot; &quot;)        = [&quot;abc&quot;, &quot;def&quot;]
	 * StringUtil.split(&quot; ab:  cd::ef  &quot;, &quot;:&quot;)  = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
	 * StringUtil.split(&quot;abc.def&quot;, &quot;&quot;)          = [&quot;abc.def&quot;]
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要分割的字符串
	 * @param separatorChars
	 *            分隔符
	 * 
	 * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String[] split(String str, String separatorChars) {
		return split(str, separatorChars, -1);
	}

	public static String[] splitExact(String str, String separatorChars) {
		return splitExact(str, separatorChars, -1);
	}
	/**
	 * 将字符串按指定字符分割。
	 * 
	 * <p>
	 * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.split(null, *, *)                 = null
	 * StringUtil.split(&quot;&quot;, *, *)                   = []
	 * StringUtil.split(&quot;ab cd ef&quot;, null, 0)        = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
	 * StringUtil.split(&quot;  ab   cd ef  &quot;, null, 0)  = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
	 * StringUtil.split(&quot;ab:cd::ef&quot;, &quot;:&quot;, 0)        = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
	 * StringUtil.split(&quot;ab:cd:ef&quot;, &quot;:&quot;, 2)         = [&quot;ab&quot;, &quot;cdef&quot;]
	 * StringUtil.split(&quot;abc.def&quot;, &quot;&quot;, 2)           = [&quot;abc.def&quot;]
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要分割的字符串
	 * @param separatorChars
	 *            分隔符
	 * @param max
	 *            返回的数组的最大个数，如果小于等于0，则表示无限制
	 * @param exact
	 * 			  是否保留空串(即分隔符之间没有其他字符的情况)
	 * 
	 * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String[] split(String str, String separatorChars, int max, boolean exact) {
		if (str == null) {
			return null;
		}

		int length = str.length();

		if (length == 0) {
			return new String[0];
		}

		List<String> list = new ArrayList<String>();
		int sizePlus1 = 1;
		int i = 0;
		int start = 0;
		boolean match = false;

		if (separatorChars == null) {
			// null表示使用空白作为分隔符
			while (i < length) {
				if (Character.isWhitespace(str.charAt(i))) {
					if (exact || match) {
						if (sizePlus1++ == max) {
							i = length;
						}

						list.add(str.substring(start, i));
						match = false;
					}

					start = ++i;
					continue;
				}

				match = true;
				i++;
			}
		} else if (separatorChars.length() == 1) {
			// 优化分隔符长度为1的情形
			char sep = separatorChars.charAt(0);

			while (i < length) {
				if (str.charAt(i) == sep) {
					if (exact || match) {
						if (sizePlus1++ == max) {
							i = length;
						}

						list.add(str.substring(start, i));
						match = false;
					}

					start = ++i;
					continue;
				}

				match = true;
				i++;
			}
		} else {
			// 一般情形
			while (i < length) {
				if (separatorChars.indexOf(str.charAt(i)) >= 0) {
					if (exact || match) {
						if (sizePlus1++ == max) {
							i = length;
						}

						list.add(str.substring(start, i));
						match = false;
					}

					start = ++i;
					continue;
				}

				match = true;
				i++;
			}
		}

		if (exact || match) {
			list.add(str.substring(start, i));
		}

		return list.toArray(new String[list.size()]);
	}

	public static String[] split(String str, String separatorChars, int max) {
		return split(str, separatorChars, max, false);
	}

	public static String[] splitExact(String str, String separatorChars, int max) {
		return split(str, separatorChars, max, true);
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 字符串连接函数。 */
	/*                                                                              */
	/* 将多个对象按指定分隔符连接成字符串。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 将数组中的元素连接成一个字符串。
	 * 
	 * <pre>
	 * StringUtil.join(null)            = null
	 * StringUtil.join([])              = &quot;&quot;
	 * StringUtil.join([null])          = &quot;&quot;
	 * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]) = &quot;abc&quot;
	 * StringUtil.join([null, &quot;&quot;, &quot;a&quot;]) = &quot;a&quot;
	 * </pre>
	 * 
	 * @param array
	 *            要连接的数组
	 * 
	 * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
	 */
	public static String join(Object[] array) {
		return join(array, null);
	}

	/**
	 * 将数组中的元素连接成一个字符串。
	 * 
	 * <pre>
	 * StringUtil.join(null, *)               = null
	 * StringUtil.join([], *)                 = &quot;&quot;
	 * StringUtil.join([null], *)             = &quot;&quot;
	 * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], ';')  = &quot;a;b;c&quot;
	 * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null) = &quot;abc&quot;
	 * StringUtil.join([null, &quot;&quot;, &quot;a&quot;], ';')  = &quot;;;a&quot;
	 * </pre>
	 * 
	 * @param array
	 *            要连接的数组
	 * @param separator
	 *            分隔符
	 * 
	 * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
	 */
	public static String join(Object[] array, char separator) {
		if (array == null) {
			return null;
		}

		int arraySize = array.length;
		int bufSize = (arraySize == 0) ? 0 : ((((array[0] == null) ? 16
				: array[0].toString().length()) + 1) * arraySize);
		StringBuilder buf = new StringBuilder(bufSize);

		for (int i = 0; i < arraySize; i++) {
			if (i > 0) {
				buf.append(separator);
			}

			if (array[i] != null) {
				buf.append(array[i]);
			}
		}

		return buf.toString();
	}

	/**
	 * 将数组中的元素连接成一个字符串。
	 * 
	 * <pre>
	 * StringUtil.join(null, *)                = null
	 * StringUtil.join([], *)                  = &quot;&quot;
	 * StringUtil.join([null], *)              = &quot;&quot;
	 * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;--&quot;)  = &quot;a--b--c&quot;
	 * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null)  = &quot;abc&quot;
	 * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;&quot;)    = &quot;abc&quot;
	 * StringUtil.join([null, &quot;&quot;, &quot;a&quot;], ',')   = &quot;,,a&quot;
	 * </pre>
	 * 
	 * @param array
	 *            要连接的数组
	 * @param separator
	 *            分隔符
	 * 
	 * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
	 */
	public static String join(Object[] array, String separator) {
		if (array == null) {
			return null;
		}

		if (separator == null) {
			separator = EMPTY_STRING;
		}

		int arraySize = array.length;

		// ArraySize == 0: Len = 0
		// ArraySize > 0: Len = NofStrings *(len(firstString) + len(separator))
		// (估计大约所有的字符串都一样长)
		int bufSize = (arraySize == 0) ? 0
				: (arraySize * (((array[0] == null) ? 16 : array[0].toString()
						.length()) + ((separator != null) ? separator.length()
						: 0)));

		StringBuilder buf = new StringBuilder(bufSize);

		for (int i = 0; i < arraySize; i++) {
			if ((separator != null) && (i > 0)) {
				buf.append(separator);
			}

			if (array[i] != null) {
				buf.append(array[i]);
			}
		}

		return buf.toString();
	}

	/**
	 * 将<code>Iterator</code>中的元素连接成一个字符串。
	 * 
	 * <pre>
	 * StringUtil.join(null, *)                = null
	 * StringUtil.join([], *)                  = &quot;&quot;
	 * StringUtil.join([null], *)              = &quot;&quot;
	 * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;--&quot;)  = &quot;a--b--c&quot;
	 * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null)  = &quot;abc&quot;
	 * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;&quot;)    = &quot;abc&quot;
	 * StringUtil.join([null, &quot;&quot;, &quot;a&quot;], ',')   = &quot;,,a&quot;
	 * </pre>
	 * 
	 * @param iterator
	 *            要连接的<code>Iterator</code>
	 * @param separator
	 *            分隔符
	 * 
	 * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
	 */
	public static String join(Iterator<?> iterator, char separator) {
		if (iterator == null) {
			return null;
		}

		StringBuilder buf = new StringBuilder(256); // Java默认值是16, 可能偏小

		while (iterator.hasNext()) {
			Object obj = iterator.next();

			if (obj != null) {
				buf.append(obj);
			}

			if (iterator.hasNext()) {
				buf.append(separator);
			}
		}

		return buf.toString();
	}

	/**
	 * 将<code>Iterator</code>中的元素连接成一个字符串。
	 * 
	 * <pre>
	 * StringUtil.join(null, *)                = null
	 * StringUtil.join([], *)                  = &quot;&quot;
	 * StringUtil.join([null], *)              = &quot;&quot;
	 * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;--&quot;)  = &quot;a--b--c&quot;
	 * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null)  = &quot;abc&quot;
	 * StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;&quot;)    = &quot;abc&quot;
	 * StringUtil.join([null, &quot;&quot;, &quot;a&quot;], ',')   = &quot;,,a&quot;
	 * </pre>
	 * 
	 * @param iterator
	 *            要连接的<code>Iterator</code>
	 * @param separator
	 *            分隔符
	 * 
	 * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
	 */
	public static String join(Iterator<?> iterator, String separator) {
		if (iterator == null) {
			return null;
		}

		StringBuilder buf = new StringBuilder(256); // Java默认值是16, 可能偏小

		while (iterator.hasNext()) {
			Object obj = iterator.next();

			if (obj != null) {
				buf.append(obj);
			}

			if ((separator != null) && iterator.hasNext()) {
				buf.append(separator);
			}
		}

		return buf.toString();
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 字符串查找函数 —— 字符或字符串。 */
	/*                                                                              */
	/* 在字符串中查找指定字符或字符串。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 在字符串中查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *)         = -1
	 * StringUtil.indexOf(&quot;&quot;, *)           = -1
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, 'a') = 0
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b') = 2
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOf(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}

		return str.indexOf(searchChar);
	}

	/**
	 * 在字符串中查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *, *)          = -1
	 * StringUtil.indexOf(&quot;&quot;, *, *)            = -1
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', 0)  = 2
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', 3)  = 5
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', 9)  = -1
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', -1) = 2
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * @param startPos
	 *            开始搜索的索引值，如果小于0，则看作0
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOf(String str, char searchChar, int startPos) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}

		return str.indexOf(searchChar, startPos);
	}

	/**
	 * 在字符串中查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *)          = -1
	 * StringUtil.indexOf(*, null)          = -1
	 * StringUtil.indexOf(&quot;&quot;, &quot;&quot;)           = 0
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;)  = 0
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;)  = 2
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;) = 1
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;&quot;)   = 0
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOf(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}

		return str.indexOf(searchStr);
	}

	/**
	 * 在字符串中查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *, *)          = -1
	 * StringUtil.indexOf(*, null, *)          = -1
	 * StringUtil.indexOf(&quot;&quot;, &quot;&quot;, 0)           = 0
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 0)  = 0
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 0)  = 2
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;, 0) = 1
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 3)  = 5
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 9)  = -1
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, -1) = 2
	 * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;&quot;, 2)   = 2
	 * StringUtil.indexOf(&quot;abc&quot;, &quot;&quot;, 9)        = 3
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * @param startPos
	 *            开始搜索的索引值，如果小于0，则看作0
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOf(String str, String searchStr, int startPos) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}

		// JDK1.3及以下版本的bug：不能正确处理下面的情况
		if ((searchStr.length() == 0) && (startPos >= str.length())) {
			return str.length();
		}

		return str.indexOf(searchStr, startPos);
	}

	/**
	 * 在字符串中查找指定字符串，并返回第num个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
	 * <code>-1</code>。
	 * 
	 * @param num
	 *            出现的次数
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * @return 第num个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOf(int num, String str, String searchStr) {
		int index = 0;
		for (int i = 0; i < num; i++) {
			index = indexOf(str, searchStr, index + 1);
		}
		return index;
	}

	/**
	 * 在字符串中查找指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOfAny(null, *)                = -1
	 * StringUtil.indexOfAny(&quot;&quot;, *)                  = -1
	 * StringUtil.indexOfAny(*, null)                = -1
	 * StringUtil.indexOfAny(*, [])                  = -1
	 * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;,['z','a']) = 0
	 * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;,['b','y']) = 3
	 * StringUtil.indexOfAny(&quot;aba&quot;, ['z'])           = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAny(String str, char[] searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length == 0)) {
			return -1;
		}

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			for (int j = 0; j < searchChars.length; j++) {
				if (searchChars[j] == ch) {
					return i;
				}
			}
		}

		return -1;
	}

	/**
	 * 在字符串中查找指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOfAny(null, *)            = -1
	 * StringUtil.indexOfAny(&quot;&quot;, *)              = -1
	 * StringUtil.indexOfAny(*, null)            = -1
	 * StringUtil.indexOfAny(*, &quot;&quot;)              = -1
	 * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, &quot;za&quot;) = 0
	 * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, &quot;by&quot;) = 3
	 * StringUtil.indexOfAny(&quot;aba&quot;,&quot;z&quot;)          = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAny(String str, String searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length() == 0)) {
			return -1;
		}

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			for (int j = 0; j < searchChars.length(); j++) {
				if (searchChars.charAt(j) == ch) {
					return i;
				}
			}
		}

		return -1;
	}

	/**
	 * 在字符串中查找指定字符串集合中的字符串，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符串集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 如果字符串集合包括<code>""</code>，并且字符串不为<code>null</code>，则返回
	 * <code>str.length()</code>
	 * 
	 * <pre>
	 * StringUtil.indexOfAny(null, *)                     = -1
	 * StringUtil.indexOfAny(*, null)                     = -1
	 * StringUtil.indexOfAny(*, [])                       = -1
	 * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;ab&quot;,&quot;cd&quot;])   = 2
	 * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;cd&quot;,&quot;ab&quot;])   = 2
	 * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;op&quot;])   = -1
	 * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;zab&quot;,&quot;aby&quot;]) = 1
	 * StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;&quot;])          = 0
	 * StringUtil.indexOfAny(&quot;&quot;, [&quot;&quot;])                    = 0
	 * StringUtil.indexOfAny(&quot;&quot;, [&quot;a&quot;])                   = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStrs
	 *            要搜索的字符串集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAny(String str, String[] searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return -1;
		}

		int sz = searchStrs.length;

		// String's can't have a MAX_VALUEth index.
		int ret = Integer.MAX_VALUE;

		int tmp = 0;

		for (int i = 0; i < sz; i++) {
			String search = searchStrs[i];

			if (search == null) {
				continue;
			}

			tmp = str.indexOf(search);

			if (tmp == -1) {
				continue;
			}

			if (tmp < ret) {
				ret = tmp;
			}
		}

		return (ret == Integer.MAX_VALUE) ? (-1) : ret;
	}

	/**
	 * 在字符串中查找不在指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOfAnyBut(null, *)             = -1
	 * StringUtil.indexOfAnyBut(&quot;&quot;, *)               = -1
	 * StringUtil.indexOfAnyBut(*, null)             = -1
	 * StringUtil.indexOfAnyBut(*, [])               = -1
	 * StringUtil.indexOfAnyBut(&quot;zzabyycdxx&quot;,'za')   = 3
	 * StringUtil.indexOfAnyBut(&quot;zzabyycdxx&quot;, 'by')  = 0
	 * StringUtil.indexOfAnyBut(&quot;aba&quot;, 'ab')         = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAnyBut(String str, char[] searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length == 0)) {
			return -1;
		}

		outer: for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			for (int j = 0; j < searchChars.length; j++) {
				if (searchChars[j] == ch) {
					continue outer;
				}
			}

			return i;
		}

		return -1;
	}

	/**
	 * 在字符串中查找不在指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.indexOfAnyBut(null, *)            = -1
	 * StringUtil.indexOfAnyBut(&quot;&quot;, *)              = -1
	 * StringUtil.indexOfAnyBut(*, null)            = -1
	 * StringUtil.indexOfAnyBut(*, &quot;&quot;)              = -1
	 * StringUtil.indexOfAnyBut(&quot;zzabyycdxx&quot;, &quot;za&quot;) = 3
	 * StringUtil.indexOfAnyBut(&quot;zzabyycdxx&quot;, &quot;by&quot;) = 0
	 * StringUtil.indexOfAnyBut(&quot;aba&quot;,&quot;ab&quot;)         = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAnyBut(String str, String searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length() == 0)) {
			return -1;
		}

		for (int i = 0; i < str.length(); i++) {
			if (searchChars.indexOf(str.charAt(i)) < 0) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * 从字符串尾部开始查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
	 * <code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *)         = -1
	 * StringUtil.lastIndexOf(&quot;&quot;, *)           = -1
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'a') = 7
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b') = 5
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOf(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}

		return str.lastIndexOf(searchChar);
	}

	/**
	 * 从字符串尾部开始查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
	 * <code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *, *)          = -1
	 * StringUtil.lastIndexOf(&quot;&quot;, *,  *)           = -1
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', 8)  = 5
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', 4)  = 2
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', 0)  = -1
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', 9)  = 5
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', -1) = -1
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'a', 0)  = 0
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * @param startPos
	 *            从指定索引开始向前搜索
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOf(String str, char searchChar, int startPos) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}

		return str.lastIndexOf(searchChar, startPos);
	}

	/**
	 * 从字符串尾部开始查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
	 * <code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *)         = -1
	 * StringUtil.lastIndexOf(&quot;&quot;, *)           = -1
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'a') = 7
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b') = 5
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOf(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}

		return str.lastIndexOf(searchStr);
	}

	/**
	 * 从字符串尾部开始查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
	 * <code>-1</code>。
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *, *)          = -1
	 * StringUtil.lastIndexOf(*, null, *)          = -1
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 8)  = 7
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 8)  = 5
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;ab&quot;, 8) = 4
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 9)  = 5
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, -1) = -1
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 0)  = 0
	 * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 0)  = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * @param startPos
	 *            从指定索引开始向前搜索
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOf(String str, String searchStr, int startPos) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}

		return str.lastIndexOf(searchStr, startPos);
	}

	/**
	 * 从字符串尾部开始查找指定字符串集合中的字符串，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符串集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 如果字符串集合包括<code>""</code>，并且字符串不为<code>null</code>，则返回
	 * <code>str.length()</code>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOfAny(null, *)                   = -1
	 * StringUtil.lastIndexOfAny(*, null)                   = -1
	 * StringUtil.lastIndexOfAny(*, [])                     = -1
	 * StringUtil.lastIndexOfAny(*, [null])                 = -1
	 * StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;ab&quot;,&quot;cd&quot;]) = 6
	 * StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;cd&quot;,&quot;ab&quot;]) = 6
	 * StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;op&quot;]) = -1
	 * StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;op&quot;]) = -1
	 * StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;&quot;])   = 10
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStrs
	 *            要搜索的字符串集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOfAny(String str, String[] searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return -1;
		}

		int searchStrsLength = searchStrs.length;
		int index = -1;
		int tmp = 0;

		for (int i = 0; i < searchStrsLength; i++) {
			String search = searchStrs[i];

			if (search == null) {
				continue;
			}

			tmp = str.lastIndexOf(search);

			if (tmp > index) {
				index = tmp;
			}
		}

		return index;
	}

	/**
	 * 检查字符串中是否包含指定的字符。如果字符串为<code>null</code>，将返回<code>false</code>。
	 * 
	 * <pre>
	 * StringUtil.contains(null, *)    = false
	 * StringUtil.contains(&quot;&quot;, *)      = false
	 * StringUtil.contains(&quot;abc&quot;, 'a') = true
	 * StringUtil.contains(&quot;abc&quot;, 'z') = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * 
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean contains(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return false;
		}

		return str.indexOf(searchChar) >= 0;
	}

	/**
	 * 检查字符串中是否包含指定的字符串。如果字符串为<code>null</code>，将返回<code>false</code>。
	 * 
	 * <pre>
	 * StringUtil.contains(null, *)     = false
	 * StringUtil.contains(*, null)     = false
	 * StringUtil.contains(&quot;&quot;, &quot;&quot;)      = true
	 * StringUtil.contains(&quot;abc&quot;, &quot;&quot;)   = true
	 * StringUtil.contains(&quot;abc&quot;, &quot;a&quot;)  = true
	 * StringUtil.contains(&quot;abc&quot;, &quot;z&quot;)  = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * 
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean contains(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return false;
		}

		return str.indexOf(searchStr) >= 0;
	}

	/**
	 * 检查字符串是是否只包含指定字符集合中的字符。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>
	 * 则返回<code>false</code>。 但是空字符串永远返回<code>true</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.containsOnly(null, *)       = false
	 * StringUtil.containsOnly(*, null)       = false
	 * StringUtil.containsOnly(&quot;&quot;, *)         = true
	 * StringUtil.containsOnly(&quot;ab&quot;, '')      = false
	 * StringUtil.containsOnly(&quot;abab&quot;, 'abc') = true
	 * StringUtil.containsOnly(&quot;ab1&quot;, 'abc')  = false
	 * StringUtil.containsOnly(&quot;abz&quot;, 'abc')  = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param valid
	 *            要查找的字符串
	 * 
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean containsOnly(String str, char[] valid) {
		if ((valid == null) || (str == null)) {
			return false;
		}

		if (str.length() == 0) {
			return true;
		}

		if (valid.length == 0) {
			return false;
		}

		return indexOfAnyBut(str, valid) == -1;
	}

	/**
	 * 检查字符串是是否只包含指定字符集合中的字符。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>
	 * 则返回<code>false</code>。 但是空字符串永远返回<code>true</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.containsOnly(null, *)       = false
	 * StringUtil.containsOnly(*, null)       = false
	 * StringUtil.containsOnly(&quot;&quot;, *)         = true
	 * StringUtil.containsOnly(&quot;ab&quot;, &quot;&quot;)      = false
	 * StringUtil.containsOnly(&quot;abab&quot;, &quot;abc&quot;) = true
	 * StringUtil.containsOnly(&quot;ab1&quot;, &quot;abc&quot;)  = false
	 * StringUtil.containsOnly(&quot;abz&quot;, &quot;abc&quot;)  = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param valid
	 *            要查找的字符串
	 * 
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean containsOnly(String str, String valid) {
		if ((str == null) || (valid == null)) {
			return false;
		}

		return containsOnly(str, valid.toCharArray());
	}

	/**
	 * 检查字符串是是否不包含指定字符集合中的字符。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>
	 * 则返回<code>true</code>。 但是空字符串永远返回<code>true</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.containsNone(null, *)       = true
	 * StringUtil.containsNone(*, null)       = true
	 * StringUtil.containsNone(&quot;&quot;, *)         = true
	 * StringUtil.containsNone(&quot;ab&quot;, '')      = true
	 * StringUtil.containsNone(&quot;abab&quot;, 'xyz') = true
	 * StringUtil.containsNone(&quot;ab1&quot;, 'xyz')  = true
	 * StringUtil.containsNone(&quot;abz&quot;, 'xyz')  = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param invalid
	 *            要查找的字符串
	 * 
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean containsNone(String str, char[] invalid) {
		if ((str == null) || (invalid == null)) {
			return true;
		}

		int strSize = str.length();
		int validSize = invalid.length;

		for (int i = 0; i < strSize; i++) {
			char ch = str.charAt(i);

			for (int j = 0; j < validSize; j++) {
				if (invalid[j] == ch) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 检查字符串是是否不包含指定字符集合中的字符。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>false</code>。 如果字符集合为<code>null</code>
	 * 则返回<code>true</code>。 但是空字符串永远返回<code>true</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.containsNone(null, *)       = true
	 * StringUtil.containsNone(*, null)       = true
	 * StringUtil.containsNone(&quot;&quot;, *)         = true
	 * StringUtil.containsNone(&quot;ab&quot;, &quot;&quot;)      = true
	 * StringUtil.containsNone(&quot;abab&quot;, &quot;xyz&quot;) = true
	 * StringUtil.containsNone(&quot;ab1&quot;, &quot;xyz&quot;)  = true
	 * StringUtil.containsNone(&quot;abz&quot;, &quot;xyz&quot;)  = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param invalidChars
	 *            要查找的字符串
	 * 
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean containsNone(String str, String invalidChars) {
		if ((str == null) || (invalidChars == null)) {
			return true;
		}

		return containsNone(str, invalidChars.toCharArray());
	}

	/**
	 * 取得指定子串在字符串中出现的次数。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>或空，则返回<code>0</code>。
	 * 
	 * <pre>
	 * StringUtil.countMatches(null, *)       = 0
	 * StringUtil.countMatches(&quot;&quot;, *)         = 0
	 * StringUtil.countMatches(&quot;abba&quot;, null)  = 0
	 * StringUtil.countMatches(&quot;abba&quot;, &quot;&quot;)    = 0
	 * StringUtil.countMatches(&quot;abba&quot;, &quot;a&quot;)   = 2
	 * StringUtil.countMatches(&quot;abba&quot;, &quot;ab&quot;)  = 1
	 * StringUtil.countMatches(&quot;abba&quot;, &quot;xxx&quot;) = 0
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param subStr
	 *            子字符串
	 * 
	 * @return 子串在字符串中出现的次数，如果字符串为<code>null</code>或空，则返回<code>0</code>
	 */
	public static int countMatches(String str, String subStr) {
		if ((str == null) || (str.length() == 0) || (subStr == null)
				|| (subStr.length() == 0)) {
			return 0;
		}

		int count = 0;
		int index = 0;

		while ((index = str.indexOf(subStr, index)) != -1) {
			count++;
			index += subStr.length();
		}

		return count;
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 取子串函数。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 取指定字符串的子串。
	 * 
	 * <p>
	 * 负的索引代表从尾部开始计算。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.substring(null, *)   = null
	 * StringUtil.substring(&quot;&quot;, *)     = &quot;&quot;
	 * StringUtil.substring(&quot;abc&quot;, 0)  = &quot;abc&quot;
	 * StringUtil.substring(&quot;abc&quot;, 2)  = &quot;c&quot;
	 * StringUtil.substring(&quot;abc&quot;, 4)  = &quot;&quot;
	 * StringUtil.substring(&quot;abc&quot;, -2) = &quot;bc&quot;
	 * StringUtil.substring(&quot;abc&quot;, -4) = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param start
	 *            起始索引，如果为负数，表示从尾部查找
	 * 
	 * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
	 */
	public static String substring(String str, int start) {
		if (str == null) {
			return null;
		}

		if (start < 0) {
			start = str.length() + start;
		}

		if (start < 0) {
			start = 0;
		}

		if (start > str.length()) {
			return EMPTY_STRING;
		}

		return str.substring(start);
	}

	/**
	 * 取指定字符串的子串。
	 * 
	 * <p>
	 * 负的索引代表从尾部开始计算。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.substring(null, *, *)    = null
	 * StringUtil.substring(&quot;&quot;, * ,  *)    = &quot;&quot;;
	 * StringUtil.substring(&quot;abc&quot;, 0, 2)   = &quot;ab&quot;
	 * StringUtil.substring(&quot;abc&quot;, 2, 0)   = &quot;&quot;
	 * StringUtil.substring(&quot;abc&quot;, 2, 4)   = &quot;c&quot;
	 * StringUtil.substring(&quot;abc&quot;, 4, 6)   = &quot;&quot;
	 * StringUtil.substring(&quot;abc&quot;, 2, 2)   = &quot;&quot;
	 * StringUtil.substring(&quot;abc&quot;, -2, -1) = &quot;b&quot;
	 * StringUtil.substring(&quot;abc&quot;, -4, 2)  = &quot;ab&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param start
	 *            起始索引，如果为负数，表示从尾部计算
	 * @param end
	 *            结束索引（不含），如果为负数，表示从尾部计算
	 * 
	 * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
	 */
	public static String substring(String str, int start, int end) {
		if (str == null) {
			return null;
		}

		if (end < 0) {
			end = str.length() + end;
		}

		if (start < 0) {
			start = str.length() + start;
		}

		if (end > str.length()) {
			end = str.length();
		}

		if (start > end) {
			return EMPTY_STRING;
		}

		if (start < 0) {
			start = 0;
		}

		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}

	/**
	 * 取得长度为指定字符数的最左边的子串。
	 * 
	 * <pre>
	 * StringUtil.left(null, *)    = null
	 * StringUtil.left(*, -ve)     = &quot;&quot;
	 * StringUtil.left(&quot;&quot;, *)      = &quot;&quot;
	 * StringUtil.left(&quot;abc&quot;, 0)   = &quot;&quot;
	 * StringUtil.left(&quot;abc&quot;, 2)   = &quot;ab&quot;
	 * StringUtil.left(&quot;abc&quot;, 4)   = &quot;abc&quot;
	 * </pre>
	 * 
	 * @param str
	 *            字符串
	 * @param len
	 *            最左子串的长度
	 * 
	 * @return 子串，如果原始字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String left(String str, int len) {
		if (str == null) {
			return null;
		}

		if (len < 0) {
			return EMPTY_STRING;
		}

		if (str.length() <= len) {
			return str;
		} else {
			return str.substring(0, len);
		}
	}

	/**
	 * 取得长度为指定字符数的最右边的子串。
	 * 
	 * <pre>
	 * StringUtil.right(null, *)    = null
	 * StringUtil.right(*, -ve)     = &quot;&quot;
	 * StringUtil.right(&quot;&quot;, *)      = &quot;&quot;
	 * StringUtil.right(&quot;abc&quot;, 0)   = &quot;&quot;
	 * StringUtil.right(&quot;abc&quot;, 2)   = &quot;bc&quot;
	 * StringUtil.right(&quot;abc&quot;, 4)   = &quot;abc&quot;
	 * </pre>
	 * 
	 * @param str
	 *            字符串
	 * @param len
	 *            最右子串的长度
	 * 
	 * @return 子串，如果原始字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String right(String str, int len) {
		if (str == null) {
			return null;
		}

		if (len < 0) {
			return EMPTY_STRING;
		}

		if (str.length() <= len) {
			return str;
		} else {
			return str.substring(str.length() - len);
		}
	}

	/**
	 * 取得从指定索引开始计算的、长度为指定字符数的子串。
	 * 
	 * <pre>
	 * StringUtil.mid(null, *, *)    = null
	 * StringUtil.mid(*, *, -ve)     = &quot;&quot;
	 * StringUtil.mid(&quot;&quot;, 0, *)      = &quot;&quot;
	 * StringUtil.mid(&quot;abc&quot;, 0, 2)   = &quot;ab&quot;
	 * StringUtil.mid(&quot;abc&quot;, 0, 4)   = &quot;abc&quot;
	 * StringUtil.mid(&quot;abc&quot;, 2, 4)   = &quot;c&quot;
	 * StringUtil.mid(&quot;abc&quot;, 4, 2)   = &quot;&quot;
	 * StringUtil.mid(&quot;abc&quot;, -2, 2)  = &quot;ab&quot;
	 * </pre>
	 * 
	 * @param str
	 *            字符串
	 * @param pos
	 *            起始索引，如果为负数，则看作<code>0</code>
	 * @param len
	 *            子串的长度，如果为负数，则看作长度为<code>0</code>
	 * 
	 * @return 子串，如果原始字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String mid(String str, int pos, int len) {
		if (str == null) {
			return null;
		}

		if ((len < 0) || (pos > str.length())) {
			return EMPTY_STRING;
		}

		if (pos < 0) {
			pos = 0;
		}

		if (str.length() <= (pos + len)) {
			return str.substring(pos);
		} else {
			return str.substring(pos, pos + len);
		}
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 搜索并取子串函数。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 取得第一个出现的分隔子串之前的子串。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * 或未找到该子串，则返回原字符串。
	 * 
	 * <pre>
	 * StringUtil.substringBefore(null, *)      = null
	 * StringUtil.substringBefore(&quot;&quot;, *)        = &quot;&quot;
	 * StringUtil.substringBefore(&quot;abc&quot;, &quot;a&quot;)   = &quot;&quot;
	 * StringUtil.substringBefore(&quot;abcba&quot;, &quot;b&quot;) = &quot;a&quot;
	 * StringUtil.substringBefore(&quot;abc&quot;, &quot;c&quot;)   = &quot;ab&quot;
	 * StringUtil.substringBefore(&quot;abc&quot;, &quot;d&quot;)   = &quot;abc&quot;
	 * StringUtil.substringBefore(&quot;abc&quot;, &quot;&quot;)    = &quot;&quot;
	 * StringUtil.substringBefore(&quot;abc&quot;, null)  = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param separator
	 *            要搜索的分隔子串
	 * 
	 * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
	 */
	public static String substringBefore(String str, String separator) {
		if ((str == null) || (separator == null) || (str.length() == 0)) {
			return str;
		}

		if (separator.length() == 0) {
			return EMPTY_STRING;
		}

		int pos = str.indexOf(separator);

		if (pos == -1) {
			return str;
		}

		return str.substring(0, pos);
	}

	/**
	 * 取得第一个出现的分隔子串之后的子串。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * 或未找到该子串，则返回原字符串。
	 * 
	 * <pre>
	 * StringUtil.substringAfter(null, *)      = null
	 * StringUtil.substringAfter(&quot;&quot;, *)        = &quot;&quot;
	 * StringUtil.substringAfter(*, null)      = &quot;&quot;
	 * StringUtil.substringAfter(&quot;abc&quot;, &quot;a&quot;)   = &quot;bc&quot;
	 * StringUtil.substringAfter(&quot;abcba&quot;, &quot;b&quot;) = &quot;cba&quot;
	 * StringUtil.substringAfter(&quot;abc&quot;, &quot;c&quot;)   = &quot;&quot;
	 * StringUtil.substringAfter(&quot;abc&quot;, &quot;d&quot;)   = &quot;&quot;
	 * StringUtil.substringAfter(&quot;abc&quot;, &quot;&quot;)    = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param separator
	 *            要搜索的分隔子串
	 * 
	 * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
	 */
	public static String substringAfter(String str, String separator) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}

		if (separator == null) {
			return EMPTY_STRING;
		}

		int pos = str.indexOf(separator);

		if (pos == -1) {
			return EMPTY_STRING;
		}

		return str.substring(pos + separator.length());
	}

	/**
	 * 取得最后一个的分隔子串之前的子串。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * 或未找到该子串，则返回原字符串。
	 * 
	 * <pre>
	 * StringUtil.substringBeforeLast(null, *)      = null
	 * StringUtil.substringBeforeLast(&quot;&quot;, *)        = &quot;&quot;
	 * StringUtil.substringBeforeLast(&quot;abcba&quot;, &quot;b&quot;) = &quot;abc&quot;
	 * StringUtil.substringBeforeLast(&quot;abc&quot;, &quot;c&quot;)   = &quot;ab&quot;
	 * StringUtil.substringBeforeLast(&quot;a&quot;, &quot;a&quot;)     = &quot;&quot;
	 * StringUtil.substringBeforeLast(&quot;a&quot;, &quot;z&quot;)     = &quot;a&quot;
	 * StringUtil.substringBeforeLast(&quot;a&quot;, null)    = &quot;a&quot;
	 * StringUtil.substringBeforeLast(&quot;a&quot;, &quot;&quot;)      = &quot;a&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param separator
	 *            要搜索的分隔子串
	 * 
	 * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
	 */
	public static String substringBeforeLast(String str, String separator) {
		if ((str == null) || (separator == null) || (str.length() == 0)
				|| (separator.length() == 0)) {
			return str;
		}

		int pos = str.lastIndexOf(separator);

		if (pos == -1) {
			return str;
		}

		return str.substring(0, pos);
	}

	/**
	 * 取得最后一个的分隔子串之后的子串。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * 或未找到该子串，则返回原字符串。
	 * 
	 * <pre>
	 * StringUtil.substringAfterLast(null, *)      = null
	 * StringUtil.substringAfterLast(&quot;&quot;, *)        = &quot;&quot;
	 * StringUtil.substringAfterLast(*, &quot;&quot;)        = &quot;&quot;
	 * StringUtil.substringAfterLast(*, null)      = &quot;&quot;
	 * StringUtil.substringAfterLast(&quot;abc&quot;, &quot;a&quot;)   = &quot;bc&quot;
	 * StringUtil.substringAfterLast(&quot;abcba&quot;, &quot;b&quot;) = &quot;a&quot;
	 * StringUtil.substringAfterLast(&quot;abc&quot;, &quot;c&quot;)   = &quot;&quot;
	 * StringUtil.substringAfterLast(&quot;a&quot;, &quot;a&quot;)     = &quot;&quot;
	 * StringUtil.substringAfterLast(&quot;a&quot;, &quot;z&quot;)     = &quot;&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param separator
	 *            要搜索的分隔子串
	 * 
	 * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
	 */
	public static String substringAfterLast(String str, String separator) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}

		if ((separator == null) || (separator.length() == 0)) {
			return EMPTY_STRING;
		}

		int pos = str.lastIndexOf(separator);

		if ((pos == -1) || (pos == (str.length() - separator.length()))) {
			return EMPTY_STRING;
		}

		return str.substring(pos + separator.length());
	}

	/**
	 * 取得指定分隔符的前两次出现之间的子串。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * ，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.substringBetween(null, *)            = null
	 * StringUtil.substringBetween(&quot;&quot;, &quot;&quot;)             = &quot;&quot;
	 * StringUtil.substringBetween(&quot;&quot;, &quot;tag&quot;)          = null
	 * StringUtil.substringBetween(&quot;tagabctag&quot;, null)  = null
	 * StringUtil.substringBetween(&quot;tagabctag&quot;, &quot;&quot;)    = &quot;&quot;
	 * StringUtil.substringBetween(&quot;tagabctag&quot;, &quot;tag&quot;) = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param tag
	 *            要搜索的分隔子串
	 * 
	 * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
	 */
	public static String substringBetween(String str, String tag) {
		return substringBetween(str, tag, tag, 0);
	}

	/**
	 * 取得两个分隔符之间的子串。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * ，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.substringBetween(null, *, *)          = null
	 * StringUtil.substringBetween(&quot;&quot;, &quot;&quot;, &quot;&quot;)          = &quot;&quot;
	 * StringUtil.substringBetween(&quot;&quot;, &quot;&quot;, &quot;tag&quot;)       = null
	 * StringUtil.substringBetween(&quot;&quot;, &quot;tag&quot;, &quot;tag&quot;)    = null
	 * StringUtil.substringBetween(&quot;yabcz&quot;, null, null) = null
	 * StringUtil.substringBetween(&quot;yabcz&quot;, &quot;&quot;, &quot;&quot;)     = &quot;&quot;
	 * StringUtil.substringBetween(&quot;yabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
	 * StringUtil.substringBetween(&quot;yabczyabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param open
	 *            要搜索的分隔子串1
	 * @param close
	 *            要搜索的分隔子串2
	 * 
	 * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
	 */
	public static String substringBetween(String str, String open, String close) {
		return substringBetween(str, open, close, 0);
	}

	/**
	 * 取得两个分隔符之间的子串。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * ，则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.substringBetween(null, *, *)          = null
	 * StringUtil.substringBetween(&quot;&quot;, &quot;&quot;, &quot;&quot;)          = &quot;&quot;
	 * StringUtil.substringBetween(&quot;&quot;, &quot;&quot;, &quot;tag&quot;)       = null
	 * StringUtil.substringBetween(&quot;&quot;, &quot;tag&quot;, &quot;tag&quot;)    = null
	 * StringUtil.substringBetween(&quot;yabcz&quot;, null, null) = null
	 * StringUtil.substringBetween(&quot;yabcz&quot;, &quot;&quot;, &quot;&quot;)     = &quot;&quot;
	 * StringUtil.substringBetween(&quot;yabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
	 * StringUtil.substringBetween(&quot;yabczyabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param open
	 *            要搜索的分隔子串1
	 * @param close
	 *            要搜索的分隔子串2
	 * @param fromIndex
	 *            从指定index处搜索
	 * 
	 * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>str</code>
	 */
	public static String substringBetween(String str, String open,
			String close, int fromIndex) {
		if ((str == null) || (open == null) || (close == null)) {
			return null;
		}

		int start = str.indexOf(open, fromIndex);

		if (start != -1) {
			int end = str.indexOf(close, start + open.length());

			if (end != -1) {
				return str.substring(start + open.length(), end);
			}
		}

		return str;
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 删除字符。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 删除所有在<code>Character.isWhitespace(char)</code>中所定义的空白。
	 * 
	 * <pre>
	 * StringUtil.deleteWhitespace(null)         = null
	 * StringUtil.deleteWhitespace(&quot;&quot;)           = &quot;&quot;
	 * StringUtil.deleteWhitespace(&quot;abc&quot;)        = &quot;abc&quot;
	 * StringUtil.deleteWhitespace(&quot;   ab  c  &quot;) = &quot;abc&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 去空白后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String deleteWhitespace(String str) {
		if (str == null) {
			return null;
		}

		int sz = str.length();
		StringBuilder builder = new StringBuilder(sz);

		for (int i = 0; i < sz; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				builder.append(str.charAt(i));
			}
		}

		return builder.toString();
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 替换子串。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 替换指定的子串，只替换第一个出现的子串。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
	 * ，则返回原字符串。
	 * 
	 * <pre>
	 * StringUtil.replaceOnce(null, *, *)        = null
	 * StringUtil.replaceOnce(&quot;&quot;, *, *)          = &quot;&quot;
	 * StringUtil.replaceOnce(&quot;aba&quot;, null, null) = &quot;aba&quot;
	 * StringUtil.replaceOnce(&quot;aba&quot;, null, null) = &quot;aba&quot;
	 * StringUtil.replaceOnce(&quot;aba&quot;, &quot;a&quot;, null)  = &quot;aba&quot;
	 * StringUtil.replaceOnce(&quot;aba&quot;, &quot;a&quot;, &quot;&quot;)    = &quot;ba&quot;
	 * StringUtil.replaceOnce(&quot;aba&quot;, &quot;a&quot;, &quot;z&quot;)   = &quot;zba&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param text
	 *            要扫描的字符串
	 * @param repl
	 *            要搜索的子串
	 * @param with
	 *            替换字符串
	 * 
	 * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String replaceOnce(String text, String repl, String with) {
		return replace(text, repl, with, 1);
	}

	/**
	 * 替换指定的子串，替换所有出现的子串。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
	 * ，则返回原字符串。
	 * 
	 * <pre>
	 * StringUtil.replace(null, *, *)        = null
	 * StringUtil.replace(&quot;&quot;, *, *)          = &quot;&quot;
	 * StringUtil.replace(&quot;aba&quot;, null, null) = &quot;aba&quot;
	 * StringUtil.replace(&quot;aba&quot;, null, null) = &quot;aba&quot;
	 * StringUtil.replace(&quot;aba&quot;, &quot;a&quot;, null)  = &quot;aba&quot;
	 * StringUtil.replace(&quot;aba&quot;, &quot;a&quot;, &quot;&quot;)    = &quot;b&quot;
	 * StringUtil.replace(&quot;aba&quot;, &quot;a&quot;, &quot;z&quot;)   = &quot;zbz&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param text
	 *            要扫描的字符串
	 * @param repl
	 *            要搜索的子串
	 * @param with
	 *            替换字符串
	 * 
	 * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String replace(String text, String repl, String with) {
		return replace(text, repl, with, -1);
	}

	/**
	 * 替换指定的子串，替换指定的次数。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
	 * ，则返回原字符串。
	 * 
	 * <pre>
	 * StringUtil.replace(null, *, *, *)         = null
	 * StringUtil.replace(&quot;&quot;, *, *, *)           = &quot;&quot;
	 * StringUtil.replace(&quot;abaa&quot;, null, null, 1) = &quot;abaa&quot;
	 * StringUtil.replace(&quot;abaa&quot;, null, null, 1) = &quot;abaa&quot;
	 * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, null, 1)  = &quot;abaa&quot;
	 * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;&quot;, 1)    = &quot;baa&quot;
	 * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 0)   = &quot;abaa&quot;
	 * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 1)   = &quot;zbaa&quot;
	 * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 2)   = &quot;zbza&quot;
	 * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, -1)  = &quot;zbzz&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param text
	 *            要扫描的字符串
	 * @param repl
	 *            要搜索的子串
	 * @param with
	 *            替换字符串
	 * @param max
	 *            maximum number of values to replace, or <code>-1</code> if no
	 *            maximum
	 * 
	 * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String replace(String text, String repl, String with, int max) {
		if ((text == null) || (repl == null) || (with == null)
				|| (repl.length() == 0) || (max == 0)) {
			return text;
		}

		StringBuilder buf = new StringBuilder(text.length());
		int start = 0;
		int end = 0;

		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();

			if (--max == 0) {
				break;
			}
		}

		buf.append(text.substring(start));
		return buf.toString();
	}

	/**
	 * 替换指定的子串，只替换第一个出现的子串。
	 * 
	 * @param startPos
	 *            开始搜索的索引值，如果小于0，则看作0
	 * @param text
	 *            要扫描的字符串
	 * @param repl
	 *            要搜索的子串
	 * @param with
	 *            替换字符串
	 * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String replaceOnce(int startPos, String text, String repl,
			String with) {
		String target = StringUtils.substring(text, startPos);
		String before = StringUtils.substring(text, 0, startPos);
		String after = StringUtils.replaceOnce(target, repl, with);
		return (before + after);
	}

	/**
	 * 将字符串中所有指定的字符，替换成另一个。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 * StringUtil.replaceChars(null, *, *)        = null
	 * StringUtil.replaceChars(&quot;&quot;, *, *)          = &quot;&quot;
	 * StringUtil.replaceChars(&quot;abcba&quot;, 'b', 'y') = &quot;aycya&quot;
	 * StringUtil.replaceChars(&quot;abcba&quot;, 'z', 'y') = &quot;abcba&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要搜索的字符
	 * @param replaceChar
	 *            替换字符
	 * 
	 * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String replaceChars(String str, char searchChar,
			char replaceChar) {
		if (str == null) {
			return null;
		}

		return str.replace(searchChar, replaceChar);
	}

	/**
	 * 将字符串中所有指定的字符，替换成另一个。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>则返回<code>null</code>。如果搜索字符串为<code>null</code>
	 * 或空，则返回原字符串。
	 * </p>
	 * 
	 * <p>
	 * 例如：
	 * <code>replaceChars(&quot;hello&quot;, &quot;ho&quot;, &quot;jy&quot;) = jelly</code>
	 * 。
	 * </p>
	 * 
	 * <p>
	 * 通常搜索字符串和替换字符串是等长的，如果搜索字符串比替换字符串长，则多余的字符将被删除。 如果搜索字符串比替换字符串短，则缺少的字符将被忽略。
	 * 
	 * <pre>
	 * StringUtil.replaceChars(null, *, *)           = null
	 * StringUtil.replaceChars(&quot;&quot;, *, *)             = &quot;&quot;
	 * StringUtil.replaceChars(&quot;abc&quot;, null, *)       = &quot;abc&quot;
	 * StringUtil.replaceChars(&quot;abc&quot;, &quot;&quot;, *)         = &quot;abc&quot;
	 * StringUtil.replaceChars(&quot;abc&quot;, &quot;b&quot;, null)     = &quot;ac&quot;
	 * StringUtil.replaceChars(&quot;abc&quot;, &quot;b&quot;, &quot;&quot;)       = &quot;ac&quot;
	 * StringUtil.replaceChars(&quot;abcba&quot;, &quot;bc&quot;, &quot;yz&quot;)  = &quot;ayzya&quot;
	 * StringUtil.replaceChars(&quot;abcba&quot;, &quot;bc&quot;, &quot;y&quot;)   = &quot;ayya&quot;
	 * StringUtil.replaceChars(&quot;abcba&quot;, &quot;bc&quot;, &quot;yzx&quot;) = &quot;ayzya&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符串
	 * @param replaceChars
	 *            替换字符串
	 * 
	 * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String replaceChars(String str, String searchChars,
			String replaceChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length() == 0)) {
			return str;
		}

		char[] chars = str.toCharArray();
		int len = chars.length;
		boolean modified = false;

		for (int i = 0, isize = searchChars.length(); i < isize; i++) {
			char searchChar = searchChars.charAt(i);

			if ((replaceChars == null) || (i >= replaceChars.length())) {
				// 删除
				int pos = 0;

				for (int j = 0; j < len; j++) {
					if (chars[j] != searchChar) {
						chars[pos++] = chars[j];
					} else {
						modified = true;
					}
				}

				len = pos;
			} else {
				// 替换
				for (int j = 0; j < len; j++) {
					if (chars[j] == searchChar) {
						chars[j] = replaceChars.charAt(i);
						modified = true;
					}
				}
			}
		}

		if (!modified) {
			return str;
		}

		return new String(chars, 0, len);
	}

	/**
	 * 将指定的子串用另一指定子串覆盖。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 负的索引值将被看作<code>0</code>
	 * ，越界的索引值将被设置成字符串的长度相同的值。
	 * 
	 * <pre>
	 * StringUtil.overlay(null, *, *, *)            = null
	 * StringUtil.overlay(&quot;&quot;, &quot;abc&quot;, 0, 0)          = &quot;abc&quot;
	 * StringUtil.overlay(&quot;abcdef&quot;, null, 2, 4)     = &quot;abef&quot;
	 * StringUtil.overlay(&quot;abcdef&quot;, &quot;&quot;, 2, 4)       = &quot;abef&quot;
	 * StringUtil.overlay(&quot;abcdef&quot;, &quot;&quot;, 4, 2)       = &quot;abef&quot;
	 * StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 2, 4)   = &quot;abzzzzef&quot;
	 * StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 4, 2)   = &quot;abzzzzef&quot;
	 * StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, -1, 4)  = &quot;zzzzef&quot;
	 * StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 2, 8)   = &quot;abzzzz&quot;
	 * StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, -2, -3) = &quot;zzzzabcdef&quot;
	 * StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 8, 10)  = &quot;abcdefzzzz&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param overlay
	 *            用来覆盖的字符串
	 * @param start
	 *            起始索引
	 * @param end
	 *            结束索引
	 * 
	 * @return 被覆盖后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String overlay(String str, String overlay, int start, int end) {
		if (str == null) {
			return null;
		}

		if (overlay == null) {
			overlay = EMPTY_STRING;
		}

		int len = str.length();

		if (start < 0) {
			start = 0;
		}

		if (start > len) {
			start = len;
		}

		if (end < 0) {
			end = 0;
		}

		if (end > len) {
			end = len;
		}

		if (start > end) {
			int temp = start;

			start = end;
			end = temp;
		}

		return new StringBuilder((len + start) - end + overlay.length() + 1)
				.append(str.substring(0, start)).append(overlay)
				.append(str.substring(end)).toString();
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* Perl风格的chomp和chop函数。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 删除字符串末尾的换行符。如果字符串不以换行结尾，则什么也不做。
	 * 
	 * <p>
	 * 换行符有三种情形：&quot;<code>\n</code>&quot;、&quot;<code>\r</code>&quot;、&quot;
	 * <code>\r\n</code>&quot;。
	 * 
	 * <pre>
	 * StringUtil.chomp(null)          = null
	 * StringUtil.chomp(&quot;&quot;)            = &quot;&quot;
	 * StringUtil.chomp(&quot;abc \r&quot;)      = &quot;abc &quot;
	 * StringUtil.chomp(&quot;abc\n&quot;)       = &quot;abc&quot;
	 * StringUtil.chomp(&quot;abc\r\n&quot;)     = &quot;abc&quot;
	 * StringUtil.chomp(&quot;abc\r\n\r\n&quot;) = &quot;abc\r\n&quot;
	 * StringUtil.chomp(&quot;abc\n\r&quot;)     = &quot;abc\n&quot;
	 * StringUtil.chomp(&quot;abc\n\rabc&quot;)  = &quot;abc\n\rabc&quot;
	 * StringUtil.chomp(&quot;\r&quot;)          = &quot;&quot;
	 * StringUtil.chomp(&quot;\n&quot;)          = &quot;&quot;
	 * StringUtil.chomp(&quot;\r\n&quot;)        = &quot;&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 不以换行结尾的字符串，如果原始字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String chomp(String str) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}

		if (str.length() == 1) {
			char ch = str.charAt(0);

			if ((ch == '\r') || (ch == '\n')) {
				return EMPTY_STRING;
			} else {
				return str;
			}
		}

		int lastIdx = str.length() - 1;
		char last = str.charAt(lastIdx);

		if (last == '\n') {
			if (str.charAt(lastIdx - 1) == '\r') {
				lastIdx--;
			}
		} else if (last == '\r') {
		} else {
			lastIdx++;
		}

		return str.substring(0, lastIdx);
	}

	/**
	 * 删除字符串末尾的指定字符串。如果字符串不以该字符串结尾，则什么也不做。
	 * 
	 * <pre>
	 * StringUtil.chomp(null, *)         = null
	 * StringUtil.chomp(&quot;&quot;, *)           = &quot;&quot;
	 * StringUtil.chomp(&quot;foobar&quot;, &quot;bar&quot;) = &quot;foo&quot;
	 * StringUtil.chomp(&quot;foobar&quot;, &quot;baz&quot;) = &quot;foobar&quot;
	 * StringUtil.chomp(&quot;foo&quot;, &quot;foo&quot;)    = &quot;&quot;
	 * StringUtil.chomp(&quot;foo &quot;, &quot;foo&quot;)   = &quot;foo &quot;
	 * StringUtil.chomp(&quot; foo&quot;, &quot;foo&quot;)   = &quot; &quot;
	 * StringUtil.chomp(&quot;foo&quot;, &quot;foooo&quot;)  = &quot;foo&quot;
	 * StringUtil.chomp(&quot;foo&quot;, &quot;&quot;)       = &quot;foo&quot;
	 * StringUtil.chomp(&quot;foo&quot;, null)     = &quot;foo&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要处理的字符串
	 * @param separator
	 *            要删除的字符串
	 * 
	 * @return 不以指定字符串结尾的字符串，如果原始字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String chomp(String str, String separator) {
		if ((str == null) || (str.length() == 0) || (separator == null)) {
			return str;
		}

		if (str.endsWith(separator)) {
			return str.substring(0, str.length() - separator.length());
		}

		return str;
	}

	/**
	 * 删除最后一个字符。
	 * 
	 * <p>
	 * 如果字符串以<code>\r\n</code>结尾，则同时删除它们。
	 * 
	 * <pre>
	 * StringUtil.chop(null)          = null
	 * StringUtil.chop(&quot;&quot;)            = &quot;&quot;
	 * StringUtil.chop(&quot;abc \r&quot;)      = &quot;abc &quot;
	 * StringUtil.chop(&quot;abc\n&quot;)       = &quot;abc&quot;
	 * StringUtil.chop(&quot;abc\r\n&quot;)     = &quot;abc&quot;
	 * StringUtil.chop(&quot;abc&quot;)         = &quot;ab&quot;
	 * StringUtil.chop(&quot;abc\nabc&quot;)    = &quot;abc\nab&quot;
	 * StringUtil.chop(&quot;a&quot;)           = &quot;&quot;
	 * StringUtil.chop(&quot;\r&quot;)          = &quot;&quot;
	 * StringUtil.chop(&quot;\n&quot;)          = &quot;&quot;
	 * StringUtil.chop(&quot;\r\n&quot;)        = &quot;&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 删除最后一个字符的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String chop(String str) {
		if (str == null) {
			return null;
		}

		int strLen = str.length();

		if (strLen < 2) {
			return EMPTY_STRING;
		}

		int lastIdx = strLen - 1;
		String ret = str.substring(0, lastIdx);
		char last = str.charAt(lastIdx);

		if (last == '\n') {
			if (ret.charAt(lastIdx - 1) == '\r') {
				return ret.substring(0, lastIdx - 1);
			}
		}

		return ret;
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 重复/对齐字符串。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 将指定字符串重复n遍。
	 * 
	 * <pre>
	 * StringUtil.repeat(null, 2)   = null
	 * StringUtil.repeat(&quot;&quot;, 0)     = &quot;&quot;
	 * StringUtil.repeat(&quot;&quot;, 2)     = &quot;&quot;
	 * StringUtil.repeat(&quot;a&quot;, 3)    = &quot;aaa&quot;
	 * StringUtil.repeat(&quot;ab&quot;, 2)   = &quot;abab&quot;
	 * StringUtil.repeat(&quot;abcd&quot;, 2) = &quot;abcdabcd&quot;
	 * StringUtil.repeat(&quot;a&quot;, -2)   = &quot;&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要重复的字符串
	 * @param repeat
	 *            重复次数，如果小于<code>0</code>，则看作<code>0</code>
	 * 
	 * @return 重复n次的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String repeat(String str, int repeat) {
		if (str == null) {
			return null;
		}

		if (repeat <= 0) {
			return EMPTY_STRING;
		}

		int inputLength = str.length();

		if ((repeat == 1) || (inputLength == 0)) {
			return str;
		}

		int outputLength = inputLength * repeat;

		switch (inputLength) {
		case 1:

			char ch = str.charAt(0);
			char[] output1 = new char[outputLength];

			for (int i = repeat - 1; i >= 0; i--) {
				output1[i] = ch;
			}

			return new String(output1);

		case 2:

			char ch0 = str.charAt(0);
			char ch1 = str.charAt(1);
			char[] output2 = new char[outputLength];

			for (int i = (repeat * 2) - 2; i >= 0; i--, i--) {
				output2[i] = ch0;
				output2[i + 1] = ch1;
			}

			return new String(output2);

		default:

			StringBuilder buf = new StringBuilder(outputLength);

			for (int i = 0; i < repeat; i++) {
				buf.append(str);
			}

			return buf.toString();
		}
	}

	/**
	 * 扩展并左对齐字符串，用空格<code>' '</code>填充右边。
	 * 
	 * <pre>
	 * StringUtil.alignLeft(null, *)   = null
	 * StringUtil.alignLeft(&quot;&quot;, 3)     = &quot;   &quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, 3)  = &quot;bat&quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, 5)  = &quot;bat  &quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, 1)  = &quot;bat&quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, -1) = &quot;bat&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要对齐的字符串
	 * @param size
	 *            扩展字符串到指定宽度
	 * 
	 * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String alignLeft(String str, int size) {
		return alignLeft(str, size, ' ');
	}

	/**
	 * 扩展并左对齐字符串，用指定字符填充右边。
	 * 
	 * <pre>
	 * StringUtil.alignLeft(null, *, *)     = null
	 * StringUtil.alignLeft(&quot;&quot;, 3, 'z')     = &quot;zzz&quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, 3, 'z')  = &quot;bat&quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, 5, 'z')  = &quot;batzz&quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, 1, 'z')  = &quot;bat&quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, -1, 'z') = &quot;bat&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要对齐的字符串
	 * @param size
	 *            扩展字符串到指定宽度
	 * @param padChar
	 *            填充字符
	 * 
	 * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String alignLeft(String str, int size, char padChar) {
		if (str == null) {
			return null;
		}

		int pads = size - str.length();

		if (pads <= 0) {
			return str;
		}

		return alignLeft(str, size, String.valueOf(padChar));
	}

	/**
	 * 扩展并左对齐字符串，用指定字符串填充右边。
	 * 
	 * <pre>
	 * StringUtil.alignLeft(null, *, *)      = null
	 * StringUtil.alignLeft(&quot;&quot;, 3, &quot;z&quot;)      = &quot;zzz&quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, 3, &quot;yz&quot;)  = &quot;bat&quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, 5, &quot;yz&quot;)  = &quot;batyz&quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, 8, &quot;yz&quot;)  = &quot;batyzyzy&quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, 1, &quot;yz&quot;)  = &quot;bat&quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, -1, &quot;yz&quot;) = &quot;bat&quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, 5, null)  = &quot;bat  &quot;
	 * StringUtil.alignLeft(&quot;bat&quot;, 5, &quot;&quot;)    = &quot;bat  &quot;
	 * </pre>
	 * 
	 * @param str
	 *            要对齐的字符串
	 * @param size
	 *            扩展字符串到指定宽度
	 * @param padStr
	 *            填充字符串
	 * 
	 * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String alignLeft(String str, int size, String padStr) {
		if (str == null) {
			return null;
		}

		if ((padStr == null) || (padStr.length() == 0)) {
			padStr = " ";
		}

		int padLen = padStr.length();
		int strLen = str.length();
		int pads = size - strLen;

		if (pads <= 0) {
			return str;
		}

		if (pads == padLen) {
			return str.concat(padStr);
		} else if (pads < padLen) {
			return str.concat(padStr.substring(0, pads));
		} else {
			char[] padding = new char[pads];
			char[] padChars = padStr.toCharArray();

			for (int i = 0; i < pads; i++) {
				padding[i] = padChars[i % padLen];
			}

			return str.concat(new String(padding));
		}
	}

	/**
	 * 扩展并右对齐字符串，用空格<code>' '</code>填充左边。
	 * 
	 * <pre>
	 * StringUtil.alignRight(null, *)   = null
	 * StringUtil.alignRight(&quot;&quot;, 3)     = &quot;   &quot;
	 * StringUtil.alignRight(&quot;bat&quot;, 3)  = &quot;bat&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, 5)  = &quot;  bat&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, 1)  = &quot;bat&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, -1) = &quot;bat&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要对齐的字符串
	 * @param size
	 *            扩展字符串到指定宽度
	 * 
	 * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String alignRight(String str, int size) {
		return alignRight(str, size, ' ');
	}

	/**
	 * 扩展并右对齐字符串，用指定字符填充左边。
	 * 
	 * <pre>
	 * StringUtil.alignRight(null, *, *)     = null
	 * StringUtil.alignRight(&quot;&quot;, 3, 'z')     = &quot;zzz&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, 3, 'z')  = &quot;bat&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, 5, 'z')  = &quot;zzbat&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, 1, 'z')  = &quot;bat&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, -1, 'z') = &quot;bat&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要对齐的字符串
	 * @param size
	 *            扩展字符串到指定宽度
	 * @param padChar
	 *            填充字符
	 * 
	 * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String alignRight(String str, int size, char padChar) {
		if (str == null) {
			return null;
		}

		int pads = size - str.length();

		if (pads <= 0) {
			return str;
		}

		return alignRight(str, size, String.valueOf(padChar));
	}

	/**
	 * 扩展并右对齐字符串，用指定字符串填充左边。
	 * 
	 * <pre>
	 * StringUtil.alignRight(null, *, *)      = null
	 * StringUtil.alignRight(&quot;&quot;, 3, &quot;z&quot;)      = &quot;zzz&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, 3, &quot;yz&quot;)  = &quot;bat&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, 5, &quot;yz&quot;)  = &quot;yzbat&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, 8, &quot;yz&quot;)  = &quot;yzyzybat&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, 1, &quot;yz&quot;)  = &quot;bat&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, -1, &quot;yz&quot;) = &quot;bat&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, 5, null)  = &quot;  bat&quot;
	 * StringUtil.alignRight(&quot;bat&quot;, 5, &quot;&quot;)    = &quot;  bat&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要对齐的字符串
	 * @param size
	 *            扩展字符串到指定宽度
	 * @param padStr
	 *            填充字符串
	 * 
	 * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String alignRight(String str, int size, String padStr) {
		if (str == null) {
			return null;
		}

		if ((padStr == null) || (padStr.length() == 0)) {
			padStr = " ";
		}

		int padLen = padStr.length();
		int strLen = str.length();
		int pads = size - strLen;

		if (pads <= 0) {
			return str;
		}

		if (pads == padLen) {
			return padStr.concat(str);
		} else if (pads < padLen) {
			return padStr.substring(0, pads).concat(str);
		} else {
			char[] padding = new char[pads];
			char[] padChars = padStr.toCharArray();

			for (int i = 0; i < pads; i++) {
				padding[i] = padChars[i % padLen];
			}

			return new String(padding).concat(str);
		}
	}

	/**
	 * 扩展并居中字符串，用空格<code>' '</code>填充两边。
	 * 
	 * <pre>
	 * StringUtil.center(null, *)   = null
	 * StringUtil.center(&quot;&quot;, 4)     = &quot;    &quot;
	 * StringUtil.center(&quot;ab&quot;, -1)  = &quot;ab&quot;
	 * StringUtil.center(&quot;ab&quot;, 4)   = &quot; ab &quot;
	 * StringUtil.center(&quot;abcd&quot;, 2) = &quot;abcd&quot;
	 * StringUtil.center(&quot;a&quot;, 4)    = &quot; a  &quot;
	 * </pre>
	 * 
	 * @param str
	 *            要对齐的字符串
	 * @param size
	 *            扩展字符串到指定宽度
	 * 
	 * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String center(String str, int size) {
		return center(str, size, ' ');
	}

	/**
	 * 扩展并居中字符串，用指定字符填充两边。
	 * 
	 * <pre>
	 * StringUtil.center(null, *, *)     = null
	 * StringUtil.center(&quot;&quot;, 4, ' ')     = &quot;    &quot;
	 * StringUtil.center(&quot;ab&quot;, -1, ' ')  = &quot;ab&quot;
	 * StringUtil.center(&quot;ab&quot;, 4, ' ')   = &quot; ab &quot;
	 * StringUtil.center(&quot;abcd&quot;, 2, ' ') = &quot;abcd&quot;
	 * StringUtil.center(&quot;a&quot;, 4, ' ')    = &quot; a  &quot;
	 * StringUtil.center(&quot;a&quot;, 4, 'y')    = &quot;yayy&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要对齐的字符串
	 * @param size
	 *            扩展字符串到指定宽度
	 * @param padChar
	 *            填充字符
	 * 
	 * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String center(String str, int size, char padChar) {
		if ((str == null) || (size <= 0)) {
			return str;
		}

		int strLen = str.length();
		int pads = size - strLen;

		if (pads <= 0) {
			return str;
		}

		str = alignRight(str, strLen + (pads / 2), padChar);
		str = alignLeft(str, size, padChar);
		return str;
	}

	/**
	 * 扩展并居中字符串，用指定字符串填充两边。
	 * 
	 * <pre>
	 * StringUtil.center(null, *, *)     = null
	 * StringUtil.center(&quot;&quot;, 4, &quot; &quot;)     = &quot;    &quot;
	 * StringUtil.center(&quot;ab&quot;, -1, &quot; &quot;)  = &quot;ab&quot;
	 * StringUtil.center(&quot;ab&quot;, 4, &quot; &quot;)   = &quot; ab &quot;
	 * StringUtil.center(&quot;abcd&quot;, 2, &quot; &quot;) = &quot;abcd&quot;
	 * StringUtil.center(&quot;a&quot;, 4, &quot; &quot;)    = &quot; a  &quot;
	 * StringUtil.center(&quot;a&quot;, 4, &quot;yz&quot;)   = &quot;yayz&quot;
	 * StringUtil.center(&quot;abc&quot;, 7, null) = &quot;  abc  &quot;
	 * StringUtil.center(&quot;abc&quot;, 7, &quot;&quot;)   = &quot;  abc  &quot;
	 * </pre>
	 * 
	 * @param str
	 *            要对齐的字符串
	 * @param size
	 *            扩展字符串到指定宽度
	 * @param padStr
	 *            填充字符串
	 * 
	 * @return 扩展后的字符串，如果字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String center(String str, int size, String padStr) {
		if ((str == null) || (size <= 0)) {
			return str;
		}

		if ((padStr == null) || (padStr.length() == 0)) {
			padStr = " ";
		}

		int strLen = str.length();
		int pads = size - strLen;

		if (pads <= 0) {
			return str;
		}

		str = alignRight(str, strLen + (pads / 2), padStr);
		str = alignLeft(str, size, padStr);
		return str;
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 反转字符串。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 反转字符串中的字符顺序。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.reverse(null)  = null
	 * StringUtil.reverse(&quot;&quot;)    = &quot;&quot;
	 * StringUtil.reverse(&quot;bat&quot;) = &quot;tab&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要反转的字符串
	 * 
	 * @return 反转后的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String reverse(String str) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}

		return new StringBuilder(str).reverse().toString();
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 取得字符串的缩略。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 将字符串转换成指定长度的缩略，例如：
	 * 将"Now is the time for all good men"转换成"Now is the time for..."。
	 * 
	 * <ul>
	 * <li>
	 * 如果<code>str</code>比<code>maxWidth</code>短，直接返回；</li>
	 * <li>
	 * 否则将它转换成缩略：<code>substring(str, 0, max-3) + "..."</code>；</li>
	 * <li>
	 * 如果<code>maxWidth</code>小于<code>4</code>抛出
	 * <code>IllegalArgumentException</code>；</li>
	 * <li>
	 * 返回的字符串不可能长于指定的<code>maxWidth</code>。</li>
	 * </ul>
	 * 
	 * <pre>
	 * StringUtil.abbreviate(null, *)      = null
	 * StringUtil.abbreviate(&quot;&quot;, 4)        = &quot;&quot;
	 * StringUtil.abbreviate(&quot;abcdefg&quot;, 6) = &quot;abc...&quot;
	 * StringUtil.abbreviate(&quot;abcdefg&quot;, 7) = &quot;abcdefg&quot;
	 * StringUtil.abbreviate(&quot;abcdefg&quot;, 8) = &quot;abcdefg&quot;
	 * StringUtil.abbreviate(&quot;abcdefg&quot;, 4) = &quot;a...&quot;
	 * StringUtil.abbreviate(&quot;abcdefg&quot;, 3) = IllegalArgumentException
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param maxWidth
	 *            最大长度，不小于<code>4</code>，如果小于<code>4</code>，则看作<code>4</code>
	 * 
	 * @return 字符串缩略，如果原始字符串为<code>null</code>则返回<code>null</code>
	 */
	public static String abbreviate(String str, int maxWidth) {
		return abbreviate(str, 0, maxWidth);
	}

	/**
	 * 将字符串转换成指定长度的缩略，例如：
	 * 将"Now is the time for all good men"转换成"...is the time for..."。
	 * 
	 * <p>
	 * 和<code>abbreviate(String, int)</code>类似，但是增加了一个“左边界”偏移量。
	 * 注意，“左边界”处的字符未必出现在结果字符串的最左边，但一定出现在结果字符串中。
	 * </p>
	 * 
	 * <p>
	 * 返回的字符串不可能长于指定的<code>maxWidth</code>。
	 * 
	 * <pre>
	 * StringUtil.abbreviate(null, *, *)                = null
	 * StringUtil.abbreviate(&quot;&quot;, 0, 4)                  = &quot;&quot;
	 * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, -1, 10) = &quot;abcdefg...&quot;
	 * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 0, 10)  = &quot;abcdefg...&quot;
	 * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 1, 10)  = &quot;abcdefg...&quot;
	 * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 4, 10)  = &quot;abcdefg...&quot;
	 * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 5, 10)  = &quot;...fghi...&quot;
	 * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 6, 10)  = &quot;...ghij...&quot;
	 * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 8, 10)  = &quot;...ijklmno&quot;
	 * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 10, 10) = &quot;...ijklmno&quot;
	 * StringUtil.abbreviate(&quot;abcdefghijklmno&quot;, 12, 10) = &quot;...ijklmno&quot;
	 * StringUtil.abbreviate(&quot;abcdefghij&quot;, 0, 3)        = IllegalArgumentException
	 * StringUtil.abbreviate(&quot;abcdefghij&quot;, 5, 6)        = IllegalArgumentException
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param offset
	 *            左边界偏移量
	 * @param maxWidth
	 *            最大长度，不小于<code>4</code>，如果小于<code>4</code>，则看作<code>4</code>
	 * 
	 * @return 字符串缩略，如果原始字符串为<code>null</code>则返回<code>null</code>
	 */
	public static String abbreviate(String str, int offset, int maxWidth) {
		if (str == null) {
			return null;
		}

		// 调整最大宽度
		if (maxWidth < 4) {
			maxWidth = 4;
		}

		if (str.length() <= maxWidth) {
			return str;
		}

		if (offset > str.length()) {
			offset = str.length();
		}

		if ((str.length() - offset) < (maxWidth - 3)) {
			offset = str.length() - (maxWidth - 3);
		}

		if (offset <= 4) {
			return str.substring(0, maxWidth - 3) + "...";
		}

		// 调整最大宽度
		if (maxWidth < 7) {
			maxWidth = 7;
		}

		if ((offset + (maxWidth - 3)) < str.length()) {
			return "..." + abbreviate(str.substring(offset), maxWidth - 3);
		}

		return "..." + str.substring(str.length() - (maxWidth - 3));
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 比较两个字符串的异同。 */
	/*                                                                              */
	/* 查找字符串之间的差异，比较字符串的相似度。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 比较两个字符串，取得第二个字符串中，和第一个字符串不同的部分。
	 * 
	 * <pre>
	 * StringUtil.difference(&quot;i am a machine&quot;, &quot;i am a robot&quot;)  = &quot;robot&quot;
	 * StringUtil.difference(null, null)                        = null
	 * StringUtil.difference(&quot;&quot;, &quot;&quot;)                            = &quot;&quot;
	 * StringUtil.difference(&quot;&quot;, null)                          = &quot;&quot;
	 * StringUtil.difference(&quot;&quot;, &quot;abc&quot;)                         = &quot;abc&quot;
	 * StringUtil.difference(&quot;abc&quot;, &quot;&quot;)                         = &quot;&quot;
	 * StringUtil.difference(&quot;abc&quot;, &quot;abc&quot;)                      = &quot;&quot;
	 * StringUtil.difference(&quot;ab&quot;, &quot;abxyz&quot;)                     = &quot;xyz&quot;
	 * StringUtil.difference(&quot;abcde&quot;, &quot;abxyz&quot;)                  = &quot;xyz&quot;
	 * StringUtil.difference(&quot;abcde&quot;, &quot;xyz&quot;)                    = &quot;xyz&quot;
	 * </pre>
	 * 
	 * @param str1
	 *            字符串1
	 * @param str2
	 *            字符串2
	 * 
	 * @return 第二个字符串中，和第一个字符串不同的部分。如果两个字符串相同，则返回空字符串<code>""</code>
	 */
	public static String difference(String str1, String str2) {
		if (str1 == null) {
			return str2;
		}

		if (str2 == null) {
			return str1;
		}

		int index = indexOfDifference(str1, str2);

		if (index == -1) {
			return EMPTY_STRING;
		}

		return str2.substring(index);
	}

	/**
	 * 比较两个字符串，取得两字符串开始不同的索引值。
	 * 
	 * <pre>
	 * StringUtil.indexOfDifference(&quot;i am a machine&quot;, &quot;i am a robot&quot;)   = 7
	 * StringUtil.indexOfDifference(null, null)                         = -1
	 * StringUtil.indexOfDifference(&quot;&quot;, null)                           = -1
	 * StringUtil.indexOfDifference(&quot;&quot;, &quot;&quot;)                             = -1
	 * StringUtil.indexOfDifference(&quot;&quot;, &quot;abc&quot;)                          = 0
	 * StringUtil.indexOfDifference(&quot;abc&quot;, &quot;&quot;)                          = 0
	 * StringUtil.indexOfDifference(&quot;abc&quot;, &quot;abc&quot;)                       = -1
	 * StringUtil.indexOfDifference(&quot;ab&quot;, &quot;abxyz&quot;)                      = 2
	 * StringUtil.indexOfDifference(&quot;abcde&quot;, &quot;abxyz&quot;)                   = 2
	 * StringUtil.indexOfDifference(&quot;abcde&quot;, &quot;xyz&quot;)                     = 0
	 * </pre>
	 * 
	 * @param str1
	 *            字符串1
	 * @param str2
	 *            字符串2
	 * 
	 * @return 两字符串开始产生差异的索引值，如果两字符串相同，则返回<code>-1</code>
	 */
	public static int indexOfDifference(String str1, String str2) {
		if ((str1 == null) || (str2 == null) || (str1.equals(str2))) {
			return -1;
		}

		int i;

		for (i = 0; (i < str1.length()) && (i < str2.length()); ++i) {
			if (str1.charAt(i) != str2.charAt(i)) {
				break;
			}
		}
		return i;
	}

	/**
	 * 取得两个字符串的相似度，<code>0</code>代表字符串相等，数字越大表示字符串越不像。
	 * 
	 * <p>
	 * 这个算法取自<a
	 * href="http://www.merriampark.com/ld.htm">http://www.merriampark.com
	 * /ld.htm</a>。 它计算的是从字符串1转变到字符串2所需要的删除、插入和替换的步骤数。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.getLevenshteinDistance(null, *)             = IllegalArgumentException
	 * StringUtil.getLevenshteinDistance(*, null)             = IllegalArgumentException
	 * StringUtil.getLevenshteinDistance(&quot;&quot;,&quot;&quot;)               = 0
	 * StringUtil.getLevenshteinDistance(&quot;&quot;,&quot;a&quot;)              = 1
	 * StringUtil.getLevenshteinDistance(&quot;aaapppp&quot;, &quot;&quot;)       = 7
	 * StringUtil.getLevenshteinDistance(&quot;frog&quot;, &quot;fog&quot;)       = 1
	 * StringUtil.getLevenshteinDistance(&quot;fly&quot;, &quot;ant&quot;)        = 3
	 * StringUtil.getLevenshteinDistance(&quot;elephant&quot;, &quot;hippo&quot;) = 7
	 * StringUtil.getLevenshteinDistance(&quot;hippo&quot;, &quot;elephant&quot;) = 7
	 * StringUtil.getLevenshteinDistance(&quot;hippo&quot;, &quot;zzzzzzzz&quot;) = 8
	 * StringUtil.getLevenshteinDistance(&quot;hello&quot;, &quot;hallo&quot;)    = 1
	 * </pre>
	 * 
	 * @param s
	 *            第一个字符串，如果是<code>null</code>，则看作空字符串
	 * @param t
	 *            第二个字符串，如果是<code>null</code>，则看作空字符串
	 * 
	 * @return 相似度值
	 */
	public static int getLevenshteinDistance(String s, String t) {
		s = defaultIfBlank(s);
		t = defaultIfBlank(t);

		int[][] d; // matrix
		int n; // length of s
		int m; // length of t
		int i; // iterates through s
		int j; // iterates through t
		char s_i; // ith character of s
		char t_j; // jth character of t
		int cost; // cost

		// Step 1
		n = s.length();
		m = t.length();

		if (n == 0) {
			return m;
		}

		if (m == 0) {
			return n;
		}

		d = new int[n + 1][m + 1];

		// Step 2
		for (i = 0; i <= n; i++) {
			d[i][0] = i;
		}

		for (j = 0; j <= m; j++) {
			d[0][j] = j;
		}

		// Step 3
		for (i = 1; i <= n; i++) {
			s_i = s.charAt(i - 1);

			// Step 4
			for (j = 1; j <= m; j++) {
				t_j = t.charAt(j - 1);

				// Step 5
				if (s_i == t_j) {
					cost = 0;
				} else {
					cost = 1;
				}

				// Step 6
				d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1]
						+ cost);
			}
		}

		// Step 7
		return d[n][m];
	}

	/**
	 * 取得最小数。
	 * 
	 * @param a
	 *            整数1
	 * @param b
	 *            整数2
	 * @param c
	 *            整数3
	 * 
	 * @return 三个数中的最小值
	 */
	private static int min(int a, int b, int c) {
		if (b < a) {
			a = b;
		}

		if (c < a) {
			a = c;
		}

		return a;
	}

	/**
	 * 补全字符串
	 * 
	 * @param str
	 *            需要补全的字符串
	 * @param wadChar
	 *            补全码
	 * @param length
	 *            补全长度
	 * @return 补全字符串 如果字符串为<code>null</code>
	 *         或者length&lt;0或者length&lt;str.length()，则返回 <code>str</code>自身
	 */
	public static String STAM(String str, String wadChar, int length) {
		if (str == null || length < 0 || length <= str.length())
			return str;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length - str.length(); i++) {
			sb.append(wadChar);
		}
		sb.append(str);
		return sb.toString();
	}

	/**
	 * 将字符串首字母转换成大写
	 * 
	 * @param str
	 *            需要替换的字符串
	 * @return 首字母为大写的字符串，如果原字符串为<code>null</code>，则返回<code>str</code>自身
	 */
	public static String toUpperCase4First(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		sb.append(Character.toUpperCase(str.charAt(0)));
		sb.append(str.substring(1));
		return sb.toString();
	}

	/**
	 * 将字符串首字母转换成小写
	 * 
	 * @param str
	 *            需要替换的字符串
	 * @return 首字母为小写的字符串，如果原字符串为<code>null</code>，则返回<code>str</code>自身
	 */
	public static String toLowerCase4First(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		sb.append(Character.toLowerCase(str.charAt(0)));
		sb.append(str.substring(1));
		return sb.toString();
	}

	/**
	 * 将字符串转移为ASCII码
	 * 
	 * <pre>
	 * StringUtil.getASCII(null) = null
	 * StringUtil.getASCII(&quot;中华人民共和国&quot;) = &quot;d6d0bbaac8cbc3f1b9b2bacdb9fa&quot;
	 * StringUtil.getASCII(&quot;abc&quot;) = &quot;616263&quot;
	 * </pre>
	 * 
	 * @param str
	 *            字符串
	 * @return ASCII码，如果原字符串为<code>null</code>，则返回<code>null</code>。
	 */
	public static String getASCII(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		byte[] byteArray = str.getBytes();
		for (int i = 0; i < byteArray.length; i++) {
			builder.append(Integer.toHexString(byteArray[i] & 0xff));
		}
		return builder.toString();
	}

	/**
	 * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
	 * 
	 * @param char c, 需要判断的字符
	 * @return boolean, 返回true,Ascill字符
	 */
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 
	 * 截取字符串，按全角字符计算.
	 * 
	 * @param inputString
	 * @param len
	 * @return
	 * @since Guang 0.8.1
	 */
	public static String subString(String inputString, int len) {
		int count = 0;
		char[] chars = inputString.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (isLetter(c)) {
				count++;
			} else {
				count += 2;
			}
			if (count > len * 2) {
				return inputString.substring(0, i);
			}
		}
		return inputString;
	}

	/**
	 * 
	 * 截取字符串，按全角字符计算.
	 * 
	 * @param inputString
	 * @param len
	 * @return
	 * @since Guang 0.8.1
	 */
	public static String subStringAndReplace(String inputString, int len) {
		int count = 0;
		char[] chars = inputString.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (isLetter(c)) {
				count++;
			} else {
				count += 2;
			}
			if (count > len * 2) {
				return inputString.substring(0, i)+"...";
			}
		}
		return inputString;
	}

	/**
	 * 判断一个字符是否为汉字
	 * 
	 * @param char c, 需要判断的字符
	 * @return boolean, 返回true,汉字
	 */
	public static boolean isChinese(char c) {
		return (c >= 0x4e00 && c <= 0x9fa5);
	}

	/**
	 * 判断一个字符是否为英文字母及数字（包含大小写）
	 * 
	 * @param char c, 需要判断的字符
	 * @return boolean, 返回true,字母
	 */
	public static boolean isEnglishLetterOrNumber(char c) {
		return (c >= 0x61 && c <= 0x7a) || (c >= 0x41 && c <= 0x5a) || (c >= 0x30 && c <= 0x39);
	}

	public static boolean isEnglishLettersOrNumbers(String s) {
		if (s == null)
			return false;
		for (int i = 0; i<s.length(); i++) {
			if (!isEnglishLetterOrNumber(s.charAt(i)))
				return false;
		}
		return true;
	}

	public static boolean isAllAscii(String s) {
		if (s == null)
			return false;
		for (int i = 0; i<s.length(); i++) {
			if (s.charAt(i) > 0x7f)
				return false;
		}
		return true;		
	}

	/**
	 * 判断目标字符串中的有效文字（汉字及英文字母）是否大于指定长度（字母2个计为1）
	 * 
	 * @param inputStr
	 * @param length
	 * @return
	 */
	public static Long computeValidTextLength(String inputStr) {
		if (isEmpty(inputStr)) {
			return 0L;
		}
		//过滤掉html标签
		inputStr = inputStr.replaceAll("<br/><br>", "");
		//过滤html实体字符名称
		inputStr = inputStr.replaceAll("&[a-zA-Z]{1,7};", "");
		//过滤html实体字符编号
		inputStr = inputStr.replaceAll("&#\\d{1,5};", "");
		int count = 0;
		char[] chars = inputStr.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (isEnglishLetterOrNumber(c)) {
				count++;
			} else if(isChinese(c)){
				count += 2;
			}
		}
		return  Math.round(count/2.0);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

		System.out.println(computeValidTextLength("&#8704;&#00;壹&forall;"));//34 t
		System.out.println(computeValidTextLength("&#8704;壹&forall;a00&lt;"));// t
		System.out.println(computeValidTextLength("，貳叁0123456789肆伍陸叁肆伍陸壹貳叁肆伍陸叁肆伍陸壹貳叁肆伍陸http://detail.tmall.com/item.htm"));// t
		 System.out.println(computeValidTextLength(",貳叁肆撒的撒旦sadasdsad伍陸http://detail.tmall.com/item.htm?id=13673473298&key=C1"));//t
		 System.out.println(computeValidTextLength("ab壹貳abc伍陸"));//t
		 System.out.println(computeValidTextLength("0123456789http://detail.tmall.com/item.htm?id=13673473298&key=C1"));//t

		 System.out.println(computeValidTextLength("質量不錯"));//t

	}

	/**
	 * 
	 * 计算字符串长度，按照区分全角和半角字符的方式（用于发送微博内容及存入数据库的字符数量的校验）.
	 * 
	 * 规则：一个字的长度为 "全角字符一个" or "半角字符2个" 微博：新浪、腾讯 inputStr is null or "" = 0
	 * StringUtil.computeTextLength("12345") = 3
	 * StringUtil.computeTextLength("123456") = 3
	 * StringUtil.computeTextLength("aB*45") = 3
	 * StringUtil.computeTextLength("aB-456") = 3
	 * StringUtil.computeTextLength("这事5-a") = 4
	 * StringUtil.computeTextLength("则撒的$/、0") = 6
	 * StringUtil.computeTextLength("壹貳叁肆伍陸") = 6
	 * 
	 * @param inputStr
	 * @return
	 * @since Guang 0.7.1
	 */
	public static Long computeTextLength(String inputStr) {
		if (isEmpty(inputStr)) {
			return 0L;
		}

		try {
			byte[] bytes = inputStr.getBytes("UTF-8");
			if (bytes != null && bytes.length > 0) {
				return Math.round((bytes.length + inputStr.length()) / 4.0);
			}
		} catch (UnsupportedEncodingException e) {
			// ignore
		}

		// Error:返回字符串长度
		return new Long(inputStr.length());
	}

    /**
	 * 正则
	 */
	public static String replaceAll(String text, String repl, String regex) {
		if (text == null || repl == null || regex == null)
			return null;
		return text.replaceAll(regex, repl);
	}

	public static int strLength(String text) {
		if (text == null)
			return 0;
		return text.length();
	}

	/**
	 * 截取字符串函数，返回处理后的字符串
	 * @param source 要截断的字符串
	 * @param len 要截取的字节数
	 * @param delim
	 * @return 截取后附加在后的字符串
	 * <pre>
	 * String s1 = "此智能设计支架可将您的iPhone或ipod垂直或水平直立，可以同时连接USB电线将iPod或iPhone与电脑数据同步。 ";
		System.out.println(StringUtil.strTruncate(s1, 40, "..."));
	 * </pre>
	 */
	public static String strTruncate(String source, int len, String delim) {
		if (null == source) return null;	// 字符串为空不做处理
		int start, stop, byteLen;
		int alen = source.getBytes().length;	// // 得到需要截断的字符串的字节数
		if (len > 0) {
			if (alen <= len) return source;	// 如果比要截取的字节数还小，不做处理
			start = stop = byteLen = 0;
			while (byteLen <= len) {
				if (source.substring(stop, stop+1).getBytes().length == 1) {
					byteLen += 1;	// 单字节字符处理
				} else {
					byteLen += 2;	// 双字节字符处理
				}
				stop++;
			}
			StringBuffer sb = new StringBuffer(source.subSequence(start, stop-1));
			if (alen > len) sb.append(delim);	// 加入附加在后的字符串
			return sb.toString();
		}
		return source;
	}
}
