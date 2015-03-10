<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$("#chkAllGroups").click(function(){
	var flag = $(this).prop("checked");
	$("input[type='checkbox']").each(function(){
		$(this).prop("checked", flag);
	});
});
</script>
<input type="hidden" id="groupUserId" value="${user.userId}" />
<input type="hidden" id="groupIds" />
<fieldset style="padding: 5px; margin-left: 0px; margin: 15px; color: #333; border: #06c dashed 1px;">
	<legend>
		&nbsp;&nbsp;${user.userName} <label><input type="checkbox" id="chkAllGroups" /> 全选 </label>
	</legend>
	<c:forEach items="${userGroups}" var="ur">
		<p style="margin-top: 5px; margin-bottom: 5px;">
			<label><input type="checkbox" name="rchks" value="${ur.groupId}" <c:if test="${ur.userId!=null}">checked</c:if> />${ur.groupName}</label>
		</p>
	</c:forEach>
</fieldset>
