<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			rightName : {required: true, maxlength: 50},
			rightMethod: {required: true, maxlength: 80},
			rightStatus: "required"
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
<form:form commandName="rightForm" id="inputForm" htmlEscape="true" >
<form:hidden path="rightId" />
<form:hidden path="menuId" />
<form:hidden path="moduleId" />
<fieldset class="dialog-form-fieldset">
		<p>
			<label class="inputlabel"><font class="requiredMark">*</font>模块: </label>
			<input type="text" class="stringfield" value="${rightForm.moduleName}" disabled="disabled" />
		</p><p>
			<label class="inputlabel"><font class="requiredMark">*</font>菜单: </label>
			<input type="text" class="stringfield" value="${rightForm.menuName}" disabled="disabled" />
		</p>
		<p>
			<form:label path="rightName" class="inputlabel"><font class="requiredMark">*</font>功能名称: </form:label>
			<form:input path="rightName" class="stringfield" />
		</p>
		<p>
			<form:label path="rightMethod" class="inputlabel"><font class="requiredMark">*</font>功能方法: </form:label>
			<form:input path="rightMethod" class="stringfield" />
		</p>
		<p>
			<form:label path="rightStatus" class="inputlabel"><font class="requiredMark">*</font>状态: </form:label>
			<label class="selection"><form:radiobutton path="rightStatus" value="Y" />启用</label>
			<label class="selection"><form:radiobutton path="rightStatus" value="N" />禁用</label>
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>