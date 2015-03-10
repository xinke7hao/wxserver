/************************************************************************************
 * @File name   :      ResultPage.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年10月31日
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
 * XuYanbo				1.0				Initial Version				2014年10月31日 下午2:51:18
 ************************************************************************************/
package com.hunantv.mbp.commons;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.hunantv.mbp.utils.BmcUtils;

/**
 * 分页对象
 */
public class PageInfo<T> {
	
	private int secho = -1;
    private long total = 0;
    private int page = 1;
	private List<T> list = new ArrayList<T>();
	
    private int pageSize = BmcConstants.PAGE_DEFAULT_SIZE;
    private int startRow = 0;
    
    private String order = BmcConstants.DESC;
    private String orderBy;

    public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public PageInfo(){}
	public PageInfo(int type){
		this.startRow = BmcConstants.CSV_START_ROW;
		this.pageSize = BmcConstants.CSV_EXPORT_MAX;
	}
    
	/**
	 * 打印请求参数内容(仅用于开发调试)
	 * @Author：XuYanbo
	 * @Date：2014年11月19日
	 * @param request
	 */
//	private void showRequestParams(HttpServletRequest request){
//		 Map map = new HashMap();
//         Enumeration paramNames = request.getParameterNames();
//         while (paramNames.hasMoreElements()) {
//             String paramName = (String) paramNames.nextElement();
//
//             String[] paramValues = request.getParameterValues(paramName);
//             if (paramValues.length == 1) {
//                 String paramValue = paramValues[0];
//                 if (paramValue.length() != 0) {
//                     map.put(paramName, paramValue);
//                 }
//             }
//         }
//
//         Set<Map.Entry<String, String>> set = map.entrySet();
//         System.out.println("******************************");
//         for (Map.Entry entry : set) {
//             System.out.println(entry.getKey() + ":" + entry.getValue());
//         }
//         System.out.println("******************************");
//	}
	
	/**
	 * 页面请求构建对象
	 * @param request
	 */
    public PageInfo(HttpServletRequest request){
    	
//    	showRequestParams(request);	//Test
    	this.secho = Integer.parseInt(request.getParameter(BmcConstants.DATATABLE_REQUEST_DRAW));
    	this.startRow = Integer.parseInt(request.getParameter(BmcConstants.DATATABLE_REQUEST_STARTROW));
    	this.pageSize = Integer.parseInt(request.getParameter(BmcConstants.DATATABLE_REQUEST_PAGESIZE));
    	if(startRow!=0){
    		int start = startRow + 1;
    		this.page = (start%pageSize==0 ? start/pageSize : start/pageSize+1);
    	}
    }
    
    /**
     * Jersey服务返回结构构建对象
     * 接口返回结果暂不排序
     * @param jsonResult
     */
    public void setData(HttpResult<T> restResult){
    	this.total = restResult.getTotalCount();
    	this.list = restResult.getData();
    }
    
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getSecho() {
		return secho;
	}
	public void setSecho(int secho) {
		this.secho = secho;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public void setList(List<T> list, Long total) {
		this.list = list;
		this.total = total;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	
	/**
	 * 是否设置排序列
	 * @return
	 */
	public boolean isOrderSetted(){
		return this.orderBy!=null && ""!=this.orderBy;
	}
	
	/*
	 * 转换成DataTable可接受的JSON串
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("{\"" + BmcConstants.DATATABLE_SECHO + "\":" + this.secho + ",\"" + BmcConstants.DATATABLE_ITOTAL_RECORDS + "\":" 
				+ this.total + ",\"" + BmcConstants.DATATABLE_ITOTAL_DISPLAYRECORDS + "\":" + this.total);
		if(list!=null){
			s.append(",\"" + BmcConstants.DATATABLE_AADATA + "\":"+JSON.toJSONString(list, BmcUtils.features));
		}
		s.append("}");
		return s.toString();
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
