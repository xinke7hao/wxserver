<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/boss/boss-account.js"></script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<label><span>账号ID:</span><input id="qAccountId" name="accountId" type="text" /></label> 
	<label><span>账号:</span><input id="qAccountName" name="accountName" type="text" /></label>
	<label><span>用户属性:</span>
	<select id="accountType" name="accountType">
		<option value="0">全部用户</option>
		<option value="1">当前有效VIP用户</option>
		<option value="2">所有VIP用户</option>
	</select>
	</label>
	<a href="javascript:search();" class="button search">搜索</a>
	<bmc:authority menu="boss/account" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="eBtn" class="button edit">编辑</a>
	</bmc:authority>
	<bmc:authority menu="boss/account" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="dBtn" class="button remove danger">删除</a>
	</bmc:authority>
	<bmc:authority menu="boss/account" right="close" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="cBtn" class="button settings danger">取消服务</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th style="width:20px;"><input type="checkbox" id="checkAll" /></th>
			<th>ID</th>
			<th>通行证</th>
			<th>积分</th>
			<th>等级</th>
			<th>芒果币</th>
			<th>业务</th>
			<th>VIP身份</th>
			<th>状态</th>
			<th>VIP身份生效时间</th>
			<th>VIP身份失效时间</th>
		</tr>
	</thead>
</table>
