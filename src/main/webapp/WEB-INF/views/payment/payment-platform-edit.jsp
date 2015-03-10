<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			platform_name: "required",
			secret: "required"
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
<form:form commandName="platformForm" id="inputForm" htmlEscape="true">
	<form:hidden path="platform_id" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="platform_name" class="inputlabel"><font class="requiredMark">*</font>业务平台名: </form:label>
			<form:input path="platform_name" class="stringfield" />
		</p>
		<p class="taller">
			<form:label path="remark" class="inputlabel">业务平台简介: </form:label>
			<form:textarea path="remark" class="stringarea" />
		</p>
		<p>
			<form:label path="secret" class="inputlabel"><font class="requiredMark">*</font>Secret: </form:label>
			<form:input path="secret" class="stringfield-long" />
		</p>
		<p class="taller">
			<form:label path="ext_data" class="inputlabel">附加信息(JSON): </form:label>
			<form:textarea path="ext_data" class="stringarea" />
		</p>
		<p>
			<form:label path="contact" class="inputlabel">接口人: </form:label>
			<form:input path="contact" class="stringfield-long" />
		</p>
		<p>
			<form:label path="status" class="inputlabel">状态: </form:label>
			<label class="selection"><form:radiobutton path="status" value="1" />可用</label>
			<label class="selection"><form:radiobutton path="status" value="0" />不可用</label>
			<label class="selection"><form:radiobutton path="status" value="9" />已删除</label>
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>