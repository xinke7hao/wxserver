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
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.util.HtmlUtils;

/**
 * 请求参数过滤
 * 防止SQL注入等非法字符
 * @author XuYanbo
 * 2014-12-2
 *
 */
public final class RequestParameterFilter implements Filter {

	static class ParamaterRequestWrapper extends HttpServletRequestWrapper {

		public ParamaterRequestWrapper(ServletRequest request) {
			super((HttpServletRequest)request);
		}

//		减小判断逻辑增强服务器性能暂屏蔽
//		@Override
//		public String getParameter(String name) {
//			return sanitize(super.getParameter(name));
//		}

		@Override
		public String[] getParameterValues(String name) {
			String[] values = super.getParameterValues(name);
			if(null != values){
				for (int index = 0; index < values.length; index++) {
//					values[index] = SpecialCharacterUtil.sanitize(values[index]);
					values[index] = HtmlUtils.htmlEscape(values[index]);
				}
			}
			return values;
		}
		
	}

	/**
	 * 过滤非法字符转发请求
	 * @Author：XuYanbo
	 * @Date：2014年12月2日
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		filterChain.doFilter(new ParamaterRequestWrapper(request), response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void destroy() {}

}
