<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<form:form commandName="accountForm" id="inputForm" htmlEscape="true">
	<form:hidden path="id" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="passport" class="inputlabel">通行证: </form:label>
			<form:input path="passport" class="stringfield-long" disabled="true" />
		</p>
		<p>
			<form:label path="point" class="inputlabel">积分: </form:label>
			<form:input path="point" class="stringfield" disabled="true" />
		</p>
		<p>
			<form:label path="level" class="inputlabel">等级: </form:label>
			<form:input path="level" class="stringfield" disabled="true" />
		</p>
		<p>
			<form:label path="mgb" class="inputlabel">芒果币: </form:label>
			<form:input path="mgb" class="stringfield" disabled="true" />
		</p>
		<p>
			<form:label path="business_str" class="inputlabel">业务: </form:label>
			<form:input path="business_str" class="stringfield" disabled="true" />
		</p>
		<p>
			<label path="roleStatus" class="inputlabel">状态: </label>
			<form:select path="status">
				<form:option value="0">正常</form:option>
				<form:option value="1">禁用</form:option>
				<form:option value="2">超级</form:option>
			</form:select>
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>