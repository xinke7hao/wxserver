/************************************************************************************
 * @File name   :      SmsAsyncNotify.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月5日
 *
 * @Copyright Notice: 
 * Copyright (c) 2015 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Who					Version				Comments				Date				
 * XuYanbo				1.0				Initial Version				2015年1月5日 上午9:47:46
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * 9588异步通知
 * @author XuYanbo
 *
 */
public class SmsAsyncNotify extends BaseDataObject implements Comparable<SmsAsyncNotify> {
	
	private static final long serialVersionUID = 8742348613419387723L;
	
	private Long id;
	private String connect_id;
	private String message_time;
	private String mobile;
	private String message;
	private String order_parameter;
	private String type;
	private String create_time;
	
	//for query
	private String startDay;
	private String endDay;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConnect_id() {
		return connect_id;
	}
	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}
	public String getMessage_time() {
		return message_time;
	}
	public void setMessage_time(String message_time) {
		this.message_time = message_time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOrder_parameter() {
		return order_parameter;
	}
	public void setOrder_parameter(String order_parameter) {
		this.order_parameter = order_parameter;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public int compareTo(SmsAsyncNotify o) {
		return o!=null && o.getId()!=null ? o.getId().compareTo(this.getId()) : 0;
	}
}
