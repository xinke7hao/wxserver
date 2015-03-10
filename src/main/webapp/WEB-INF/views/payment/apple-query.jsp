<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>
<script src="js/payment/apple-query.js"></script>
<form id="appleQueryForm" method="post">
<div class="bossToolBar">
	<label><span>支付流水ID:</span><input id="orderId" name="orderId" type="text" style="width:180px;" /></label>
	<label><span>用户账号:</span><input id="payAccount" name="payAccount" type="text" style="width:180px;" /></label>
	<a href="#" id="appleSearch" class="button search">查询</a>
	<a href="javascript:resetUrl('payment/apple');" class="button reload">重置</a>
	<p><label><span style="vertical-align: top;">支付凭证:</span><textarea id="receipt" name="receipt" style="width:75%;height:150px;"></textarea></label></p>
</div>
</form>
<div class="container">
<div class="content-div">
		<table style="width: 600px; max-width: 800px;">
			<thead>
				<tr>
					<th>支付流水ID</th>
					<th>用户账号</th>
					<th>支付凭证状态代码</th>
					<th>支付凭证状态信息</th>
				</tr>
			<thead>
			<tbody id="myorders"></tbody>
	</div>
</div>