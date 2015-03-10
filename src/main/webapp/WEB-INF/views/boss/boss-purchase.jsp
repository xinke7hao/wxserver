<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/boss/boss-purchase.js"></script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<label><span>账号:</span><input id="qAccountId" name="account" type="text" /></label> 
	<label><span>订单号:</span><input id="qOrderId" name="orderId" type="text" /></label>
	<label class="radio-group-line">
	<span>订购关系:</span>
	<label><input name="onlyValid" type="radio" value="N" checked />所有</label>
	<label><input name="onlyValid" type="radio" value="Y" />有效</label>
	</label>
	<a href="javascript:search();" class="button search">搜索</a>
	<a href="javascript:resetUrl('boss/purchase');" class="button reload">重置</a>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>账号</th>
			<th>订单编号</th>
			<th>订单支付时间</th>
			<th>订单创建时间</th>
			<th>支付渠道</th>
			<th>产品</th>
			<th>产品服务开始时间</th>
			<th>产品服务结束时间</th>
			<th>数量</th>
			<th>类别</th>
			<th>发货时间</th>
		</tr>
	</thead>
</table>
