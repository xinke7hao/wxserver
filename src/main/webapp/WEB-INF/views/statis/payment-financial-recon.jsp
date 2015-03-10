<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.financialTable thead tr {
	line-height: 25px;
	background-color: darkslategray;
	color: white;
}
.financialTable thead tr th {
	border-bottom: 1px solid white;
	border-right: 1px solid white;
}
.financialTable tbody tr {
	line-height: 25px;
	text-align: center;
}
.financialTable tbody td {
	border-bottom: 1px solid #9d9d9d;
	font-weight: bold;
	color: #333;
}
.financialTable tbody td.accountTd {
	border-left: 1px solid #9d9d9d;
	border-right: 1px solid #9d9d9d;
}
.financialTable tr td.p0 {
	background-color: #99ccff;
	width: 6%;
}
.financialTable tr td.p1 {
	background-color: #ccffff;
	width: 6%;
}
.financialTable tr td.p2 {
	background-color: #ff99cc;
	width: 6%;
}
.financialTable tr td.p3 {
	background-color: #ccffcc;
	width: 6%;
}
.financialTable tr td.ps {
	background-color: greenyellow;
	width: 7%;
}
</style>
<script src="js/statis/statis-payment-financial-recon.js"></script>
<form id="queryForm" method="post" action="statis/payment/finanrecon/export">
<div class="bossToolBar">
	<label><span>统计年份:</span>
	<select id="year" name="year" class="querySelect">
		<c:forEach begin="2006" end="2020" step="1" var="syear">
		<option value="${syear}">${syear}</option>
		</c:forEach>
	</select></label>
	<a href="javascript:doStatis();" class="button statis">统计</a>
<!-- 	<a href="javascript:doExportCsv();" class="button log">导出</a> -->
</div>
</form>
<table class="financialTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th rowspan="3" style="width:150px;">账号</th>
			<th rowspan="2" colspan="2" style="width:80px;">总收入</th>
			<th colspan="${4*platformSize}">业务平台</th>
		</tr>
		<tr>
			<c:forEach items="${platforms}" var="pp">
				<th colspan="4"><input type="hidden" name="platformIds" value="${pp.dataId}" />${pp.dataName}</th>
			</c:forEach>
		</tr>
		<tr>
			<th style="width: 55px;">月份</th>
			<th>到款总额</th>
		<c:forEach begin="1" end="${platformSize}" step="1">
			<th>总收入</th>
			<th>总利润</th>
			<th>手续费</th>
			<th>总支付订单数</th>
		</c:forEach>
		</tr>
	</thead>
	<tbody id="tData"></tbody>
</table>
<table id="accountPrompt" cellspacing="0" style="width: 100%; margin: 0px;margin-top:20px; display: none;">
	<tr><td colspan="15" style="text-align:center; color:red; font-weight: bold;">没有相关的统计数据</td></tr>
</table>
<table class="financialTable" width="100%" class="display" cellspacing="0" style="margin: 0px;margin-top:20px;">
	<thead>
		<tr>
			<th rowspan="3" style="width:150px;">支付渠道</th>
			<th rowspan="2" colspan="2" style="width:80px;">总收入</th>
			<th colspan="${4*platformSize}">业务平台</th>
		</tr>
		<tr>
			<c:forEach items="${platforms}" var="pp">
				<th colspan="4">${pp.dataName}</th>
			</c:forEach>
		</tr>
		<tr>
			<th style="width: 55px;">月份</th>
			<th>到款总额</th>
		<c:forEach begin="1" end="${platformSize}" step="1">
			<th>总收入</th>
			<th>总利润</th>
			<th>手续费</th>
			<th>总支付订单数</th>
		</c:forEach>
		</tr>
	</thead>
	<tbody id="cData"></tbody>
</table>
<table id="channelPrompt" cellspacing="0" style="width: 100%; margin: 0px;margin-top:20px; display: none;">
	<tr><td colspan="15" style="text-align:center; color:red; font-weight: bold;">没有相关的统计数据</td></tr>
</table>
