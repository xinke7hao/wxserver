<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/admin/user.js"></script>
<form id="queryForm" method="post" action="user/export">
<div class="bossToolBar">
	<label><span>用户名:</span><input id="qUserCode" name="userCode" type="text" /></label> 
	<label><span>姓名:</span><input id="qUserName" name="userName" type="text" /></label>
	<label><span>状态:</span>
	<select id="qUserStatus" name="userStatus" class="querySelect" style="width:80px;">
		<option value="">全部</option>
		<option value="Y">启用</option>
		<option value="N">禁用</option>
	</select>
	</label>
	<a href="javascript:search();" class="button search">搜索</a>
	<a href="javascript:resetUrl('user');" class="button reload">重置</a>
	<bmc:authority menu="user" right="create" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="aBtn" href="javascript:void(0);" class="button add">添加</a>
	</bmc:authority>
	<bmc:authority menu="user" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="eBtn" href="javascript:void(0);" class="button edit">编辑</a>
	</bmc:authority>
	<bmc:authority menu="user" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="dBtn" href="javascript:void(0);" class="button remove danger">删除</a>
	</bmc:authority>
	<bmc:authority menu="user" right="rolelist" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="sBtn" href="javascript:void(0);" class="button user">设置角色</a>
	</bmc:authority>
	<bmc:authority menu="user" right="grouplist" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="gBtn" href="javascript:void(0);" class="button user">设置数据</a>
	</bmc:authority>
	<a href="javascript:doExportCsv();" class="button log">导出</a>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>账号</th>
			<th>姓名</th>
			<th>联系电话</th>
			<th>部门</th>
			<th>Email</th>
			<th>管理员</th>
			<th>状态</th>
			<th>创建时间</th>
			<th>更新时间</th>
		</tr>
	</thead>
</table>
