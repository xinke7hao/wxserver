<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="shortcut icon" href="images/favicon.ico">
	<link href="css/simpleaccordion.css" rel="stylesheet" />
	<link href="css/main.css" rel="stylesheet" />
	<link href="css/datatable.min.css" rel="stylesheet" />
	<link href="js/jquery/jquery-ui.min.css" rel="stylesheet" />
	<link href="css/buttons.css" rel="stylesheet" />
	<link href="css/mydialog.css" rel="stylesheet" />
	<script src="js/jquery/jquery.min.js"></script>
	<script src="js/jquery/jquery-ui.min.js"></script>
	<script src="js/jquery/jquery.validate.min.js"></script>
	<script src="js/jquery/jquery.dataTables.min.js"></script>
	<script src="js/validate-message.js"></script>
	<script src="js/common.js"></script>
	<title>Billing Management Center</title>
	<script>
	
		$(function(){

			$("#accordion").accordion();

			//home page init
			$.get($("#menu0").attr("lang"), function(data) {
				$("#mainDiv").html(data);
			});

			// click menu to create/select tab
			$(".menu").click(function(e) {
				var url = $(this).attr("lang");
				var menuid = $(this).attr("id").substr(4);
				if(url==''){
					alert("功能正在建设中.....");
				} else {
					//加载菜单描述说明
					$.get('menudesc?mid='+menuid, function(data) {
						if(data!=null && data.menuDesc!=null && data.menuDesc!=''){
							$("#prompt").html(data.menuDesc);
							$("#promptDiv").show();
						} else {
							$("#prompt").empty();
							$("#promptDiv").hide();
						}
					});
					//加载功能内容
					$.get(url, function(data) {
						$("#mainDiv").html(data);
					});
				}
			});
			
			//menu click
			$("#accordion li a").click(function() {
				var link = $(this);
				var closest_li = link.closest("li");
				var link_status = closest_li.hasClass("active");
				if (!link_status) {
					$("#accordion li").removeClass("active");
					closest_li.addClass("active");
				}
			});

			//menu collapse/expand
			$("#menuShow").click(function() {
				if ($(this).html() == "<img src=\"images/collapse.png\">") {
					$("#accordion").hide('slide', {direction : 'left'});
					$(this).html("<img src=\"images/expand.png\">");
				} else {
					$("#accordion").show('slide', {direction : 'left'});
					$(this).html("<img src=\"images/collapse.png\">");
				}
			});
		});
	</script>
</head>
<body>
	<%@include file="/top.jsp" %>
	<div style="overflow: hidden;">
		<div id="accordion" style="float: left;margin-right: 2px;overflow: hidden;">
			<h3><a href="#">系统主页</a></h3>
			<ul><li><a id="menu0" class="menu" lang="home">系统主页</a></li></ul>
			<c:forEach items="${barMap}" var="bar">
				<h3><a href="#">${bar.moduleName}</a></h3>
				<ul>
					<c:forEach items="${bar.menus}" var="m">
						<li><a id="menu${m.menuId}" lang="${m.menuUrl}/list" class="menu">${m.menuName}</a></li>
					</c:forEach>
				</ul>
			</c:forEach>
		</div>
		<div id="contentDiv">
			<div id="promptDiv">
				<img src='images/info.png' style='vertical-align:top;width:20px;height:20px;' />
				<label id="prompt"></label>
			</div>
			<div id="mainDiv"></div>
		</div>
	</div>
	<%@include file="/foot.jsp" %>
</body>
</html>