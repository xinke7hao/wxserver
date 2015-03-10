<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
#chartContainer{ text-align:center; }
#platformPieChart {	float: left; }
#channelPieChart { float: right; }
</style>
<script src="js/echarts/echarts.js"></script>
<script src="js/chart/payment-pie-chart.js"></script>
<div class="bossToolBar">
	<label><span>开始日期:</span><input id="qStartDay" type="text" class="queryDate" readonly="readonly" /></label> 
	<label><span>结束日期:</span><input id="qEndDay" type="text" class="queryDate" readonly="readonly" /></label>
	<a href="javascript:reloadChart();" class="button search">搜索</a>
	<a href="javascript:resetUrl('chart/paypie');" class="button reload">重置</a><br/>
	<label class="radio-group-line"><span>业务平台:</span>
	<c:forEach items="${platforms}" var="c">
		<label><input type="checkbox" name="qPlatform" value="${c.dataId}" />${c.dataName}</label>
	</c:forEach>
	</label><br/>
	<label class="radio-group-line"><span>支付渠道:</span>
	<label><input type="checkbox" id="chkAllChannel" />全选</label>
	<c:forEach items="${channels}" var="c">
		<label><input type="checkbox" name="qChannel" value="${c.dataId}" />${c.dataName}</label>
	</c:forEach>
	</label>
</div>
<div id="chartContainer">
	<div id="platformPieChart" style="width: 700px; height:450px;"></div>
	<div id="channelPieChart" style="width: 700px; height:450px;"></div>
</div>
