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

import com.lai.domain.UserAccount;
import com.lai.framework.base.BaseServiceImpl;
import com.lai.framework.exception.UserCaseRuntimeException;
import com.lai.repository.UserAccountRepository;
import com.lai.service.UserAccountService;
/**
 * Created by Mao on 2019/03/15.
 */
@Service
public class UserAccountServiceImpl extends BaseServiceImpl<UserAccount> implements UserAccountService {
    
    @Autowired
    private UserAccountRepository userAccountRepository;
    
    @Override
	protected JpaRepository<UserAccount, Integer> getRepository() {
		return userAccountRepository;
	}

	public Page<UserAccount> findByPage(Integer start,Integer size, UserAccount pojo) {
		//order by
		Sort sort = new Sort(Direction.DESC,"seqid");
		PageRequest page = PageRequest.of(start, size,sort);
		
		 Specification<UserAccount> specification = (Root<UserAccount> root,
		                CriteriaQuery<?> query, CriteriaBuilder cb)-> {
		            List<Predicate> list = new ArrayList<Predicate>();
		            
		            //==========查询条件=================== String Integer Date
						if(pojo.getSeqid() != null){
			            	Predicate p = cb.equal(root.get("seqid").as(Integer.class), pojo.getSeqid());
			            	list.add(p);
			            }
						if(pojo.getCompanySeqid() != null){
			            	Predicate p = cb.equal(root.get("companySeqid").as(Integer.class), pojo.getCompanySeqid());
			            	list.add(p);
			            }
						if(StringUtils.hasText(pojo.getAccount())){
			            	Predicate p = cb.equal(root.get("account").as(String.class), pojo.getAccount());
			            	list.add(p);
			            }
						if(StringUtils.hasText(pojo.getPassword())){
			            	Predicate p = cb.equal(root.get("password").as(String.class), pojo.getPassword());
			            	list.add(p);
			            }
						if(pojo.getUserType() != null){
			            	Predicate p = cb.equal(root.get("userType").as(Integer.class), pojo.getUserType());
			            	list.add(p);
			            }
						if(pojo.getUseStatus() != null){
			            	Predicate p = cb.equal(root.get("useStatus").as(Integer.class), pojo.getUseStatus());
			            	list.add(p);
			            }
						if(StringUtils.hasText(pojo.getUserName())){
			            	Predicate p = cb.equal(root.get("userName").as(String.class), pojo.getUserName());
			            	list.add(p);
			            }
						if(pojo.getSex() != null){
			            	Predicate p = cb.equal(root.get("sex").as(Integer.class), pojo.getSex());
			            	list.add(p);
			            }
						if(StringUtils.hasText(pojo.getJobId())){
			            	Predicate p = cb.equal(root.get("jobId").as(String.class), pojo.getJobId());
			            	list.add(p);
			            }
						if(StringUtils.hasText(pojo.getFaceId())){
			            	Predicate p = cb.equal(root.get("faceId").as(String.class), pojo.getFaceId());
			            	list.add(p);
			            }
						
						
		            
	                //=====================================
		            
		            return cb.and(list.toArray(new Predicate[0]));
		};
		
		return userAccountRepository.findAll(specification, page);
	}

	@Override
	public UserAccount login(String userName, String password) {
		UserAccount user = userAccountRepository.findByUserName(userName);
		if(user != null){
			if(user.getPassword().equals(password)){
				return user;
			}
		}
		throw new UserCaseRuntimeException("用户名或密码错误");
	}

	@Override
	public void regist(UserAccount user) {
		UserAccount old = userAccountRepository.findByUserName(user.getAccount());
		if(old != null){
			throw new UserCaseRuntimeException("用户名已注册");
		}
		this.save(user);
	}

}
