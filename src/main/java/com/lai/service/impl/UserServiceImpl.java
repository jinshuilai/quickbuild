package com.lai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lai.base.BaseServiceImpl;
import com.lai.pojo.User;
import com.lai.repository.UserRepository;
import com.lai.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected JpaRepository<User, Integer> getRepository() {
		return userRepository;
	}

	public Page<User> findByPage(Integer start,Integer size, User user) {
		//order by
		Sort sort = new Sort(Direction.DESC,"addTime");
		PageRequest page = new PageRequest(start, size,sort);
		
		 Specification<User> specification = (Root<User> root,
		                CriteriaQuery<?> query, CriteriaBuilder cb)-> {
		            List<Predicate> list = new ArrayList<Predicate>();
		            
		            //==========查询条件===================
		            if(StringUtils.hasText(user.getUserName())){
		            	Predicate p1 = cb.equal(root.get("userName").as(String.class), user.getUserName());
		            	list.add(p1);
		            }
		            
		            if(StringUtils.hasText(user.getPassword())){
		            	Predicate p2 = cb.equal(root.get("password").as(String.class), user.getUserName());
		            	list.add(p2);
		            }
		            
		            
		            if(user.getAddTime() != null){
		            	Date now = user.getAddTimeEnd() == null ? new Date() : user.getAddTimeEnd();
		                Predicate p3 = cb.between(root.get("addTime").as(Date.class), user.getAddTime(), now);
		                list.add(p3);
		            }
	                //=====================================
		            
		            return cb.and(list.toArray(new Predicate[0]));
		};
		
		return userRepository.findAll(specification, page);
	}
	
}
