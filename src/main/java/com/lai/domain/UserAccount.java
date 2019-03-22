package com.lai.domain;

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
import com.lai.framework.util.ObjectAnalyzer;
import com.lai.framework.base.HtmlParams;

@Entity
@Table(name="user_account")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class UserAccount implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SEQID")
	private Integer seqid;
	public Integer getSeqid() {
		return seqid;
	}
	public void setSeqid(Integer seqid) {
		this.seqid = seqid;
	}
	
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true,lable="COMPANY_SEQID")
	@Column(name="COMPANY_SEQID")
	private Integer companySeqid;
	
	public Integer getCompanySeqid() {
		return companySeqid;
	}
	public void setCompanySeqid(Integer companySeqid) {
		this.companySeqid = companySeqid;
	}
	
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true,lable="ACCOUNT")
	@Column(name="ACCOUNT")
	private String account;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true,lable="PASSWORD")
	@Column(name="PASSWORD")
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true,lable="USER_TYPE")
	@Column(name="USER_TYPE")
	private Integer userType;
	
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true,lable="USE_STATUS")
	@Column(name="USE_STATUS")
	private Integer useStatus;
	
	public Integer getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}
	
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true,lable="USER_NAME")
	@Column(name="USER_NAME")
	private String userName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true,lable="SEX")
	@Column(name="SEX")
	private Integer sex;
	
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true,lable="JOB_ID")
	@Column(name="JOB_ID")
	private String jobId;
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true,lable="FACE_ID")
	@Column(name="FACE_ID")
	private String faceId;
	
	public String getFaceId() {
		return faceId;
	}
	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}
	
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true,lable="UPDATE_TIME")
	@Column(name="UPDATE_TIME")
	private Date updateTime;
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@HtmlParams(search=true,lable="UPDATE_TIMEEnd")
	@Transient
	private Date updateTimeEnd;
	
	public Date getUpdateTimeEnd() {
		return updateTimeEnd;
	}
	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}
	
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true,lable="ADD_TIME")
	@Column(name="ADD_TIME")
	private Date addTime;
	
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	@HtmlParams(search=true,lable="ADD_TIMEEnd")
	@Transient
	private Date addTimeEnd;
	
	public Date getAddTimeEnd() {
		return addTimeEnd;
	}
	public void setAddTimeEnd(Date addTimeEnd) {
		this.addTimeEnd = addTimeEnd;
	}
	
	
	@Override
	public String toString() {
		return ObjectAnalyzer.toString(this);
	}
	
	@Transient
	private String verify;
	public void setVerify(String verify) {
		this.verify = verify;
	}
	public String getVerify() {
		return verify;
	}
	
	@Transient 
	private String companyName;
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyName() {
		return companyName;
	}
}
