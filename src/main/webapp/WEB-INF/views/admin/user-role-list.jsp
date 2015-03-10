<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$("#chkAllRoles").click(function(){
	var flag = $(this).prop("checked");
	$("input[type='checkbox']").each(function(){
		$(this).prop("checked", flag);
	});
});
</script>
<input type="hidden" id="roleUserId" value="${user.userId}" />
<input type="hidden" id="roleIds" />
<fieldset style="padding: 5px; margin-left: 0px; margin: 15px; color: #333; border: #06c dashed 1px;">
	<legend>
		&nbsp;&nbsp;${user.userName} <label><input type="checkbox" id="chkAllRoles" /> 全选 </label>
	</legend>
	<c:forEach items="${userRoles}" var="ur">
		<p style="margin-top: 5px; margin-bottom: 0px;">
			<label><input type="checkbox" name="rchks" value="${ur.roleId}" <c:if test="${ur.userId!=null}">checked</c:if> />${ur.roleName}</label>
		</p>
	</c:forEach>
</fieldset>
