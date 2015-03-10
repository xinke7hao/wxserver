<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			moduleId: "required",
			menuId: "required",
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
<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="moduleId" class="inputlabel"><font class="requiredMark">*</font>模块: </form:label>
			<form:select path="moduleId" id="cModuleId" onchange="loadChilds();">
				<form:option label="---请选择---" value="" />
				<c:forEach items="${modules}" var="m">
					<option value="${m.moduleId}">${m.moduleName}</option>
				</c:forEach>
			</form:select>
		</p>
		<p>
			<form:label path="menuId" class="inputlabel"><font class="requiredMark">*</font>菜单: </form:label>
			<form:select path="menuId" id="cMenuId" />
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