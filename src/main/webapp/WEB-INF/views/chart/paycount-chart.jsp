<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="js/echarts/echarts.js"></script>
<script src="js/chart/stack-chart.js"></script>
<script src="js/chart/paycount-chart.js"></script>
<form id="chartForm" method="post">
<div class="bossToolBar">
	<label><span>开始日期:</span><input id="qStartDay" name="startDay" type="text" class="queryDate" readonly="readonly" /></label> 
	<label><span>结束日期:</span><input id="qEndDay" name="endDay" type="text" class="queryDate" readonly="readonly" /></label>
	<label class="radio-group-line"><span>时间粒度:</span>
	<label><input name="timeType" type="radio" value="0" checked /> 天</label>
	<label><input name="timeType" type="radio" value="1" /> 月</label>
	</label>
	<a href="javascript:reloadChart();" class="button search">搜索</a>
	<a href="javascript:resetUrl('chart/paycount');" class="button reload">重置</a><br/>
</div>
</form>
<div id="paycountChart" style="height:750px;"></div>
