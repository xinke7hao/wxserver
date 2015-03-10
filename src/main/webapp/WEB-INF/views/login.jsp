<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="shortcut icon" href="./images/favicon.ico">
<link href="./css/login.css" rel="stylesheet" />
<script src="./js/jquery/jquery.min.js"></script>
<script src="./js/jquery/jquery.cookie.min.js"></script>
<script src="./js/base64.js"></script>
<title>Login Form</title>
<script>

	var b = new Base64();  

	function resetUserCookie(){
		$.cookie("ucode", b.encode($("#usercode").val()), { expires: 7 });
		$.cookie("rempass", $("#remember_me").prop("checked"), { expires: 7 });	
	}

	function setUserInputs(){
		var code = $.cookie("ucode");
		var rempass = $.cookie("rempass");
		if(code){
			$("#usercode").val(b.decode(code));
			$("#remember_me").prop("checked", rempass);
		}
	}
	
	$(function() {
		
		/****************
		navigator
		appCodeName       	浏览器的代码名。    
		appMinorVersion    	浏览器的次级版本。    
		appName             浏览器的名称。    
		appVersion          浏览器的平台和版本信息。    
		browserLanguage    	当前浏览器的语言。    
		cookieEnabled      	指明浏览器中是否启用 cookie 的布尔值。    
		cpuClass            浏览器系统的 CPU 等级。    
		onLine              指明系统是否处于脱机模式的布尔值。    
		platform            运行浏览器的操作系统平台。    
		systemLanguage    	OS 使用的默认语言。    
		userAgent           由客户机发送服务器的 user-agent 头部的值。    
		userLanguage   		OS 的自然语言设置。
		****************/
		
		//浏览器版本判断(仅判断IE系列,是否低于IE9)
		//Microsoft Internet Explorer/Netscape/Opera/Firefox/Chrome
		var browserIsIE = navigator.appName.indexOf("Microsoft Internet Explorer")!=-1;
		if(browserIsIE){
			var browserVersion = navigator.userAgent.split(";")[1].toLowerCase();
		    var lowBrowser = browserVersion.indexOf("msie 6.0")!=-1 || browserVersion.indexOf("msie 7.0")!=-1 || browserVersion.indexOf("msie 8.0")!=-1;
		    if(lowBrowser){
		    	$("#browser_prompt").show();
		    }
		}
		
		setUserInputs();
		
		$("#usercode").focus();
		
		//Enter press event
		$("#loginForm").keypress(function(e){
			var key = e.which;
			if(key==13){
				$("#subBtn").click();
				return false;
			}
		});

		$("#subBtn").click(function() {
			
			//验证码
// 			if($("#verifycode").val().trim()==""){
// 				$('#login-error').html("验证码为空!");
// 				return false;
// 			}
			
			if ($("#usercode").val() == "") {
				$('#login-error').html("用户名不能为空!");
			} else if ($("#userpass").val() == "") {
				$('#login-error').html("密码不能为空!");
			} else {
				$('#login-error').html("");
				if($("#remember_me").prop("checked")){
					resetUserCookie();
				} else {
					$.removeCookie('ucode');
					$.removeCookie('rempass');
				}
				//密码字段加密提交
				$("#userpass").val(b.encode($("#userpass").val()));
				$("#loginForm").submit();
			}
			return false;
		});

		//show login error
		var errorCode = "${errorCode}";
		if (errorCode != "") {
			var errorMsg = (errorCode == "1" ? "用户名不存在"	: (errorCode == "2" ? "用户名密码不匹配" : (errorCode == "3" ? "用户被锁定,请联系管理员" :"验证码不正确")));
			$('#login-error').html(errorMsg);
		}
	});
</script>
</head>
<body>
<form:form action="login" commandName="loginuser" id="loginForm" method="post">
  <div class="login">
    <h1>Login to BMC</h1>
  	<div id="login-error" style="height: 12px;"></div>
      <p><img src="./images/account.png" /><input type="text" id="usercode" name="userCode" placeholder="请输入用户名" value="${userCode}" /></p>
      <p><img src="./images/pass.png" /><input type="password" id="userpass" name="userPass" placeholder="请输入密码" /></p>
<!--       <p style="text-align:right;"> -->
<!--       <img src="captcha" style="width: 110px; height: 32px; cursor: pointer; " title="看不清,换一张" onclick="this.src='captcha';" /> -->
<!--       <input type="text" id="verifycode" name="verifyCode" placeholder="验证码" maxlength="4" style="width: 60px; margin-right: 8px;" /></p> -->
      <p class="remember_me">
        <label><input type="checkbox" id="remember_me" name="remember_me">记住用户名</label>
      </p>
      <p class="submit"><input id="subBtn" type="button" name="commit" value="Login"></p>
  </div>
</form:form>

<div class="login-help">
  <p>Powered by <a href="http://www.hunantv.com">Hunantv.com</a></p>
</div>
<div id="browser_prompt" style="display:none;" class="login-help">
  <p>你的浏览器版本过低，建议使用更高版本的浏览器。</p>
</div>
</body>
</html>
