<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			departCode: { required: true, maxlength: 20, alphanumeric: true	},
			departName: {required: true, maxlength: 30}
		},
		errorPlacement: function(error, element) {
			error.insertAfter(element);
		}
	});
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<form:form commandName="departForm" id="inputForm" method="post">
<form:hidden path="departId" />
<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="departCode" class="inputlabel"><font class="requiredMark">*</font>部门代码: </form:label>
			<form:input path="departCode" class="stringfield" />
		</p>
		<p>
			<form:label path="departName" class="inputlabel"><font class="requiredMark">*</font>部门名称: </form:label>
			<form:input path="departName" class="stringfield" />
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>
