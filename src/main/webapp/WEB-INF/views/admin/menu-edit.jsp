<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			menuDesc : {maxlength: 200}
		}, 
		errorPlacement: function(error, element) {
			error.insertAfter(element);
		}
	});
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<form:form commandName="menuForm" id="inputForm" htmlEscape="true" >
<form:hidden path="menuId" />
<fieldset class="dialog-form-fieldset">
		<p>
			<label class="inputlabel">模块名称: </label>
			<input type="text" class="stringfield" value="${menuForm.moduleName}" disabled="disabled" />
		</p><p>
			<label class="inputlabel">菜单名称: </label>
			<input type="text" class="stringfield" value="${menuForm.menuName}" disabled="disabled" />
		</p>
		<p>
			<form:label path="menuDesc" class="inputlabel">描述说明: </form:label>
			<form:textarea path="menuDesc" style="width: 300px; height: 100px;" />
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>