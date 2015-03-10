<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(function(){
	$("#inputForm").validate({
		rules : {
			start: { required: true, digits: true},
		},
		errorPlacement: function(error, element) {
			error.insertAfter(element);
		}
	});
	
	if($("#mid").val()!="undefined"){
		$("#mark_id").val($("#mid").val());	
	}
});
</script>
<form id="inputForm" action="boss/content/single/setepisode" method="post">
<input type="hidden" name="ids" value="${ids}" />
<input type="hidden" id="mid" value="${markid}" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<label class="inputlabel">收费起始集数: </label>
			<input type="text" name="start" class="stringfield-short" value="${start}" />&nbsp;(取消按集收费请填<font color='red'>0</font>)
		</p>
		<p>
			<label class="inputlabel">角标: </label>
			<select id="mark_id" name="mark_id">
				<option value="">无角标</option>
				<c:if test="${tags!=null && tags.size()>0}">
				<c:forEach items="${tags}" var="m">
					<option value="${m.id}">${m.name}</option>
				</c:forEach>
				</c:if>
			</select>
		</p>
	</fieldset>
</form>
