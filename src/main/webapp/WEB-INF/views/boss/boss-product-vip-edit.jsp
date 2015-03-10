<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			name: "required",
			vip_id: "required",
			kind: "required",
			days: { required: true, digits: true},
			show: "required",
			price: { required: true, number: true}
		},
		errorPlacement: function(error, element) {
			if ( element.is(":radio") ){
				if(element.attr("id")=="kind1"){
					error.insertAfter(element.parent().next().next());
				} else {
					error.insertAfter(element.parent().next());
				}
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
	<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="name" class="inputlabel"><font class="requiredMark">*</font>名称: </form:label>
			<form:input path="name" class="stringfield" />
		</p>
		<p>
			<form:label path="vip_id" class="inputlabel"><font class="requiredMark">*</font>会员: </form:label>
			<form:select path="vip_id">
				<form:option label="---请选择---" value="" />
				<form:options items="${vips}" itemLabel="name" itemValue="id" />
			</form:select>
		</p>
		<p>
			<form:label path="days" class="inputlabel"><font class="requiredMark">*</font>时长(天): </form:label>
			<form:input path="days" class="stringfield-short" />
		</p>
		<p>
			<form:label path="kind" class="inputlabel"><font class="requiredMark">*</font>类别: </form:label>
			<label class="selection"><form:radiobutton path="kind" value="0" />正式产品</label>
			<label class="selection"><form:radiobutton path="kind" value="1" />体验活动</label>
			<label class="selection"><form:radiobutton path="kind" value="2" />升级产品</label>
		</p>
		<p>
			<form:label path="show" class="inputlabel"><font class="requiredMark">*</font>前端可见: </form:label>
			<label class="selection"><form:radiobutton path="show" value="0" />不可见</label>
			<label class="selection"><form:radiobutton path="show" value="1" />可见</label>
		</p>
		<p>
			<form:label path="price" class="inputlabel"><font class="requiredMark">*</font>价格(元): </form:label>
			<form:input path="price" class="stringfield-short" />
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>