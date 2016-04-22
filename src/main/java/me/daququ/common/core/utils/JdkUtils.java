package me.daququ.common.core.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public final class JdkUtils {
	public static String jdkVersion() {
		return System.getProperty("java.specification.version");
	}

	public static byte[] serialize(Object object) throws IOException {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			if(baos!=null){
				baos.close();
			}
			if(oos!=null){
				oos.close();
			}
		}
	}

	public static Object unserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		if(bytes!=null && bytes.length>0){
			ObjectInputStream ois = null;
			try {
				  ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(bytes)));
				return ois.readObject();
			} catch (IOException e) {
				throw new IOException(e);
			} catch (ClassNotFoundException e){
				throw new ClassNotFoundException();
			} finally {
				try {
					if(ois!=null){
						ois.close();
					}
				} catch (IOException e) {}
			}
		}
		return null;
		
	}
	
	
	public static enum DataType {
		INTEGER, DATE, DOUBLE, NONE, CHAR
	}

	public static DataType javatype(String typname) {
		DataType r = null;
		String n = typname.trim().toLowerCase();
		if (n == "int" || n == "integer" || n == "long" || n == "short") {
			r = DataType.INTEGER;
		} else if (n == "date" || n == "timestamp"|| n == "datetime") {
			r = DataType.DATE;
		} else if (n == "string" || n == "char" || n == "stringbuffer" || n == "stringbuilder") {
			r = DataType.CHAR;
		} else if (n == "float" || n == "double") {
			r = DataType.DOUBLE;
		} else {
			r = DataType.NONE;
		}
		return r;
	}

	public static String asString(Method method)

	{
		StringBuilder buffer = new StringBuilder();

		buffer.append(method.getDeclaringClass().getName());
		buffer.append(".");
		buffer.append(method.getName());
		buffer.append("(");
		for (int i = 0; i < method.getParameterTypes().length; i++) {
			if (i > 0)
				buffer.append(", ");
			String name = method.getParameterTypes()[i].getSimpleName();
			buffer.append(name);
		}
		return buffer.append(")").toString();
	}

	public static String getClazzName(Class<?> clazz) {
		String n = clazz.getName();
		return n.substring(n.lastIndexOf(".") + 1, n.length());
	}

	public static List<String> getPrivateNoStaticField(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		List<String> strs = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			Field fd = fields[i];
			int md = fd.getModifiers();

			// 获取只有private 不是常量等
			if ((md & Modifier.PRIVATE) != Modifier.PRIVATE && (md & Modifier.FINAL) != Modifier.FINAL
					&& (md & Modifier.STATIC) != Modifier.STATIC) {
				strs.add(fd.getName());
			}
		}
		return strs;
	}

	 

	public static List<MysqlColumnDefine> getAllPulicAndStaticFeild(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		List<MysqlColumnDefine> strs = new ArrayList<MysqlColumnDefine>();
		String n = null;
		for (int i = 0; i < fields.length; i++) {
			Field fd = fields[i];
			int md = fd.getModifiers();

			// 获取只有private 不是常量等
			if ((md & Modifier.PUBLIC) == Modifier.PUBLIC && (md & Modifier.STATIC) == Modifier.STATIC) {
				// n = fd.getType().getName();
				// n = n.lastIndexOf(".")>0 ?
				// (n.substring(n.lastIndexOf(".")+1,n.length())) : n;
				// strs.add(new JavaType(fd.getName(),n.toLowerCase()));
				System.out.println("type.put(\"" + fd.getName() + "\", \"\");");
			}
		}
		return strs;
	}

	public String toString() {
		StringBuffer results = new StringBuffer();
		Class<?> clazz = getClass();

		results.append(getClass().getName());
		results.append("::[");
		Field[] fields = clazz.getDeclaredFields();
		try {
			AccessibleObject.setAccessible(fields, true);

			for (int i = 0; i < fields.length; i++) {
				results.append(" ");
				results.append(fields[i].getName());
				results.append(":=");
				results.append(fields[i].get(this));
			}
			results.append("]");
		} catch (Exception e) {
			// ignored!
		}

		return results.toString();
	}

	public static String toString(Object clazz) {
		StringBuffer results = new StringBuffer();

		results.append(clazz.getClass().getName());
		results.append("::[");
		Field[] fields = clazz.getClass().getDeclaredFields();
		try {
			AccessibleObject.setAccessible(fields, true);

			for (int i = 0; i < fields.length; i++) {
				results.append(" ");
				results.append(fields[i].getName());
				results.append(":=");
				results.append(fields[i].get(clazz));
			}
			results.append("]");
		} catch (Exception e) {
			// ignored!
		}

		return results.toString();
	}

	public static class MysqlColumnDefine {
		public MysqlColumnDefine(String field, String type, String key, String extra, String comment,String javaType) {
			this.field = field;
			this.type = type;
			this.key = key;
			this.extra = extra;
			this.comment = comment;
			this.javaType = javaType;
		}
		String field ;
		String type;
		String key;
		String extra;
		String comment;
		String javaType;
		
		
		public String getJavaType() {
			return javaType;
		}
		public void setJavaType(String javaType) {
			this.javaType = javaType;
		}
		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		 
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getExtra() {
			return extra;
		}
		public void setExtra(String extra) {
			this.extra = extra;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}

		 
	}

	/**
	 * 原生克隆
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Object cloneObject(Object obj) throws Exception {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(obj);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		return in.readObject();
	}
}
