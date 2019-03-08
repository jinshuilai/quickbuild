package com.lai.web;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lai.pojo.User;
import com.lai.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private UserService userService;

	@Test
	public void getHello() throws Exception {
		Pageable pageable = new PageRequest(0,20);
	    //封装条件查询对象Specification
	    Specification<User> specification = new Specification<User>(){

	        
	        // Root 用于获取属性字段，CriteriaQuery可以用于简单条件查询，CriteriaBuilder 用于构造复杂条件查询
	        public Predicate toPredicate(Root<User> root,
	                CriteriaQuery<?> query, CriteriaBuilder cb) {
	            //集合 用于封装查询条件
	            List<Predicate> list = new ArrayList<Predicate>();
	            //简单单表查询
	            
                //Predicate p1 = cb.equal(root.get("userName").as(String.class),"mao");
                //list.add(p1);
	            try{
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmm");
	                Date d1 = sdf.parse("20181112 1200");
	                Date d2 = sdf.parse("20181114 1200");
	                Predicate p2 = cb.between(root.get("addTime").as(Date.class), d1, d2);
	                list.add(p2);
	            } catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
	            return cb.and(list.toArray(new Predicate[0]));
	        }

	    };
	    //调用业务层，返回Page
	    //Page<User> page = userService.getPage().findAll(specification, pageable);
	    //System.out.println(page.getContent());
	    
	    User user = new User();
	    user.setUserName("mac");
	    ExampleMatcher matcher = ExampleMatcher.matching()
	    		.withMatcher("userName", ExampleMatcher.GenericPropertyMatchers.contains())
	    		.withIgnoreCase("password");
	    
	    Example<User> example = Example.of(user,matcher);
	   // Page<User> page2 = userService.getDao().findAll(example,pageable);
	   // System.out.println(page2.getContent());
	}

}
