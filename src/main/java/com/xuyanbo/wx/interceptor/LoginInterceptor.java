/************************************************************************************
 * @File name   :      LoginInterceptor.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月11日
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
 * XuYanbo				1.0				Initial Version				2014年11月11日 上午10:56:02
 ************************************************************************************/
package com.xuyanbo.wx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.entity.admin.User;

/**
 * 登录验证拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * @Author：XuYanbo
	 * @Date：2014年11月11日
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
		if(user==null){
			response.sendRedirect(request.getContextPath() + "/tologin");
			return false;
		}
		
		return true;
	}

	/**
	 * @Author：XuYanbo
	 * @Date：2014年11月11日
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
	 * @Date：2014年11月11日
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
