/************************************************************************************
 * @File name   :      BossContentCommand.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月12日
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
 * XuYanbo				1.0				Initial Version				2015年1月12日 上午11:43:08
 ************************************************************************************/
package com.hunantv.mbp.command;

/**
 * @author XuYanbo
 *
 */
public class BossContentCommand {

	private String cid;
	private String cname;
	private String ctype;
	private String single;	//是否单点
	private String charge;	//是否按集收费
	private String cstatus;
	private String starttime;
	private String endtime;
	private String onlyValid;	//是否有效
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getSingle() {
		return single;
	}
	public void setSingle(String single) {
		this.single = single;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getCstatus() {
		return cstatus;
	}
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getOnlyValid() {
		return onlyValid;
	}
	public void setOnlyValid(String onlyValid) {
		this.onlyValid = onlyValid;
	}
	
}
