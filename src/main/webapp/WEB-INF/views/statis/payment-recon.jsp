<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.myrowspan {
	background-color: #f7f7f7;
}
.financialTable thead tr {
	line-height: 30px;
	background-color: darkslategray;
	color: white;
}
.financialTable tbody tr {
	line-height: 30px;
	text-align: center;
}
.financialTable tbody tr.hover {
  background-color: #a9b7d1;
}
.financialTable tbody tr.odd {
  background-color: #f9f9f9;
}
.financialTable tbody td {
	border-bottom: 1px solid #9d9d9d;
	font-weight: bold;
	color: #333;
}
.financialTable tr td.sumtd {
	background-color: greenyellow;
}
</style>
<script src="js/statis/statis-payment-recon.js"></script>
<form id="queryForm" method="post" action="statis/payment/recon/export">
<div class="bossToolBar">
	<label><span>业务平台:</span>
	<select id="qPlatformId" name="platformId" class="querySelect">
		<option value="">----</option>
		<c:forEach items="${platforms}" var="p">
		<option value="${p.dataId}">${p.dataName}</option>
		</c:forEach>
	</select></label>
	<label><span>支付渠道:</span>
	<select id="qChannelId" name="channelId" class="querySelect">
		<option value="">----</option>
		<c:forEach items="${channels}" var="c">
		<option value="${c.dataId}">${c.dataName}</option>
		</c:forEach>
	</select></label>
	<label><span>开始日期:</span><input id="qStartDay" name="startDay" type="text" class="queryDate" readonly="readonly" /></label> 
	<label><span>结束日期:</span><input id="qEndDay" name="endDay" type="text" class="queryDate" readonly="readonly" /></label>
	<a href="javascript:submitRecon();" class="button statis">统计</a>
	<a href="javascript:resetUrl('statis/payment/recon');" class="button reload">重置</a>
	<a href="javascript:doExportCsv();" class="button log">导出</a>
</div>
</form>

<table class="financialTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>业务平台</th>
			<th>支付渠道</th>
			<th>支付数</th>
			<th>总金额(元)</th>
			<th>总收入(元)</th>
			<th>手续费(元)</th>
		</tr>
	</thead>
	<tbody id="tData"></tbody>
</table>
<table id="noData" cellspacing="0" style="width: 100%; margin: 0px;margin-top:20px; display: none;">
	<tr><td colspan="15" style="text-align:center; color:red; font-weight: bold;">没有相关的统计数据</td></tr>
</table>
