package com.lai.framework.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lai.domain.UserAccount;

public final class SessionUtil {

	private static final String LOGIN_USER = "LOGIN_USER";
	private static final String VERIFY_CODE = "VERIFY_CODE";
	
	private static HttpSession getSession(){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attributes.getRequest().getSession();
	}
	
	public static UserAccount getUser(){
		UserAccount UserAccount = (UserAccount) getSession().getAttribute(LOGIN_USER);
		return UserAccount;
	}
	
	public static void setUser(UserAccount UserAccount){
		getSession().setAttribute(LOGIN_USER, UserAccount);
	}

	public static void removeAll() {
		getSession().removeAttribute(LOGIN_USER);
		getSession().removeAttribute(VERIFY_CODE);
	}

	public static String getCheckCode() {
		return (String) getSession().getAttribute(VERIFY_CODE);
	}
	
	public static void setCheckCode(String code){
		getSession().setAttribute(VERIFY_CODE, code);
	}
}
