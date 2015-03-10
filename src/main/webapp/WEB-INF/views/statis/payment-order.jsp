<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="js/statis/statis-payment-order.js"></script>
<form id="queryForm" method="post" action="statis/payment/order/export">
<div class="bossToolBar">
	<label><span>开始日期:</span><input id="qStartDay" name="startDay" type="text" class="queryDate" readonly="readonly" /></label> 
	<label><span>结束日期:</span><input id="qEndDay" name="endDay" type="text" class="queryDate" readonly="readonly" /></label>
	<a href="javascript:search();" class="button statis">统计</a>
	<a href="javascript:resetUrl('statis/payment/order');" class="button reload">重置</a>
	<a href="javascript:doExportCsv();" class="button log">导出</a>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>统计日期</th>
			<th>支付流水总数</th>
			<th>已结单的支付流水数</th>
			<th>总金额(元)</th>
			<th>同步返回数</th>
			<th>异步返回数</th>
			<th>关闭数</th>
			<th>天对账失败数</th>
			<th>业务方处理失败数</th>
			<th>创建时间</th>
		</tr>
	</thead>
</table>
