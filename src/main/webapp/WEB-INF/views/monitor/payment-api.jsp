<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/monitor/monitor-payment-api.js"></script>
<form id="queryForm" method="post" action="monitor/payment/export">
<div class="bossToolBar">
	<label><span>API名称:</span><input id="qApiName" type="text" name="apiName" /></label> 
	<label><span>开始日期:</span><input id="qStartDay" name="startDay" type="text" class="queryDate" readonly="readonly" /></label> 
	<label><span>结束日期:</span><input id="qEndDay" name="endDay" type="text" class="queryDate" readonly="readonly" /></label>
	<a href="javascript:search();" class="button search">搜索</a>
	<a href="javascript:resetUrl('monitor/payment');" class="button reload">重置</a>
	<a href="javascript:doExportCsv();" class="button log">导出</a>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>API名称</th>
			<th>调用次数</th>
			<th>报错数</th>
			<th>警告数</th>
			<th>平均响应时间(ms)</th>
			<th>监控类型</th>
			<th>监控数据日期</th>
			<th>监控数据时间</th>
			<th>数据建立时间</th>
		</tr>
	</thead>
</table>
