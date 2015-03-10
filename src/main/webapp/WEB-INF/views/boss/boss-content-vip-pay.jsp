<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	$(function(){
		if($("#minvip").val()=="-1"){
			$("#free").attr("checked", true);
		}
		if($("#mid").val()!="undefined"){
			$("#mark_id").val($("#mid").val());	
		}
		if($("#prev").val()=="0"){
			$("#noprev").attr("checked", true);
		}
	});
</script>
<form id="inputForm" action="boss/content/vip/setpay" method="post">
<input type="hidden" name="ids" value="${ids}" />
<input type="hidden" id="minvip" value="${minvip}" />
<input type="hidden" id="prev" value="${prev}" />
<input type="hidden" id="mid" value="${markid}" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<label class="inputlabel">收费: </label>
			<label class="selection"><input type="radio" name="min_vip_id" value="0" checked />收费</label>
			<label class="selection"><input type="radio" id="free" name="min_vip_id" value="-1" />免费</label>
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
		<p>
			<label class="inputlabel">试看: </label>
			<label class="selection"><input type="radio" name="preview" value="1" checked />是</label>
			<label class="selection"><input type="radio" id="noprev" name="preview" value="0" />否</label>
		</p>
	</fieldset>
</form>
