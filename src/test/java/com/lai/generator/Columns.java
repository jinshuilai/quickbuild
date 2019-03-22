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
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Columns){
			Columns o = (Columns) obj;
			if(this.name.equals(o.getName())){
				return true;
			}
		}
		return false;
	}
}
