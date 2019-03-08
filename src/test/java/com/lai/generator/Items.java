package com.lai.generator;

public class Items {

	private String label;
	private String name;
	private String tips;
	private String type;
	private boolean must;
	public String getLabel() {
		return label;
	}
	public String getName() {
		return name;
	}
	public String getTips() {
		return tips;
	}
	public String getType() {
		return type;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isMust() {
		return must;
	}
	public void setMust(boolean must) {
		this.must = must;
	}
	@Override
	public String toString() {
		return "Items [label=" + label + ", name=" + name + ", tips=" + tips + ", type=" + type + ", must=" + must
				+ "]";
	}
	
	
}
