// =================================================================================================
// Copyright 2011 Twitter, Inc.
// -------------------------------------------------------------------------------------------------
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this work except in compliance with the License.
// You may obtain a copy of the License in the LICENSE file, or at:
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// =================================================================================================

package me.daququ.common.core.net.http;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import me.daququ.common.core.utils.Random;

public class UrlUtils {

  private static final Logger LOG = Logger.getLogger(UrlUtils.class.getName());

  /**
   * Gets the domain from {@code url}.
   *
   * @param url A url.
   * @return The domain portion of the URL, or {@code null} if the url is invalid.
   */
  public static String getDomain(String url) {
    try {
      return getDomainChecked(url);
    } catch (URISyntaxException e) {
      LOG.finest("Malformed url: " + url);
      return null;
    }
  }

  /**
   * Gets the domain from {@code uri}, and throws an exception if it's not a valid uri.
   *
   * @param url A url.
   * @throws java.net.URISyntaxException if url is not a valid {@code URI}
   * @return The domain portion of the given url, or {@code null} if the host is undefined.
   */
  public static String getDomainChecked(String url) throws URISyntaxException {
    Preconditions.checkNotNull(url);
    url = addProtocol(url);
    return new URI(url).getHost();
  }

  /**
   * Gets the path from {@code url}.
   *
   * @param url A url.
   * @return The path portion of the URL, or {@code null} if the url is invalid.
   */
  public static String getPath(String url) {
    Preconditions.checkNotNull(url);
    url = addProtocol(url);
    try {
      return new URI(url).getPath();
    } catch (URISyntaxException e) {
      LOG.info("Malformed url: " + url);
      return null;
    }
  }

  /**
   * Strips URL parameters from a url.
   * This will remove anything after and including a question mark in the URL.
   *
   * @param url The URL to strip parameters from.
   * @return The original URL with parameters stripped, which will be the original URL if no
   *   parameters were found.
   */
  public static String stripUrlParameters(String url) {
    Preconditions.checkNotNull(url);
    url = addProtocol(url);
    int paramStartIndex = url.indexOf("?");
    
    if (paramStartIndex == -1) {
    	int andStartIdex = url.indexOf("&");
    	if(andStartIdex>0){
    		return url.substring(0,andStartIdex);
    	}
      return url;
    } else {
      return url.substring(0, paramStartIndex);
    }
  }

  /**
   * Convenience method that calls #stripUrlParameters(String) for a URL.
   *
   * @param url The URL to strip parameters from.
   * @return The original URL with parameters stripped, which will be the original URL if no
   *   parameters were found.
   */
  public static String stripUrlParameters(URL url) {
    return stripUrlParameters(url.toString());
  }

  private static final Pattern URL_PROTOCOL_REGEX =
      Pattern.compile("^https?://", Pattern.CASE_INSENSITIVE);

  /**
   * Checks whether a URL specifies its protocol, prepending http if it does not.
   *
   * @param url The URL to fix.
   * @return The URL with the http protocol specified if no protocol was already specified.
   */
  public static String addProtocol(String url) {
    Preconditions.checkNotNull(url);
    Matcher matcher = URL_PROTOCOL_REGEX.matcher(url);
    if (!matcher.find()) {
      url = "http://" + url;
    }
    return url;
  }

  public static String replaceProtocol(String url,String replace){
	 return url.replaceAll("^https?://", replace);
  }
  
  
  
  /**
   * Gets the domain levels for a host.
   * For example, sub1.sub2.domain.co.uk would return
   * [sub1.sub2.domain.co.uk, sub2.domain.co.uk, domain.co.uk, co.uk, uk].
   *
   *
   * @param host The host to peel subdomains off from.
   * @return The domain levels in this host.
   */
  public static List<String> getDomainLevels(String host) {
    Preconditions.checkNotNull(host);

    // Automatically include www prefix if not present.
    if (!host.startsWith("www")) {
      host = "www." + host;
    }

    Joiner joiner = Joiner.on(".");
    List<String> domainParts = Lists.newLinkedList(Arrays.asList(host.split("\\.")));
    List<String> levels = Lists.newLinkedList();

    while (domainParts.size() > 1) {
      levels.add(joiner.join(domainParts));
      domainParts.remove(0);
    }

    return levels;
  }
  
  
	/**
	 * 判断url是否完整
	 */
	static Pattern full_url_pattern = Pattern.compile("^https?://");

	public static boolean isFullUrl(String url) {
		 Preconditions.checkNotNull(url);
		return full_url_pattern.matcher(url).find();
	}
	
