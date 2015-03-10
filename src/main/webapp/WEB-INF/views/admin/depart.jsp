<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/admin/department.js"></script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<label><span>部门代码:</span><input id="qDepartCode" name="departCode" type="text" /></label> 
	<label><span>部门名称:</span><input id="qDepartName" name="departName" type="text" /></label>
	<a href="javascript:search();" class="button search">搜索</a>
	<a href="javascript:resetUrl('depart');" class="button reload">重置</a>
	<bmc:authority menu="depart" right="create" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="aBtn" href="javascript:void(0);" class="button add">添加</a>
	</bmc:authority>
	<bmc:authority menu="depart" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="eBtn" href="javascript:void(0);" class="button edit">编辑</a>
	</bmc:authority>
	<bmc:authority menu="depart" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="dBtn" href="javascript:void(0);" class="button remove danger">删除</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>部门代码</th>
			<th>部门名称</th>
			<th>创建时间</th>
			<th>更新时间</th>
		</tr>
	</thead>
</table>
