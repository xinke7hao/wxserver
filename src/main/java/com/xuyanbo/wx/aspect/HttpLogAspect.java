package com.xuyanbo.wx.aspect;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.commons.MbpHttpLog;
import com.xuyanbo.wx.entity.admin.SystemLog;
import com.xuyanbo.wx.entity.admin.User;
import com.xuyanbo.wx.service.SystemLogService;
import com.xuyanbo.wx.utils.BmcUtils;

@Component
@Aspect
public class HttpLogAspect {

	@Inject
	private SystemLogService systemLogService;
	
	/**
	 * 在业务方法完成后执行
	 * @author XuYanbo
	 * @param jp
	 * @param rl
	 */
	@AfterReturning(pointcut="within(com.xuyanbo.wx.service..*) && @annotation(rl)")
	public void saveSystemLog(MbpHttpLog rl){
		
		//正常日志只保存增删改操作
		if(StringUtils.isNotBlank(rl.type()) && !rl.type().equals(BmcConstants.LOG_TYPE_QUERY)){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			User user = (User)request.getSession().getAttribute(BmcConstants.LOGIN_USER);
			if(user!=null){
				SystemLog log = new SystemLog();
				log.setModule(rl.module());
				log.setLogStyle(BmcConstants.LOG_STYLE_HTTP);
				log.setLogStatus(BmcConstants.YES);
				log.setLogType(rl.type());
				log.setLogUser(user.getUserCode());
				log.setLogDesc(rl.desc());
				String logIp = BmcUtils.getRealClientIP(request);
				log.setLogIp(logIp);
				try {
					systemLogService.insert(log);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 当业务方法出现异常时执行
	 * @param jp
	 * @param rl
	 * @param ex
	 */
	@AfterThrowing(pointcut="within(com.xuyanbo.wx.service..*) && @annotation(rl)", throwing="ex")
	public void handleException(JoinPoint jp, MbpHttpLog rl, Exception ex){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		User user = (User)request.getSession().getAttribute(BmcConstants.LOGIN_USER);
		if(user!=null){
			SystemLog log = new SystemLog();
			log.setModule(rl.module());
			log.setLogStyle(BmcConstants.LOG_STYLE_HTTP);
			log.setLogStatus(BmcConstants.NO);
			log.setLogType(rl.type());
			log.setLogUser(user.getUserCode());

			String errorMsg = ex.getLocalizedMessage();
			StringBuffer error = new StringBuffer();
			if(StringUtils.isNotBlank(errorMsg) && errorMsg.length()>200){
				error.append(errorMsg.substring(0, 200));
			} else {
				error.append(errorMsg);
			}
			
			log.setLogDesc(rl.desc() + "[" + error + "]");
			String logIp = BmcUtils.getRealClientIP(request);
			log.setLogIp(logIp);
			try {
				systemLogService.insert(log);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
