package me.daququ.common.core.utils;

import java.util.Map;

public class MapUtils {
	public static String get(String path,Map map){
		String[] arr = path.split("\\.");
		Object result = null;
		if(arr.length>1){
			Object temp = map;
			for(String str : arr){
				if(Map.class.isAssignableFrom(temp.getClass())){
					temp = ((Map) temp).get(str);
				} else {
					break;
				}
			}
			result = temp;
		} else {
			result = map.get(path);
			
		}
		return result!=null?result.toString():"";
	}
	
	public static String getString(String path,Map map,String defaultValue){
		String result = defaultValue;
		if(map!=null){
			String[] arr = path.split("\\.");
			
			if(arr.length>1){
				Object temp = map;
				for(String str : arr){
                    if(Map.class.isAssignableFrom(temp.getClass())){
						temp = ((Map) temp).get(str);
					} else {
						break;
					}
				}
				result =  temp.toString();
			} else {
				result =   map.get(path).toString();
				
			}
		}
		
		return result!=null?result:defaultValue;
	}
	
	public static <T> T getAuto(String path,Map map,T defaultValue){
		T result = defaultValue;
		if(map!=null){
			String[] arr = path.split("\\.");
			
			if(arr.length>1){
				Object temp = map;
				for(String str : arr){
                    if(Map.class.isAssignableFrom(temp.getClass())){
						temp = ((Map) temp).get(str);
                        if(null == temp){
                            break;
                        }
					} else {
						break;
					}
				}
                if(temp!=null){
                    result = (T) temp;
                }

			} else {
				result = (T) map.get(path);
				
			}
		}
		
		return result;
	}
	
	public static Object get(String path,Map map,Object defaultValue){
		String[] arr = path.split("\\.");
		Object result = null;
		if(arr.length>1){
			Object temp = map;
			for(String str : arr){
                if(Map.class.isAssignableFrom(temp.getClass())){
					temp = ((Map) temp).get(str);
                    if(temp == null){
                        return defaultValue;
                    }
				} else {
					break;
				}
			}
			result = temp;
		} else {
			result = map.get(path);
            if(result==null)
                result = defaultValue;
			
		}
		return result;
	}

}
