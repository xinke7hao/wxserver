/************************************************************************************
 * @File name   :      AuthenticationInterceptor.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月20日
 *
 * @Copyright Notice: 
 * Copyright (c) 2014 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Who					Version				Comments				Date				
 * XuYanbo				1.0				Initial Version				2015年1月20日 上午10:56:02
 ************************************************************************************/
package com.hunantv.mbp.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.entity.admin.User;

/**
 * 权限验证拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
	
	/**
	 * @Author：XuYanbo
	 * @Date：2015年1月20日
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {

		User user = (User)request.getSession().getAttribute(BmcConstants.LOGIN_USER);
		if(!BmcConstants.YES.equals(user.getIsAdmin())){
			String url = request.getRequestURI();
			String[] temp = url.split("/");
			int methodIndex = temp.length-1;
			if(StringUtils.isNumeric(temp[temp.length-1])){
				methodIndex--;
			}
			String method = temp[methodIndex];
			String menuurl = url.substring(1, url.lastIndexOf("/"+method));
			
			@SuppressWarnings("unchecked")
			Map<String, String> authorities = (Map<String, String>)request.getSession().getAttribute(BmcConstants.USER_MENU_RIGHT);
			String methods = authorities.get(menuurl);
			if(methods==null || !methods.contains(method)){
				String usercode = user!=null ? user.getUserCode() : "Unknown";
				logger.debug("********** Authentication Interceptor ([" + usercode + "] tried to view resource: [" + url + "]) **********");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
		}
		
		return true;
	}

	/**
	 * @Author：XuYanbo
	 * @Date：2015年1月20日
	 * @param request
	 * @param response
	 * @param handler
	 * @param exp
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception exp)
			throws Exception {
	}

	/**
	 * @Author：XuYanbo
	 * @Date：2015年1月20日
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
	}
	
}
