<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/payment/payment-order.js"></script>
<form id="queryForm" method="post" action="payment/order/export">
<div class="bossToolBar">
	<p style="margin:0px; padding: 0px; padding-bottom: 7px;">
	<label><span>支付渠道:</span>
	<select id="qChannelId" name="channelId" class="querySelect">
		<option value="">----</option>
		<c:forEach items="${channels}" var="c">
		<option value="${c.dataId}">${c.dataName}</option>
		</c:forEach>
	</select></label>
	<label><span>平台订单ID:</span><input id="qBizOrderId" name="businessOrderId" type="text" /></label> 
	<label><span>流水ID:</span><input id="qOrderId" name="orderId" type="text" style="width:160px;" /></label> 
	<label><span>产品ID:</span><input id="qProductId" name="productId" type="text" /></label> 
	<label><span>账号:</span><input id="qAccount" name="account" type="text" /></label> 
	<label><span>手机号:</span><input id="qMobile" name="mobile" type="text" /></label></p>
	<label><span>开始日期:</span><input id="qStartDay" name="startDay" type="text" class="queryDate" readonly="readonly" /></label> 
	<label><span>结束日期:</span><input id="qEndDay" name="endDay" type="text" class="queryDate" readonly="readonly" /></label>
	<a href="javascript:search();" class="button search">搜索</a>
	<a href="javascript:resetUrl('payment/order');" class="button reload">重置</a>
	<bmc:authority menu="payment/order" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="eBtn" href="javascript:void(0);" class="button edit">编辑</a>
	</bmc:authority>
	<bmc:authority menu="payment/order" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="dBtn" href="javascript:void(0);" class="button remove danger">删除</a>
	</bmc:authority>
	<a id="ddBtn" href="javascript:void(0);" class="button comment">查看详情</a>
	<bmc:authority menu="payment/order" right="export" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a href="javascript:doExportCsv();" class="button log">导出</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>支付流水id</th>
			<th>业务订单id</th>
			<th>支付渠道</th>
			<th>产品id</th>
			<th>产品名称</th>
			<th>账号</th>
			<th>手机号</th>
			<th>金额(元)</th>
			<th>状态</th>
			<th>创建时间</th>
			<th>对账状态</th>
			<th>业务方处理结果</th>
		</tr>
	</thead>
</table>
