<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$(function(){
		var qstr = $("#qualities_str").val();
		var arr = qstr.split("|");
		$("input[type='checkbox'][name='quality_ids']").each(function() {
			for (var i = 0; i < arr.length; i++) {
				if (arr[i] == $(this).attr("title")){
					$(this).prop("checked", "checked");
				}
			}
		});
	});
	$("#inputForm").validate({
		rules : {
			name : "required",
			weight : {
				required : true,
				digits : true,
				range : [ 1, 5000 ]
			},
			quality_ids : "required"
		},
		errorPlacement : function(error, element) {
			if (element.is(":checkbox")) {
				$("#chkerror").html("<font color='#EA5200'><b>必填</b></font>");
			} else {
				error.insertAfter(element);
			}
		}
	});
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<form:form commandName="vipForm" id="inputForm" htmlEscape="true">
	<form:hidden path="id" />
	<form:hidden path="qualities_str" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="name" class="inputlabel"><font class="requiredMark">*</font>名称: </form:label>
			<form:input path="name" class="stringfield" />
		</p>
		<p>
			<form:label path="weight" class="inputlabel"><font class="requiredMark">*</font>权重: </form:label>
			<form:input path="weight" class="stringfield" />
		</p>
		<p>
			<form:label path="quality_ids" class="inputlabel"><font class="requiredMark">*</font>清晰度: </form:label>
			<label id="chkerror"></label>
			<c:forEach items="${bitMap}" var="m">
			<label><input type="checkbox" name="quality_ids" onclick="if(this.checked) $('#chkerror').empty();" value="${m.key}" title="${m.value}">${m.value}</label>
			</c:forEach>
		</p>
		<p>
			<label>&nbsp;</label>
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>