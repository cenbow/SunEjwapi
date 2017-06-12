package com.huaao.sunejwapi.api.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.huaao.sunejwapi.common.Constants;
import com.huaao.sunejwapi.common.util.DateTool;
import com.huaao.sunejwapi.common.util.FuncTool;

public class RequestInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute(Constants.REQUEST_ID,"RQ"+FuncTool.createUUID());
		request.setAttribute(Constants.REQUEST_TIME,DateTool.nowTimeStap());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
 	}

}
