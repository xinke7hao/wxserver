<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			name: { required: true },
			uuid: { required: true },
			fee_percent: { required: true, number:true, range:[0,1] },
			fee_per_trans: { required: true, number:true, range:[0,10000000] },
			discount: { required: true, number:true, range:[0,1] }
		}, 
		errorPlacement: function(error, element) {
			error.insertAfter(element);
		}
	});
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<form:form commandName="channelForm" id="inputForm" htmlEscape="true">
	<form:hidden path="id" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="name" class="inputlabel"><font class="requiredMark">*</font>名称: </form:label>
			<form:input path="name" class="stringfield" />
		</p>
		<p>
			<form:label path="uuid" class="inputlabel"><font class="requiredMark">*</font>UUID: </form:label>
			<form:input path="uuid" class="stringfield" />
		</p>
		<p>
			<form:label path="fee_percent" class="inputlabel"><font class="requiredMark">*</font>手续费比例: </form:label>
			<form:input path="fee_percent" class="stringfield-short" />
		</p>
		<p>
			<form:label path="fee_per_trans" class="inputlabel"><font class="requiredMark">*</font>手续费金额: </form:label>
			<form:input path="fee_per_trans" class="stringfield-short" />
		</p>
		<p>
			<form:label path="discount" class="inputlabel"><font class="requiredMark">*</font>折扣: </form:label>
			<form:input path="discount" class="stringfield-short" />
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>