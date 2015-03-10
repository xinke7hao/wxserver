<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/monitor/monitor-payment-order.js"></script>
<form id="queryForm" method="post" action="monitor/payorder/export">
<div class="bossToolBar">
	<label><span>开始日期:</span><input id="qStartDay" name="startDay" type="text" class="queryDate" readonly="readonly" /></label> 
	<label><span>结束日期:</span><input id="qEndDay" name="endDay" type="text" class="queryDate" readonly="readonly" /></label>
	<a href="javascript:search();" class="button search">搜索</a>
	<a href="javascript:resetUrl('monitor/payorder');" class="button reload">重置</a>
	<a href="javascript:doExportCsv();" class="button log">导出</a>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>订单总数</th>
			<th>未支付数</th>
			<th>同步通知数</th>
			<th>异步通知数</th>
			<th>关闭数</th>
			<th>业务方失败数</th>
			<th>监控数据日期</th>
			<th>监控数据时间</th>
			<th>数据建立时间</th>
		</tr>
	</thead>
</table>
