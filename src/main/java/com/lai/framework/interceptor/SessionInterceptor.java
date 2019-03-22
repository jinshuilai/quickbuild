package com.lai.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.lai.domain.UserAccount;
import com.lai.framework.util.SessionUtil;

public class SessionInterceptor implements HandlerInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(SessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		log.info(uri);
		if(uri.startsWith("/api")){
			UserAccount user = SessionUtil.getUser();
			if(user == null)
				response.sendRedirect("/pages/login.html");
		}
		
		return true;
	}
}
