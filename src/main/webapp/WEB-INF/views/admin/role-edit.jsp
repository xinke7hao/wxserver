<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			roleName : {required: true, maxlength: 30},
			roleStatus : "required"
		}, 
		errorPlacement: function(error, element) {
			if ( element.is(":radio") ){
				error.insertAfter( element.parent().next() );
			} else {
			    error.insertAfter(element);
			}
		}
	});
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<form:form commandName="roleForm" id="inputForm" htmlEscape="true">
	<form:hidden path="roleId" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="roleName" class="inputlabel"><font class="requiredMark">*</font>角色名称: </form:label>
			<form:input path="roleName" class="stringfield" />
		</p>
		<p>
			<label path="roleStatus" class="inputlabel"><font class="requiredMark">*</font>状态: </label>
			<label class="selection"><form:radiobutton path="roleStatus" value="Y" />启用</label>
			<label class="selection"><form:radiobutton path="roleStatus" value="N" />禁用</label>
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>