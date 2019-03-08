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
import com.lai.util.ObjectAnalyzer;

@Entity
@Table(name="user")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seqid")
	private Integer seqid;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="add_time")
	private Date addTime;
	
	@Transient
	private Date addTimeEnd;

	public Integer getSeqid() {
		return seqid;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setSeqid(Integer seqid) {
		this.seqid = seqid;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

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
	
}
