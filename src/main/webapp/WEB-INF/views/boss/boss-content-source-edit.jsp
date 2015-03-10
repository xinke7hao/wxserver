<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(function(){
	if($("#mvip").val()!="undefined"){
		$("input[type='radio'][name='status']").each(function(){
			if($(this).val()==$("#mvip").val()){
				$(this).attr("checked", true);
			}
		});
	}
});
</script>
<form id="inputForm" method="post">
	<input type="hidden" name="ids" value="${ids}" />
	<input type="hidden" id="mvip" value="${minvip}" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<label class="inputlabel">收费设置: </label>
			<label class="selection"><input type="radio" name="status" value="-1" checked />免费</label>
			<c:forEach items="${vips}" var="m">
				<label class="selection"><input type="radio" name="status" value="${m.id}" />${m.name}</label>
			</c:forEach>
		</p>
	</fieldset>
</form>
