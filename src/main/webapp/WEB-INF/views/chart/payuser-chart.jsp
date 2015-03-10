<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="js/echarts/echarts.js"></script>
<script src="js/chart/payuser-chart.js"></script>
<form id="chartForm" method="post">
<div class="bossToolBar">
	<label><span>开始日期:</span><input id="qStartDay" name="startDay" type="text" class="queryDate" readonly="readonly" /></label> 
	<label><span>结束日期:</span><input id="qEndDay" name="endDay" type="text" class="queryDate" readonly="readonly" /></label>
	<a href="javascript:reloadChart();" class="button search">搜索</a>
	<a href="javascript:resetUrl('chart/payuser');" class="button reload">重置</a>
</div>
</form>
<div id="payuserChart" style="height:650px;"></div>
