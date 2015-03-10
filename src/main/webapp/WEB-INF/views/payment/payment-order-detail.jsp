<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form commandName="orderForm">
	<fieldset class="dialog-form-fieldset">
		<p><label class="infolabel">支付流水ID: </label>${orderForm.id}</p>
		<p><label class="infolabel">业务订单ID: </label>${orderForm.business_order_id}</p> 
		<p><label class="infolabel">产品ID: </label>${orderForm.product_id}</p>
		<p><label class="infolabel">产品名称: </label>${orderForm.product_name}</p>
		<p><label class="infolabel">金额(单位:元): </label>${orderForm.amount}</p>
		<p><label class="infolabel">流水状态: </label>${orderForm.status}</p>
		<p><label class="infolabel">创建时间: </label>${orderForm.create_time}</p>
		<p><label class="infolabel">对账状态: </label>${orderForm.recon_status}</p>
		<p><label class="infolabel">业务方处理结果: </label>${orderForm.business_result}</p>
	</fieldset>
</form:form>
