package me.daququ.common.core.net.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
 
public class HttpUtils2 {
	static Logger logger = LoggerFactory.getLogger(HttpUtils2.class);
	static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.93 Safari/537.36";
	
	
	public static byte[] jdk_post(String url, Map<String, String> argus) throws IOException {
		 
		OutputStream out = null;
		InputStream in = null;
		ByteArrayOutputStream buffOs = null;
		HttpURLConnection connection = null;
		 
		try {
			URL postUrl = new URL(url);
			connection = (HttpURLConnection) postUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setRequestProperty("User-Agent", USER_AGENT);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			connection.connect();
			out = connection.getOutputStream();
			List<String> l = new ArrayList<String>();
			for (Iterator<String> it = argus.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				l.add(key + "=" + encode(argus.get(key).toString()));
			}
			String content = Joiner.on("&").join(l);
			logger.debug("post url[" + url + "],form content[" + content + "]");
			out.write(content.getBytes());
			out.flush();

			in = connection.getInputStream();
			buffOs = new ByteArrayOutputStream();
			byte[] buff = new byte[2048];
			int n;
			while ((n = in.read(buff)) != -1) {
				buffOs.write(buff, 0, n);
			}
			return buffOs.toByteArray();
		} finally {
			try {
				if (connection != null)
					connection.disconnect();
				if(buffOs!=null){
					buffOs.close();
				}
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
	
	
	static String encode(String value) throws UnsupportedEncodingException   {
		if(value!=null){
			return URLEncoder.encode(value, "utf8");
		} else {
			return "";
		}
		
	}
	static Charset charset = Charset.forName("utf8");
	public static String get(String url) throws IOException{
		byte[] buff = jdk_get(url);
		return new String(buff,charset);
	}
	
	public static byte[] jdk_get(String url) throws IOException{
		logger.debug("get url[" + url + "] .");
		HttpURLConnection connection = null;
		ByteArrayOutputStream	buffOs =null;
		InputStream in =null;
		try {
			URL postUrl = new URL(url);
			connection = (HttpURLConnection) postUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			connection.setRequestProperty("User-Agent", USER_AGENT);	
			connection.connect();
			in = connection.getInputStream();
			buffOs = new ByteArrayOutputStream();
			byte[] buff = new byte[2048];
			int n;
			while ((n = in.read(buff)) != -1) {
				buffOs.write(buff, 0, n);
			}
			return buffOs.toByteArray();
		} finally {
			if (connection != null)
				connection.disconnect();
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			try {
				if (buffOs != null)
					buffOs.close();
			} catch (IOException e) {
			}

		}
}
}
