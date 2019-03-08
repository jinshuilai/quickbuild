package com.lai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lai.pojo.User;

public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {

}
