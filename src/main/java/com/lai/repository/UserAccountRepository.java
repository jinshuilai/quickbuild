package com.lai.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.lai.domain.UserAccount;
import com.lai.domain.UserAccountView;

public interface UserAccountRepository extends JpaRepository<UserAccount,Integer>,JpaSpecificationExecutor<UserAccount> {

	UserAccount findByUserName(String userName);

	@Query(value="select p.SEQID seqid,p.COMPANY_SEQID companySeqid,p.ACCOUNT account,"+
	"p.PASSWORD password,p.USER_TYPE userType,p.USE_STATUS useStatus,p.USER_NAME userName,"+
	"p.SEX sex,p.JOB_ID jobId,p.FACE_ID faceId,p.UPDATE_TIME updateTime,p.ADD_TIME addTime,"+
	"c.COMPANY_NAME companyName from USER_ACCOUNT p left join COMPANY_INFO c ON p.COMPANY_SEQID = c.SEQID "
	,nativeQuery=true,
	countQuery="select count(*) from USER_ACCOUNT p left join COMPANY_INFO c ON p.COMPANY_SEQID = c.SEQID")
	Page<UserAccountView> findViewByName(Pageable page);
}
