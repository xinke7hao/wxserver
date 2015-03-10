<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			channel_name: "required",
			channel_alias: "required",
			is_available: "required",
			channel_code: "required",
			agent_name: "required",
			order_rate: { required:true, number:true, range:[0,10] },
			seller: "required",
			pay_url: { required: true, url: true },
			return_url: { required: true },
			notify_url: { required: true },
			channel_type: "required"
		}, 
		errorPlacement: function(error, element) {
			if ( element.is(":radio") ){
				error.insertAfter( element.parent().next() );
			} else {
				error.insertAfter(element);
			}
		}
	});
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<form:form commandName="channelForm" id="inputForm" htmlEscape="true">
	<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="channel_name" class="inputlabel"><font class="requiredMark">*</font>支付渠道名: </form:label>
			<form:input path="channel_name" class="stringfield" />
		</p>
		<p>
			<form:label path="channel_alias" class="inputlabel"><font class="requiredMark">*</font>中文名: </form:label>
			<form:input path="channel_alias" class="stringfield" />
		</p>
		<p>
			<form:label path="is_available" class="inputlabel"><font class="requiredMark">*</font>状态: </form:label>
			<label class="selection"><form:radiobutton path="is_available" value="1" />启用</label>
			<label class="selection"><form:radiobutton path="is_available" value="0" />禁用</label>
		</p>
		<p>
			<form:label path="channel_code" class="inputlabel"><font class="requiredMark">*</font>渠道代号: </form:label>
			<form:input path="channel_code" class="stringfield" />
		</p>
		<p>
			<form:label path="agent_name" class="inputlabel"><font class="requiredMark">*</font>支付代理名称: </form:label>
			<form:input path="agent_name" class="stringfield" />
		</p>
		<p>
			<form:label path="order_rate" class="inputlabel"><font class="requiredMark">*</font>渠道费率: </form:label>
			<form:input path="order_rate" class="stringfield" />
		</p>
		<p>
			<form:label path="pay_url" class="inputlabel"><font class="requiredMark">*</font>支付地址: </form:label>
			<form:input path="pay_url" class="stringfield-long" />
		</p>
		<p>
			<form:label path="return_url" class="inputlabel"><font class="requiredMark">*</font>同步返回地址: </form:label>
			<form:input path="return_url" class="stringfield-long" />
		</p>
		<p>
			<form:label path="notify_url" class="inputlabel"><font class="requiredMark">*</font>异步返回地址: </form:label>
			<form:input path="notify_url" class="stringfield-long" />
		</p>
		<p>
			<form:label path="query_url" class="inputlabel">查单地址: </form:label>
			<form:input path="query_url" class="stringfield-long" />
		</p>
		<p>
			<form:label path="channel_type" class="inputlabel"><font class="requiredMark">*</font>跳转类型: </form:label>
			<form:select path="channel_type">
				<form:option value="">------</form:option>
				<form:option value="location">location</form:option>
				<form:option value="ott">ott</form:option>
				<form:option value="qrcode">qrcode</form:option>
				<form:option value="wait">wait</form:option>
			</form:select>
		</p>
		<p>
			<form:label path="key" class="inputlabel">Key: </form:label>
			<form:input path="key" class="stringfield" />
		</p>
		<p>
			<form:label path="secret" class="inputlabel">Secret: </form:label>
			<form:input path="secret" class="stringfield-long" />
		</p>
		<p>
			<form:label path="seller" class="inputlabel"><font class="requiredMark">*</font>账号: </form:label>
			<form:input path="seller" class="stringfield" />
		</p>
		<p>
			<form:label path="seller_name" class="inputlabel">账号公司名: </form:label>
			<form:input path="seller_name" class="stringfield" />
		</p>
		<p>
			<form:label path="per" class="inputlabel">公钥地址: </form:label>
			<form:input path="per" class="stringfield-long" />
		</p>
		<p>
			<form:label path="cper" class="inputlabel">私钥: </form:label>
			<form:input path="cper" class="stringfield-long" />
		</p>
		<p>
			<form:label path="app_id" class="inputlabel">APP ID: </form:label>
			<form:input path="app_id" class="stringfield" />
		</p>
		<p>
			<form:label path="app_secret" class="inputlabel">APP Secret: </form:label>
			<form:input path="app_secret" class="stringfield" />
		</p>
		<p>
			<form:label path="app_signkey" class="inputlabel">APP Sign Key: </form:label>
			<form:input path="app_signkey" class="stringfield" />
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>