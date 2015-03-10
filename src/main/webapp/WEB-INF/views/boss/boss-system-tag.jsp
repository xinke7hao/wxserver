<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/boss/boss-system-tag.js"></script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<bmc:authority menu="boss/system/tag" right="create" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="cBtn" class="button add">添加</a>
	</bmc:authority>
	<bmc:authority menu="boss/system/tag" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="eBtn" class="button edit">编辑</a>
	</bmc:authority>
	<bmc:authority menu="boss/system/tag" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="dBtn" class="button remove danger">删除</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th style="width:20px;"><input type="checkbox" id="checkAll" /></th>
			<th>ID</th>
			<th>名称</th>
			<th>分类</th>
			<th>是否默认值</th>
		</tr>
	</thead>
</table>
