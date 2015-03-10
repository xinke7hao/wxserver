<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			name: "required",
			kind: "required",
			default_flag: "required"
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
<form:form commandName="tagForm" id="inputForm" htmlEscape="true">
	<form:hidden path="id" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="name" class="inputlabel"><font class="requiredMark">*</font>角标名称: </form:label>
			<form:input path="name" class="stringfield" />
		</p>
		<p>
			<form:label path="kind" class="inputlabel"><font class="requiredMark">*</font>角标类别: </form:label>
			<label class="selection"><form:radiobutton path="kind" value="0" />单点</label>
			<label class="selection"><form:radiobutton path="kind" value="1" />VIP</label>
		</p>
		<p>
			<form:label path="default_flag" class="inputlabel"><font class="requiredMark">*</font>是否默认: </form:label>
			<label class="selection"><form:radiobutton path="default_flag" value="1" />是</label>
			<label class="selection"><form:radiobutton path="default_flag" value="0" />否</label>
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>