<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	$(function(){
		
		//加载主页工作台内容
		$.get("loadDesktop", function(data){
			$("#desktopDiv").html(data);
		});
		
	});
</script>
欢迎使用支付管理中心系统,这里是不同工作台的通用部分.
<div id="desktopDiv" style="padding-top: 10px;"></div>
