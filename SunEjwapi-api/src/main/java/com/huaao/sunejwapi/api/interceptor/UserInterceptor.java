package com.huaao.sunejwapi.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.huaao.sunejwapi.common.Constants;
import com.huaao.sunejwapi.common.security.AccessToken;
import com.huaao.sunejwapi.common.web.SystemException; 


public class UserInterceptor implements HandlerInterceptor{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Value("${checktoken}")
	private String checktoken;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getRequestURI().contains("/apis-sun/systemtool")){
			return true;
		}
		
		if("true".equals(checktoken)){
			String tokenstr = request.getParameter("atoken");
			AccessToken atoken = AccessToken.decode(tokenstr);
			if(atoken == null) {
				throw SystemException.init(Constants.API_CODE_TOKEN_INVALID, "缺少atoken参数或者atoken不正确");
			}
			final String deviceId = request.getParameter("deviceid");
			if(StringUtils.isNotEmpty(deviceId)) {
				if(AccessToken.crc32(deviceId) != atoken.device_checksum) {
					throw SystemException.init(Constants.API_CODE_TOKEN_INVALID, "atoken已经失效（设备不匹配）");
				}
			}
			
			request.setAttribute(Constants.userId, atoken.uid);
 		} 
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
