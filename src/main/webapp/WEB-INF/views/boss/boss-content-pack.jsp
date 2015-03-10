<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/boss/boss-content-pack.js"></script>
<input type="hidden" id="pid" name="pid" />
<div class="bossToolBar">
	<bmc:authority menu="boss/content/pack" right="create" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="cBtn" class="button add">添加</a>
	</bmc:authority>
	<bmc:authority menu="boss/content/pack" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="eBtn" class="button edit">编辑</a>
	</bmc:authority>
	<bmc:authority menu="boss/content/pack" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="dBtn" class="button remove danger">删除</a>
	</bmc:authority>
</div>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th style="width:20px;"><input type="checkbox" id="checkAll" /></th>
			<th>ID</th>
			<th>名称</th>
			<th>最后修改时间</th>
			<th>售价</th>
			<th>天数</th>
			<th>操作</th>
		</tr>
	</thead>
</table>
