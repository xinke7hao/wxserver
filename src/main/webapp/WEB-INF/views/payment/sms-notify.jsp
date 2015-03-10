<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/payment/sms-notify.js"></script>
<form id="queryForm" method="post" action="payment/sms/export">
<div class="bossToolBar">
	<label><span>手机号:</span><input id="qMobile" name="mobile" type="text" /></label>
	<label><span>开始日期:</span><input id="qStartDay" name="startDay" type="text" class="queryDate" readonly="readonly" /></label> 
	<label><span>结束日期:</span><input id="qEndDay" name="endDay" type="text" class="queryDate" readonly="readonly" /></label>
	<a href="javascript:search();" class="button search">搜索</a>
	<a href="javascript:resetUrl('payment/sms');" class="button reload">重置</a>
	<bmc:authority menu="payment/sms" right="export" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a href="javascript:doExportCsv();" class="button log">导出</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>连接ID</th>
			<th>消息时间</th>
			<th>手机号</th>
			<th>消息内容</th>
			<th>订阅参数</th>
			<th>通知类型</th>
			<th>创建时间</th>
		</tr>
	</thead>
</table>
