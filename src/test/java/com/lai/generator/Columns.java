package com.lai.generator;

public class Columns {

	private String name;
	private String type;
	
	private String filed;
	private String filedUp;
	private String javaType;
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getFiled() {
		return filed;
	}
	public String getFiledUp() {
		return filedUp;
	}
	public String getJavaType() {
		return javaType;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setFiled(String filed) {
		this.filed = filed;
	}
	public void setFiledUp(String filedUp) {
		this.filedUp = filedUp;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	@Override
	public String toString() {
		return "Columns [name=" + name + ", type=" + type + ", filed=" + filed + ", filedUp=" + filedUp + ", javaType="
				+ javaType + "]";
	}
	
	
}
