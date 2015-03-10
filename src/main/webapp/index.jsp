<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
var uc = "${loginUser.userCode}";
window.location=uc==""?"login":"index";
</script>