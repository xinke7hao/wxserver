/************************************************************************************
 * @File name   :      BossSingleCommand.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年2月25日
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
 * XuYanbo				1.0				Initial Version				2015年2月25日 上午10:14:26
 ************************************************************************************/
package com.hunantv.mbp.command;

/**
 * @author XuYanbo
 *
 */
public class BossPackCommand {

	private String pid;
	private String aids;
	private String opercodes;
	private String markid;
	private String preview;
	
	public String getOpercodes() {
		return opercodes;
	}
	public void setOpercodes(String opercodes) {
		this.opercodes = opercodes;
	}
	public String getMarkid() {
		return markid;
	}
	public void setMarkid(String markid) {
		this.markid = markid;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getAids() {
		return aids;
	}
	public void setAids(String aids) {
		this.aids = aids;
	}
	
}
