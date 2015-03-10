<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
$(function(){

	$("#inputForm").validate({
		rules : {
			days: { required: true, digits: true, min: 1, max:3650},
			price: { required: true, number: true, min:0, max:1000000},
			opercodes: { required: true}
		},
		errorPlacement: function(error, element) {
			if ( element.is(":checkbox") ){
				error.insertAfter(element.parent().next().next());
			} else {
				error.insertAfter(element);
			}
		}
	});
	
	if($("#mid").val()!="undefined"){
		$("#markid").val($("#mid").val());	
	}
	if($("#prev").val()=="0"){
		$("#noprev").attr("checked", true);
	}
	var ops = $("#ops").val();
	if(ops!=""){
		for(var i=1;i<4;i++){
			$("#c"+i).prop("checked", ops.charAt(i-1)=='1');
		}
	} else {
		$("input[type='checkbox'][name='opercodes']").prop("checked", false);
	}
});
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<form:form commandName="cmd" id="inputForm" action="boss/content/single/setpay" htmlEscape="true">
<input type="hidden" id="ops" value="${cmd.opercodes}" />
<input type="hidden" id="mid" value="${cmd.markid}" />
<input type="hidden" id="prev" value="${cmd.preview}" />
<form:hidden path="ids" />
<form:hidden path="asset_id" />
<form:hidden path="product_id" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="price" class="inputlabel"><font class="requiredMark">*</font>价格(元): </form:label>
			<form:input path="price" class="stringfield-short" />
		</p>
		<p>
			<form:label path="days" class="inputlabel"><font class="requiredMark">*</font>时长(天): </form:label>
			<form:input path="days" class="stringfield-short" />
		</p>
		<p>
			<label class="inputlabel"><font class="requiredMark">*</font>运营方式: </label>
			<label class="selection"><input type="checkbox" id="c1" name="opercodes" value="1" />VIP运营</label>
			<label class="selection"><input type="checkbox" id="c2" name="opercodes" value="2" />单点产品运营</label>
			<label class="selection"><input type="checkbox" id="c3" name="opercodes" value="3" />集合产品运营</label>
		</p>
		<p>
			<label class="inputlabel">角标: </label>
			<select id="markid" name="markid">
				<option value="">无角标</option>
				<c:if test="${tags!=null && tags.size()>0}">
				<c:forEach items="${tags}" var="m">
					<option value="${m.id}">${m.name}</option>
				</c:forEach>
				</c:if>
			</select>
		</p>
		<p>
			<form:label path="sync_sid" class="inputlabel">同步策略ID: </form:label>
			<form:input path="sync_sid" class="stringfield" />
		</p>
		<p>
			<label class="inputlabel">试看: </label>
			<label class="selection"><input type="radio" name="preview" value="1" checked />是</label>
			<label class="selection"><input type="radio" name="preview" id="noprev" value="0" />否</label>
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>
