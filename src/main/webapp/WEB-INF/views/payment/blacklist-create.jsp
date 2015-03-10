<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			item: "required",
			type_id: "required",
			platform_id: "required",
			channel_id: "required",
			status_id: "required"
		}, 
		errorPlacement: function(error, element) {
			if(element.is(":radio")){
				error.insertAfter(element.parent().next());
			} else {
			    error.insertAfter(element);
			}
		}
	});
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<form:form commandName="blacklistForm" id="inputForm" htmlEscape="true">
	<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="item" class="inputlabel"><font class="requiredMark">*</font>黑名单: </form:label>
			<form:input path="item" class="stringfield" />
		</p>
		<p>
			<form:label path="type_id" class="inputlabel"><font class="requiredMark">*</font>名单类型: </form:label>
			<form:select path="type_id">
				<form:option value="">------</form:option>
				<form:option value="0">手机号</form:option>
				<form:option value="1">账号</form:option>
				<form:option value="2">MAC</form:option>
				<form:option value="3">License</form:option>
			</form:select>
		</p>
		<p>
			<form:label path="platform_id" class="inputlabel"><font class="requiredMark">*</font>业务平台: </form:label>
			<form:select path="platform_id">
				<form:option value="">------</form:option>
				<form:options items="${platforms}" itemLabel="dataName" itemValue="dataId" />
			</form:select>
		</p>
		<p>
			<form:label path="channel_id" class="inputlabel"><font class="requiredMark">*</font>支付渠道: </form:label>
			<form:select path="channel_id">
				<form:option value="">------</form:option>
				<form:options items="${channels}" itemLabel="dataName" itemValue="dataId" />
			</form:select>
		</p>
		<p>
			<form:label path="status_id" class="inputlabel">状态: </form:label>
			<label class="selection"><form:radiobutton path="status_id" value="1"  />有效</label>
			<label class="selection"><form:radiobutton path="status_id" value="0" />无效</label>
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>