	/**
	 * 最后面有/符号
	 * @param url http://www.112233.co/co1/co2/co3/index.php
	 * @return http://www.112233.co/co1/co2/co3/
	 */
	public static String getUrlfullPath(String url){
		 Preconditions.checkNotNull(url);
		 url = replaceProtocol(url, "");
		 int o = -1;
		do {
			url = url.replaceAll("//", "/");
			o = url.indexOf("//");
		} while (o >= 0);
		o = url.lastIndexOf("/");
		if(o != -1){
			url = url.substring(0,o+1);
		} else {
			url = url+"/";
		}
		url=addProtocol(url);
		return url;
	}
	/**
	 * 获取url中的文件
	 * @param url
	 * @return
	 */
	public static String getUrlFile(String url){
		String a = replaceProtocol(stripUrlParameters(url),"");
		
		int o = a.lastIndexOf("/");
		if(o == -1 || (o+1)==a.length()){ return null;} else {
			return a.substring(o+1);
		}
	}
	
	/**
	 * deep = 4
	 * @see
	 * 9/6E/F91/B76E/
	 * @param deep
	 * @return
	 */
	public static String hashDir(int deep){
		StringBuilder sb = new StringBuilder(deep*3);
		String uid = java.util.UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
		Random random = Random.Util.newDefaultRandom();
		for(int i=1;i<deep+1;i++){
			int sed  = random.nextInt(32);
			if(sed+i>32){
				sed = 32-i;
			}
			sb.append(uid.substring(sed, sed+i)).append("/");
		}
		return sb.toString();
	}
	
	 
	
	public static void main(String[] args){
		String a = "http://www.hao123.com/auto";
		
//		System.out.println("stripUrlParameters   "+stripUrlParameters(a));
		System.out.println("getUrlfullPath  "+getUrlfullPath(a));
//		System.out.println("replaceProtocol  "+replaceProtocol(a,""));
//		
		System.out.println("getUrlFile " + getUrlFile(a));
//		System.out.println(replaceProtocol(getUrlfullPath(stripUrlParameters(a)),""));
//		System.out.println(aaa(a));
		
//		String a = "/baidu/";
		
		System.out.println(replaceProtocol(getUrlfullPath("x:/xxxxx/www.hao123.com/auto/"),""));
		 
			System.out.println(hashDir(4));
			System.out.println(dirStrFix("x:/xxxxx//www.hao123.com/A/8D/96D/"));
			//绝对路径
			String absolutePath = "http://www.aaa.com/1/2/";
			System.out.println(getDomain(absolutePath));
			//相对路径
			String relativePath = "http://www.aaa.com/1/3/3/ss";

			System.out.println("relative2Abaslute=> "+relative2Abaslute(absolutePath,relativePath));
	}
	

	public static String aaa(String link){
		return replaceProtocol(getUrlfullPath(stripUrlParameters(link)),"");
	}
	
	public static String dirStrFix(String dirstr){
		int o = -1;
		do {
			dirstr = dirstr.replaceAll("//", "/");
			o = dirstr.indexOf("//");
		} while (o >= 0);
		return dirstr;
	}
	
	/**
	 * relative path to absolute path
	 * @param refere
     * @param  path
	 * @return
	 */
	public static String relative2Abaslute(String refere,String path){
		try {
			
			//以下方法对相对路径进行转换
			URL absoluteUrl = new URL(refere);

			URL parseUrl = new URL(absoluteUrl ,path );
			//最终结果
			return parseUrl.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String buildUrl(String ip, String port, String module, String action, Map<String, String> argus) throws UnsupportedEncodingException {
		String fmt = "http://"+ip+":"+port+"/%s/%s?%s";
		String content = "";
		if (argus != null) {
			List<String> l = new ArrayList<String>();
			for (Iterator<String> it = argus.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				l.add(key + "=" + URLEncoder.encode(String.valueOf(argus.get(key)), "utf8"));
			}
			content = Joiner.on("&").join(l);
		}
		String url = String.format(fmt, module, action, content);
		return url;
	}
	
	public static String buildUrl(String ip, Map<String, String> argus) throws UnsupportedEncodingException {
		String fmt = "http://"+ip+"?%s";
		String content = "";
		if (argus != null) {
			List<String> l = new ArrayList<String>();
			for (Iterator<String> it = argus.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				l.add(key + "=" + URLEncoder.encode(String.valueOf(argus.get(key)), "utf8"));
			}
			content = Joiner.on("&").join(l);
		}
		String url = String.format(fmt, content);
		return url;
	}
}
