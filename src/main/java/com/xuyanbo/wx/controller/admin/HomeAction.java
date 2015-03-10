/************************************************************************************
 * @File name   :      HomeAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年10月28日
 *
 * @Copyright Notice: 
 * Copyright (c) 2014 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2014年10月28日 5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.xuyanbo.wx.controller.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.xuyanbo.wx.commons.ApplicationConfigs;
import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.commons.ErrorConstants;
import com.xuyanbo.wx.controller.BaseAction;
import com.xuyanbo.wx.dto.MenuBar;
import com.xuyanbo.wx.dto.MenuDesc;
import com.xuyanbo.wx.entity.admin.LoginLog;
import com.xuyanbo.wx.entity.admin.User;
import com.xuyanbo.wx.service.GroupService;
import com.xuyanbo.wx.service.MenuService;
import com.xuyanbo.wx.service.SystemLogService;
import com.xuyanbo.wx.service.UserService;
import com.xuyanbo.wx.utils.BmcUtils;

@Controller
@RequestMapping("/")
public class HomeAction extends BaseAction {

	@Resource
	private SystemLogService systemLogService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private MenuService menuService;
	
	@Resource
	private GroupService groupService;
	
	/**
	 * 跳转登录页面
	 * @Author：XuYanbo
	 * @Date：2014年11月11日
	 * @param userCode
	 * @param userName
	 * @return
	 */
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(){
		return "login";
	}

	/**
	 * 执行登录,成功后加载首页
	 * @Author：XuYanbo
	 * @Date：2014年11月11日
	 * @param request
	 * @param userCode
	 * @param userName
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, @ModelAttribute("loginuser") User logUser, Model model) {
		
		int errorCode = 0;
		String desc = "登录成功";
		HttpSession session = request.getSession();
		LoginLog log = new LoginLog();
		log.setLoginUser(logUser.getUserCode());
		log.setLoginIp(BmcUtils.getRealClientIP(request));
		
		try {
			
			//Base64 decrypt & MD5 encrypt
			String password = DigestUtils.md5Hex(new String(Base64.decodeBase64(logUser.getUserPass())));
			User user = userService.getUserByCode(logUser.getUserCode());
			if(user==null){
				errorCode = ErrorConstants.LOGIN_ERROR_NOUSER;
				desc = ErrorConstants.LOGIN_ERROR_NOUSER_DESC;
			} else if(!user.getUserPass().equals(password)) {
				errorCode = ErrorConstants.LOGIN_ERROR_WRONG_PASSWORD;
				desc = ErrorConstants.LOGIN_ERROR_WRONG_PASSWORD_DESC;
			} else if(!BmcConstants.YES.equals(user.getUserStatus())){
				errorCode = ErrorConstants.LOGIN_ERROR_USER_LOCKED;
				desc = ErrorConstants.LOGIN_ERROR_USER_LOCKED_DESC;
			} else {
				session.setAttribute(BmcConstants.LOGIN_USER, user);
			}
			
			log.setLoginStatus(errorCode+"");
			log.setLoginDesc(desc);
			systemLogService.saveLoginLog(log);
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		if(errorCode == ErrorConstants.LOGIN_SUCCESS){
			return "redirect:/index";
		} else {
			model.addAttribute("errorCode", errorCode);
			model.addAttribute("userCode", logUser.getUserCode());
			return "login";
		}
	}
	
	/**
	 * Session超时退出中转
	 * @Author：XuYanbo
	 * @Date：2014年12月29日
	 * @return
	 */
	@RequestMapping(value="tologin", method=RequestMethod.GET)
	public String tologin(){
		return "logout";
	}
	
	/**
	 * 退出,注销用户
	 * @Author：XuYanbo
	 * @Date：2014年11月13日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(BmcConstants.LOGIN_USER) != null){
			session.removeAttribute(BmcConstants.LOGIN_USER);
		}
		
		session.invalidate();

		return "redirect:/login";
	}
	
	/**
	 * 加载首页
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		
		try {
			User user = (User)request.getSession().getAttribute(BmcConstants.LOGIN_USER);
			if(user!=null){
				
				//user-menu-rights
				Map<String, String> userAuthorities = menuService.getUserMenuRightMap(user.getUserId(), user.getIsAdmin());
				request.getSession().setAttribute(BmcConstants.USER_MENU_RIGHT, userAuthorities);
				
				//menu-bar
				List<MenuBar> barMap = menuService.getMenus(user.getUserId(), user.getIsAdmin());
				model.addAttribute("barMap", barMap);
				
				//group-data
				Map<String, String> dataMap = groupService.getUserGroupDatas(user.getUserId(), user.getIsAdmin());
				request.getSession().setAttribute(BmcConstants.USER_GROUP_DATA, dataMap);
				
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return "main";
	}
	
	/**
	 * 加载Home
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="home", method=RequestMethod.GET)
	public String home(){
		return "home";
	}
	
	/**
	 * 加载工作台内容
	 * @Author：XuYanbo
	 * @Date：2014年12月26日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="loadDesktop", method=RequestMethod.GET)
	public String loadDesktop(HttpServletRequest request, Model model){
		User user = getCurrentUser(request);
		String location = BmcConstants.USER_TYPE_ADMIN == user.getUserType() ? "admin-desktop" : (BmcConstants.USER_TYPE_LEADER == user.getUserType() ? "leader-desktop" : "common-desktop");
		return location;
	}
	
	/**
	 * 跳转更改密码页面
	 * @Author：XuYanbo
	 * @Date：2014年12月3日
	 * @return
	 */
	@RequestMapping(value="topass", method=RequestMethod.GET)
	public String viewPass(){
		return "change-pass";
	}
	
	/**
	 * 验证密码
	 * @param uid
	 * @param pass
	 * @return
	 */
	@RequestMapping(value="checkpass", method=RequestMethod.POST)
	@ResponseBody
	public String checkPass(@RequestParam("uid") Integer uid, @RequestParam("pass") String pass){
		String result = BmcConstants.FALSE;
		pass = new String(Base64.decodeBase64(pass.getBytes()));
		try {
			if(uid!=null && StringUtils.isNotBlank(pass)){
				String userpass = userService.getPassById(uid);
				String md5pass = DigestUtils.md5Hex(pass);
				if(md5pass.equals(userpass)){
					result = BmcConstants.TRUE;
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 保存更改密码
	 * @Author：XuYanbo
	 * @Date：2014年12月3日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="savepass", method=RequestMethod.POST)
	@ResponseBody
	public String savePass(@RequestParam("pass") String newpass, @RequestParam("uid") Integer uid){
		try {
			if(uid!=null && StringUtils.isNotBlank(newpass)){
				User u = userService.get(uid);
				u.setUserPass(DigestUtils.md5Hex(HtmlUtils.htmlUnescape(newpass)));
				userService.updatePassById(u);
			}
		} catch (Exception e){
			e.printStackTrace();
			return BmcConstants.NO;
		}

		return BmcConstants.YES;
	}
	
	/**
	 * 跳转更改密码页面
	 * @Author：XuYanbo
	 * @Date：2014年12月3日
	 * @return
	 */
	@RequestMapping(value="menudesc", method=RequestMethod.GET)
	@ResponseBody
	public Object menudesc(@RequestParam("mid") Integer menuId){
		String desc = ApplicationConfigs.menuDescMap.get(menuId);
		return new MenuDesc(menuId, desc);
	}
}
