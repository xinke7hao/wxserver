<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			name: "required",
			days: { required: true, digits: true, min: 1, max:3650},
			yuan_price: { required: true, number: true, min:0, max:1000000}
		},
		errorPlacement: function(error, element) {
			error.insertAfter(element);
		}
	});
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<form:form commandName="singleForm" id="inputForm" htmlEscape="true">
	<form:hidden path="id" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="name" class="inputlabel"><font class="requiredMark">*</font>名称: </form:label>
			<form:input path="name" class="stringfield" />
		</p>
		<p>
			<form:label path="days" class="inputlabel"><font class="requiredMark">*</font>时长(天): </form:label>
			<form:input path="days" class="stringfield-short" />
		</p>
		<p>
			<form:label path="yuan_price" class="inputlabel"><font class="requiredMark">*</font>价格(元): </form:label>
			<form:input path="yuan_price" class="stringfield-short" />
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>