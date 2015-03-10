<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="js/jquery/jquery-ui-timepicker-addon.min.js"></script>
<script type="text/javascript">

	$('#recon_time').datetimepicker({dateFormat: 'yy-m-d'});
	$('#notify_time').datetimepicker({dateFormat: 'yy-m-d'});

	$("#inputForm").validate({
		rules : {
			recon_time: {
				required: {
					depends: function(e){
						return $("#recon_code").val()!="0" && $("#recon_code").val()!="";
					}
				}
			}, 
			notify_time: {
				required: {
					depends: function(e){
						return $("#pay_code").val()=="3";
					}
				}
			}
		}, 
		errorPlacement: function(error, element) {
			error.insertAfter(element);
		}
	});
</script>
<form:form commandName="orderForm" id="inputForm" htmlEscape="true">
	<form:hidden path="id" />
	<fieldset class="dialog-form-fieldset">
		<p><label class="infolabel">支付流水ID: </label>${orderForm.id}</p>
		<p><label class="infolabel">业务订单ID: </label>${orderForm.business_order_id}&nbsp;</p>
		<p><label class="infolabel">产品ID: </label>${orderForm.product_id}</p>
		<p><label class="infolabel">产品名称: </label>${orderForm.product_name}</p>
		<p><label class="infolabel">金额(单位:元): </label>${orderForm.amount}</p>
		<p><label class="infolabel">创建时间: </label>${orderForm.create_time}</p>
		<p><label class="infolabel">业务方处理结果: </label>${orderForm.business_result}</p>
		<p>
			<form:label path="pay_code" class="inputlabel">支付流水状态: </form:label>
			<form:select path="pay_code">
				<form:option value="">-----</form:option>
				<form:options items="${payStatusList}" itemLabel="dictDesc" itemValue="dictValue"  />
			</form:select>
		</p>
		<p>
			<form:label path="recon_code" class="inputlabel">对账状态: </form:label>
			<form:select id="recon_code" path="recon_code">
				<form:option value="">-----</form:option>
				<form:options items="${reconStatusList}" itemLabel="dictDesc" itemValue="dictValue" />
			</form:select>
		</p>
		<p>
			<!-- Y-m-d H:i:s -->
			<form:label path="recon_time" class="inputlabel">对账时间: </form:label>
			<form:input path="recon_time" readonly="true" />
		</p>
		<p>
			<!-- Y-m-d H:i:s -->
			<form:label path="notify_time" class="inputlabel">异步通知时间: </form:label>
			<form:input path="notify_time" readonly="true" />
		</p>
	</fieldset>
</form:form>
