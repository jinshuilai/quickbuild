package com.lai.domain;

import java.util.Date;

public interface UserAccountView {

	Integer getSeqid();
	Integer getCompanySeqid();
	String getAccount();
	String getPassword();
	String getUserType();
	Integer getUseStatus();
	String getUserName();
	Integer getSex();
	String getJobId();
	String getFaceId();
	Date getUpdateTime();
	Date getAddTime();
	String getCompanyName();
}
