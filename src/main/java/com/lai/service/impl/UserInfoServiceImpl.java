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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.lai.base.BaseServiceImpl;
import com.lai.repository.UserInfoRepository;
import com.lai.pojo.UserInfo;
import com.lai.service.UserInfoService;
/**
 * Created by Mao on 2018/11/26.
 */
@Service
@Transactional
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {
    
    @Autowired
    private UserInfoRepository userInfoRepository;
    
    @Override
	protected JpaRepository<UserInfo, Integer> getRepository() {
		return userInfoRepository;
	}

	public Page<UserInfo> findByPage(Integer start,Integer size, UserInfo pojo) {
		//order by
		Sort sort = new Sort(Direction.DESC,"seqid");
		PageRequest page = new PageRequest(start, size,sort);
		
		 Specification<UserInfo> specification = (Root<UserInfo> root,
		                CriteriaQuery<?> query, CriteriaBuilder cb)-> {
		            List<Predicate> list = new ArrayList<Predicate>();
		            
		            //==========查询条件=================== String Integer Date
						if(StringUtils.hasText(pojo.getAuiAppId())){
			            	Predicate p = cb.equal(root.get("auiAppId").as(String.class), pojo.getAuiAppId());
			            	list.add(p);
			            }
						if(StringUtils.hasText(pojo.getAuiAppSecret())){
			            	Predicate p = cb.equal(root.get("auiAppSecret").as(String.class), pojo.getAuiAppSecret());
			            	list.add(p);
			            }
						if(pojo.getAuiStatus()!= null){
			            	Predicate p = cb.equal(root.get("auiStatus").as(Integer.class), pojo.getAuiStatus());
			            	list.add(p);
			            }
						if(pojo.getAuiDayRequestCount() != null){
			            	Predicate p = cb.equal(root.get("auiDayRequestCount").as(Integer.class), pojo.getAuiDayRequestCount());
			            	list.add(p);
			            }
						if(StringUtils.hasText(pojo.getAuiAjaxBindIp())){
			            	Predicate p = cb.equal(root.get("auiAjaxBindIp").as(String.class), pojo.getAuiAjaxBindIp());
			            	list.add(p);
			            }
						if(StringUtils.hasText(pojo.getAuiMark())){
			            	Predicate p = cb.equal(root.get("auiMark").as(String.class), pojo.getAuiMark());
			            	list.add(p);
			            }
		            
	                //=====================================
		            
		            return cb.and(list.toArray(new Predicate[0]));
		};
		
		return userInfoRepository.findAll(specification, page);
	}

}
