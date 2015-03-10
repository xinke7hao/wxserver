/************************************************************************************
 * @File name   :      RequestParameterFilter.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月2日
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
 * XuYanbo				1.0				Initial Version				2014年12月2日 下午4:14:03
 ************************************************************************************/
package com.xuyanbo.wx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.entity.admin.User;

/**
 * 请求参数过滤
 * 防止SQL注入等非法字符
 * @author XuYanbo
 * 2014-12-2
 *
 */
public final class MonitoringFilter implements Filter {

	/**
	 * 验证是否已经登录并且是管理员
	 * @Author：XuYanbo
	 * @Date：2014年12月26日
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		User user = (User)(((HttpServletRequest)request).getSession().getAttribute(BmcConstants.LOGIN_USER));
		if(null==user || !BmcConstants.YES.equals(user.getIsAdmin())){
			((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void destroy() {}

}
