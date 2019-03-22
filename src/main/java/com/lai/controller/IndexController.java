package com.lai.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.patchca.utils.encoder.EncoderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lai.domain.UserAccount;
import com.lai.framework.exception.UserCaseRuntimeException;
import com.lai.framework.util.SessionUtil;
import com.lai.framework.util.VerificationUtil;
import com.lai.framework.vo.Result;
import com.lai.framework.vo.ResultGenerator;
import com.lai.service.UserAccountService;

/**
 * 注意：前后分离，所有路由交给前端控制
 * @author Mao
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private UserAccountService userService;
	
	@RequestMapping("/")
	public String index(){
		return "/pages/login.html";
	}
	
	@PostMapping("login")
	@ResponseBody
	public Result doLogin(UserAccount user){
		
		if(StringUtils.isBlank(user.getUserName())){
			return ResultGenerator.fail("用户名不能为空");
		}
		
		if(StringUtils.isBlank(user.getPassword())){
			return ResultGenerator.fail("密码不能为空");
		}
		
		try {
			user = userService.login(user.getUserName(), user.getPassword());
		} catch (UserCaseRuntimeException e) {
			return ResultGenerator.fail(e.getMessage());
		}
			
		SessionUtil.setUser(user);

		return ResultGenerator.success();
	}
	
	@RequestMapping("logout")
	@ResponseBody
	public Result logout(){
		SessionUtil.removeAll();
		return ResultGenerator.success();
	}
	
	@PostMapping("regist")
	@ResponseBody
	public Result doRegist(UserAccount user,HttpServletRequest request){
		if(StringUtils.isBlank(user.getUserName())){
			return ResultGenerator.fail("用户名不能为空");
		}
		
		if(StringUtils.isBlank(user.getPassword())){
			return ResultGenerator.fail("密码不能为空");
		}
		
		String code = SessionUtil.getCheckCode();
		if(StringUtils.isBlank(user.getVerify()) || !user.getVerify().equals(code)){
			return ResultGenerator.fail("验证码错误");
		}
		user.setAddTime(new Date());
		try {
			userService.regist(user);
		} catch (UserCaseRuntimeException e) {
			return ResultGenerator.fail(e.getMessage());
		}
		
		return ResultGenerator.success();
	}
	
	@RequestMapping("regCode")
	public void getRegCode(HttpServletResponse response){
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
		try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
			String code = EncoderHelper.getChallangeAndWriteImage(VerificationUtil.getCs(), "jpg", out);
			log.info("验证码："+code);
			SessionUtil.setCheckCode(code);
			response.getOutputStream().write(out.toByteArray());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("api/me")
	@ResponseBody
	public Result me(){
		UserAccount user = SessionUtil.getUser();
		return ResultGenerator.success(user);
	}
	
	@RequestMapping("api/updPwd")
	@ResponseBody
	public Result updPwd(String password,HttpServletRequest request){
		UserAccount user = SessionUtil.getUser();
		user.setPassword(password);
		userService.save(user);
		return ResultGenerator.success();
	}
}
