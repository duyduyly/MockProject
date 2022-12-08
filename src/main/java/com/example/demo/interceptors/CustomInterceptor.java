package com.example.demo.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class CustomInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//In ra de biet dang o trang web nao
		System.out.println("======================================================");
		System.out.println("PreHandle: " + request.getContextPath() + " method: " + request.getMethod() + " url: " + request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		System.out.println("PostHandle: " + request.toString() + " method: " + request.getMethod() + " url: " + request.getRequestURI());
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		if (ex != null) {
//			ex.printStackTrace();
//		} else {
//			System.out.println("AfterCompletion: " + request.toString() + " method: " + request.getMethod() + " url: " + request.getRequestURI());
//		}
		
	}
	


}
