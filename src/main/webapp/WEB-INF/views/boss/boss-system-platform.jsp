<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/boss/boss-system-platform.js"></script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<bmc:authority menu="boss/system/platform" right="create" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="cBtn" class="button add">添加</a>
	</bmc:authority>
	<bmc:authority menu="boss/system/platform" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="eBtn" class="button edit">编辑</a>
	</bmc:authority>
	<bmc:authority menu="boss/system/platform" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="dBtn" class="button remove danger">删除</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>名称</th>
			<th>UUID标识</th>
			<th>状态</th>
			<th>创建时间</th>
			<th>最后更新时间</th>
		</tr>
	</thead>
</table>
