<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="js/jquery/jquery-ui-timepicker-addon.min.js"></script>
<script src="js/monitor/monitor-login-log.js"></script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<label><span>用户名:</span><input id="qUserCode" name="usercode" type="text" /></label> 
	<label><span>开始时间:</span><input id="qStartDay" name="startDay" type="text" class="queryTime" readonly="readonly" /></label> 
	<label><span>结束时间:</span><input id="qEndDay" name="endDay"  type="text" class="queryTime" readonly="readonly" /></label>
	<a href="javascript:search();" class="button search">搜索</a>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>用户名</th>
			<th>登录时间</th>
			<th>登录状态</th>
			<th>IP</th>
			<th>描述</th>
		</tr>
	</thead>
</table>
