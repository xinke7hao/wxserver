<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="js/payment/blacklist.js"></script>
<form id="queryForm" method="post" action="payment/blacklist/export">
<div class="bossToolBar">
	<label><span>业务平台:</span>
	<select id="qPlatformId" name="platform_id" class="querySelect">
		<option value="">----</option>
		<c:forEach items="${platforms}" var="p">
		<option value="${p.dataId}">${p.dataName}</option>
		</c:forEach>
	</select></label>
	<label><span>支付渠道:</span>
	<select id="qChannelId" name="channel_id" class="querySelect">
		<option value="">----</option>
		<c:forEach items="${channels}" var="c">
		<option value="${c.dataId}">${c.dataName}</option>
		</c:forEach>
	</select></label>
	<label><span>黑名单名称:</span><input id="qItem" name="item" type="text" /></label> 
	<label><span>名单类型:</span>
	<select id="qType" name="type">
		<option value="">------</option>
		<option value="0">手机号</option>
		<option value="1">账号</option>
		<option value="2">MAC</option>
		<option value="3">License</option>
	</select>
	</label>
	<a href="javascript:search();" class="button search">搜索</a>
	<a href="javascript:resetUrl('payment/blacklist');" class="button reload">重置</a>
	<bmc:authority menu="payment/blacklist" right="create" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="aBtn" href="javascript:void(0);" class="button add">添加</a>
	</bmc:authority>
	<bmc:authority menu="payment/blacklist" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="eBtn" href="javascript:void(0);" class="button edit">编辑</a>
	</bmc:authority>
	<bmc:authority menu="payment/blacklist" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="dBtn" href="javascript:void(0);" class="button remove danger">删除</a>
	</bmc:authority>
	<bmc:authority menu="payment/blacklist" right="export" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a href="javascript:doExportCsv();" class="button log">导出</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>黑名单名称</th>
			<th>名单类型</th>
			<th>业务平台ID</th>
			<th>支付渠道ID</th>
			<th>状态</th>
			<th>创建时间</th>
			<th>更新时间</th>
		</tr>
	</thead>
</table>
