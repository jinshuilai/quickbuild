package com.lai.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lai.pojo.UserInfo;
import com.lai.service.UserInfoService;
import com.lai.vo.Result;
import com.lai.vo.ResultGenerator;
/**
* Created by Mao on 2018/11/26.
*/
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/save")
	public Result save(UserInfo userInfo){
	
		userInfoService.save(userInfo);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/deletes")
	public Result delete(String ids){
		
		userInfoService.deletes(ids);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/find")
	public Result find(Integer id){
		
		UserInfo userInfo = userInfoService.find(id);
		return ResultGenerator.genSuccessResult(userInfo);
	}
	
	@RequestMapping("/list")
	public Result list(@RequestParam(defaultValue = "0") Integer start,
			@RequestParam(defaultValue = "20") Integer size,UserInfo userInfo) throws Exception{
		if(start != 0) start--;
		Page<UserInfo> list = userInfoService.findByPage(start,size,userInfo);
		return ResultGenerator.genSuccessResult(list);
	}
}
