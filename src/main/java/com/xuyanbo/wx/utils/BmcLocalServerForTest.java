/************************************************************************************
 * @File name   :      BmcLocalServerForTest.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年2月2日
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
 * XuYanbo				1.0				Initial Version				2015年2月2日 下午5:12:30
 ************************************************************************************/
package com.xuyanbo.wx.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 模拟HTTP请求响应
 * @author XuYanbo
 */
public class BmcLocalServerForTest {

	/**
	 * 读取json文件,返回响应内容
	 * @Author：XuYanbo
	 * @Date：2015年2月2日
	 * @return
	 */
	public static String getResponseContent(){
		StringBuffer responseStr = new StringBuffer();
//		responseStr.append("{\"msg\": \"\", \"code\": 0, \"data\": [{\"date\": \"201412\", \"money\": \"3\", \"name\": \"\u8292\u679c\u5e01\", \"times\": 1}, {\"date\": \"201501\", \"money\": \"200\", \"name\": \"\u8292\u679c\u5e01\", \"times\": 2}], \"uuid\": \"86a5977535b6407cbb75cb5caefebd91\"}");
		BufferedReader br = null;
		if(responseStr.length()==0){
			try {
				String filepath = "D:/Develop/GitRepository/myserver/src/main/java/com/hunantv/mbp/utils/test.json";
				br = new BufferedReader(new FileReader(new File(filepath)));
				String line = null;
				while((line = br.readLine()) != null){
					responseStr.append(line);
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(br!=null){
					try {
						br.close();
					} catch (Exception e){}
				}
			}
		}
		
		return responseStr.toString();
	}
}
