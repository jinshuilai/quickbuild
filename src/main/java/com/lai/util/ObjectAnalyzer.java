package com.lai.util;

import java.lang.reflect.Field;

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
}
