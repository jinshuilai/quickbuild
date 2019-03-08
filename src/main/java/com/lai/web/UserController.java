package com.lai.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lai.pojo.User;
import com.lai.service.UserService;
import com.lai.vo.Result;
import com.lai.vo.ResultGenerator;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/save")
	public Result save(User user){
		user.setAddTime(new Date());
		userService.save(user);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/deletes")
	public Result delete(String ids){
		
		userService.deletes(ids);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/find")
	public Result find(Integer id){
		
		User user = userService.find(id);
		return ResultGenerator.genSuccessResult(user);
	}
	
	@RequestMapping("/list")
	public Result list(@RequestParam(defaultValue = "0") Integer start,
			@RequestParam(defaultValue = "20") Integer size,User user) throws Exception{
		if(start != 0) start--;
		Page<User> list = userService.findByPage(start,size,user);
		return ResultGenerator.genSuccessResult(list);
	}
	
}
