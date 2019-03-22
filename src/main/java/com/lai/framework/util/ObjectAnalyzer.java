package com.lai.framework.util;

import java.lang.reflect.Field;

import javax.persistence.Column;

public class ObjectAnalyzer {
	
	public static String toString(Object mb){
		Field [] fs = mb.getClass().getDeclaredFields();
		StringBuffer sb = new StringBuffer(); 
		sb.append("\n[ "+ mb.getClass().getSimpleName()+",");
		for (int i = 0; i < fs.length; i++) {
			try {
				fs[i].setAccessible(true);
				if(fs[i].getName().startsWith("_") ){
					continue;
				}
				Object obj = fs[i].get(mb);
				String value = obj == null ? " NULL " : obj.toString();
				sb.append(fs[i].getName()+":"+value+",");
			} catch (Exception e) {
			} 
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append("]");
		return sb.toString();
	}
	
	public static String toSqlString(Class clazz,String prex){
		StringBuffer sb = new StringBuffer();
		Field[] fs = clazz.getDeclaredFields();
		for (Field field : fs) {
			field.setAccessible(true);
			boolean b = field.isAnnotationPresent(Column.class);
			if(b){
				Column c = field.getAnnotation(Column.class);
				sb.append(prex+"."+c.name()+" "+field.getName()+",");
			}
		}
		return sb.substring(0, sb.length()-1);
	}
}
