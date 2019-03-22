package com.lai.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lai.service.UserAccountService;
import com.lai.domain.UserAccount;
import com.lai.framework.vo.Result;
import com.lai.framework.vo.ResultGenerator;
/**
* Created by Mao on 2019/03/15.
*/
@RestController
@RequestMapping("/api/userAccount")
public class UserAccountController {
    
    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping("/save")
	public Result save(UserAccount userAccount){
	
		userAccountService.save(userAccount);
		return ResultGenerator.success();
	}
	
	@RequestMapping("/deletes")
	public Result delete(String ids){
		
		userAccountService.deletes(ids);
		return ResultGenerator.success();
	}
	
	@RequestMapping("/find")
	public Result find(Integer id){
		
		UserAccount userAccount = userAccountService.find(id);
		return ResultGenerator.success(userAccount);
	}
	
	@RequestMapping("/list")
	public Result list(@RequestParam(defaultValue = "0") Integer start,
			@RequestParam(defaultValue = "20") Integer size,UserAccount userAccount) throws Exception{
		if(start != 0) start--;
		Page<UserAccount> list = userAccountService.findByPage(start,size,userAccount);
		return ResultGenerator.success(list);
	}
}
