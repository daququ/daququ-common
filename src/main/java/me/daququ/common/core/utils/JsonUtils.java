package me.daququ.common.core.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class  JsonUtils {
	static Log logger = LogFactory.getLog(JsonUtils.class);
	static JsonFactory factory = new JsonFactory(); 
   
	/**
     * 对象转json字串
     * @param paramObject
     * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
     */
	  public static String obj2jsonException(Object paramObject) throws JsonGenerationException, JsonMappingException, IOException
	  {
	    	 ObjectMapper mapper = new ObjectMapper(factory); 
	    	 mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	    	 return mapper.writeValueAsString(paramObject);
	     
	  }
	
	  
	  /**
	     * 字符串转对象
	     * @param paramString
	     * @param paramClass
	     * @param <T>
	     * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	     */
		  public static <T> T json2objException(String paramString, Class<T> paramClass) throws JsonParseException, JsonMappingException, IOException
		  {
		     
		      ObjectMapper localObjectMapper = new ObjectMapper(factory);
		      localObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		      T localObject = localObjectMapper.readValue(paramString, paramClass);
		    
		    return localObject;
		  }
	
		  
		  public static String obj2jsonPretty(Object paramObject)
		  {
		    String str = null;
		    try
		    {
		    	 ObjectMapper mapper = new ObjectMapper(factory); 
		    	 mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		      str = mapper.defaultPrettyPrintingWriter().writeValueAsString(paramObject);
		    }
		    catch (Exception localException)
		    {
		    	localException.printStackTrace();
		      logger.error("JsonUtils.obj2json", localException);
		    }
		    return str;
		  }
		  
    /**
     * 对象转json字串
     * @param paramObject
     * @return
     */
	  public static String obj2json(Object paramObject)
	  {
	    String str = null;
	    try
	    {
	    	 ObjectMapper mapper = new ObjectMapper(factory); 
	    	 mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	      str = mapper.writeValueAsString(paramObject);
	    }
	    catch (Exception localException)
	    {
	    	localException.printStackTrace();
	      logger.error("JsonUtils.obj2json", localException);
	    }
	    return str;
	  }

	
	public static <T> T json2objForException(String paramString, Class<T> paramClass) throws JsonParseException, JsonMappingException, IOException
	  {
	      ObjectMapper localObjectMapper = new ObjectMapper(factory);
//	      localObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	      T localObject = localObjectMapper.readValue(paramString, paramClass);
	    return localObject;
	  }
	  
    /**
     * 字符串转对象
     * @param paramString
     * @param paramClass
     * @param <T>
     * @return
     */
	  public static <T> T json2obj(String paramString, Class<T> paramClass)
	  {
	    T localObject = null;
	    try
	    {
	      ObjectMapper localObjectMapper = new ObjectMapper(factory);
	      localObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	      localObject = localObjectMapper.readValue(paramString, paramClass);
	    }
	    catch (Exception localException)
	    {
	      logger.error("JsonUtils.obj2json", localException);
	    }
	    return localObject;
	  }

	  /**
	     * 字符串转对象
	     * @param paramString
	     * @param paramClass
	     * @param <T>
	     * @return
	     */
		  public static <T> T json2obj(byte[] paramString, Class<T> paramClass)
		  {
		    T localObject = null;
		    try
		    {
		      ObjectMapper localObjectMapper = new ObjectMapper(factory);
		      localObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		      localObject = localObjectMapper.readValue(paramString, paramClass);
		    }
		    catch (Exception localException)
		    {
		      logger.error("JsonUtils.obj2json", localException);
		    }
		    return localObject;
		  }
	  
	  public static <T> T json2obj(File paramFile, Class<T> paramClass)
	  {
	    T localObject = null;
	    try
	    {
	      ObjectMapper localObjectMapper = new ObjectMapper();
	      localObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	      localObject = localObjectMapper.readValue(paramFile, paramClass);
	    }
	    catch (Exception localException)
	    {
	      logger.error("JsonUtils.json2obj", localException);
	    }
	    return localObject;
	  }
}
