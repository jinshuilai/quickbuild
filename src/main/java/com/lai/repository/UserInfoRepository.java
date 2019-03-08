package com.lai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lai.pojo.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer>,JpaSpecificationExecutor<UserInfo> {

}
