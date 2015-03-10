<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="js/statis/statis-payment-channel.js"></script>
<form id="queryForm" method="post" action="statis/payment/channel/export">
<div class="bossToolBar">
	<label><span>支付渠道:</span></label> 
	<select id="qChannelId" name="channelId" class="querySelect">
		<option value="">----</option>
		<c:forEach items="${channels}" var="c">
		<option value="${c.dataId}">${c.dataName}</option>
		</c:forEach>
	</select> 
	<label><span>开始日期:</span><input id="qStartDay" name="startDay" type="text" class="queryDate" readonly="readonly" /></label> 
	<label><span>结束日期:</span><input id="qEndDay" name="endDay" type="text" class="queryDate" readonly="readonly" /></label>
	<a href="javascript:search();" class="button statis">统计</a>
	<a href="javascript:resetUrl('statis/payment/channel');" class="button reload">重置</a>
	<a href="javascript:doExportCsv();" class="button log">导出</a>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>支付渠道英文名</th>
			<th>支付渠道中文名</th>
			<th>支付流水总数</th>
			<th>已结单的支付流水数</th>
			<th>总金额(元)</th>
			<th>同步返回数</th>
			<th>异步返回数</th>
			<th>统计日期</th>
			<th>创建时间</th>
		</tr>
	</thead>
</table>
