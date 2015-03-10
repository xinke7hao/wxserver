<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			opercodes: "required"
		},
		errorPlacement: function(error, element) {
			error.insertAfter(element.parent().next().next());
		}
	});
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<form:form commandName="cmd" id="inputForm" action="boss/content/pack/savemode" htmlEscape="true">
	<form:hidden path="pid" />
	<form:hidden path="aids" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<label class="inputlabel"><font class="requiredMark">*</font>运营方式: </label>
			<label class="selection"><input type="checkbox" name="opercodes" value="1" />VIP运营</label>
			<label class="selection"><input type="checkbox" name="opercodes" value="2" />单点产品运营</label>
			<label class="selection"><input type="checkbox" name="opercodes" value="3" />集合产品运营</label>
		</p>
		<p>
			<label class="inputlabel">角标: </label>
			<select name="markid">
				<option value="">无角标</option>
				<c:if test="${tags!=null && tags.size()>0}">
				<c:forEach items="${tags}" var="m">
					<option value="${m.id}">${m.name}</option>
				</c:forEach>
				</c:if>
			</select>
		</p>
		<p>
			<label class="inputlabel">试看: </label>
			<label class="selection"><input type="radio" name="preview" value="1" checked />是</label>
			<label class="selection"><input type="radio" name="preview" value="0" />否</label>
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>