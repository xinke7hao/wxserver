<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<link href="${ctx}/css/error.css" rel="stylesheet" />
<link rel="shortcut icon" href="${ctx}/images/favicon.ico">
<title>405 ~ Method not allowed</title>
</head>
<body>
<div class="error_page">
    <img src="${ctx}/images/error_face_sad.gif">
    <h1>We're sorry...</h1>
    <p>The page you are looking for contains method not allowed, <span>Error Code: 405</span>.</p>
    <p><a href="javascript:history.go(-1);">Back</a> or <a href="login">Return to the Login</a></p>
  </div>
</body>
</html>