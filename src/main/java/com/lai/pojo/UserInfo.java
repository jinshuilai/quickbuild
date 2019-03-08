package com.lai.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lai.base.HtmlParams;
import com.lai.util.ObjectAnalyzer;

@Entity
@Table(name="userInfo")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class UserInfo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seqid")
	private Integer seqid;
	public Integer getSeqid() {
		return seqid;
	}
	public void setSeqid(Integer seqid) {
		this.seqid = seqid;
	}
	
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true, lable = "账号")
	@Column(name="aui_app_id")
	private String auiAppId;
	
	public String getAuiAppId() {
		return auiAppId;
	}
	public void setAuiAppId(String auiAppId) {
		this.auiAppId = auiAppId;
	}
	
	@HtmlParams(list=true,input=true,detail=true,must=true, lable = "密码")
	@Column(name="aui_app_secret")
	private String auiAppSecret;
	
	public String getAuiAppSecret() {
		return auiAppSecret;
	}
	public void setAuiAppSecret(String auiAppSecret) {
		this.auiAppSecret = auiAppSecret;
	}
	
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true, lable = "状态")
	@Column(name="aui_status")
	private Integer auiStatus;
	
	public Integer getAuiStatus() {
		return auiStatus;
	}
	public void setAuiStatus(Integer auiStatus) {
		this.auiStatus = auiStatus;
	}
	
	@HtmlParams(list=true,input=true,detail=true, lable = "时限")
	@Column(name="aui_day_request_count")
	private Integer auiDayRequestCount;
	
	public Integer getAuiDayRequestCount() {
		return auiDayRequestCount;
	}
	public void setAuiDayRequestCount(Integer auiDayRequestCount) {
		this.auiDayRequestCount = auiDayRequestCount;
	}
	
	@HtmlParams(list=true,input=true,detail=true, lable = "登录ip")
	@Column(name="aui_ajax_bind_ip")
	private String auiAjaxBindIp;
	
	public String getAuiAjaxBindIp() {
		return auiAjaxBindIp;
	}
	public void setAuiAjaxBindIp(String auiAjaxBindIp) {
		this.auiAjaxBindIp = auiAjaxBindIp;
	}
	
	@HtmlParams(list=true,input=true,detail=true, lable = "备注")
	@Column(name="aui_mark")
	private String auiMark;
	
	public String getAuiMark() {
		return auiMark;
	}
	public void setAuiMark(String auiMark) {
		this.auiMark = auiMark;
	}
	
	
	@Override
	public String toString() {
		return ObjectAnalyzer.toString(this);
	}
	
}
