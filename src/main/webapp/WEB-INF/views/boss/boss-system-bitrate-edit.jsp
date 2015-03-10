<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			name: "required"
		},
		errorPlacement: function(error, element) {
			error.insertAfter(element);
		}
	});
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<form:form commandName="bitForm" id="inputForm" htmlEscape="true">
	<form:hidden path="id" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="name" class="inputlabel"><font class="requiredMark">*</font>名称: </form:label>
			<form:input path="name" class="stringfield" />
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>