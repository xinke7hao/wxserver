<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/boss/boss-system-vip.js"></script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<bmc:authority menu="boss/system/vip" right="create" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="cBtn" class="button add">添加</a>
	</bmc:authority>
	<bmc:authority menu="boss/system/vip" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="eBtn" class="button edit">编辑</a>
	</bmc:authority>
	<bmc:authority menu="boss/system/vip" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="dBtn" class="button remove danger">删除</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>名称</th>
			<th>权重</th>
			<th>状态</th>
			<th>清晰度(码率)</th>
			<th>创建时间</th>
			<th>最近修改时间</th>
		</tr>
	</thead>
</table>
