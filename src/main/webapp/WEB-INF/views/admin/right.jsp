<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/admin/right.js"></script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<label><span>模块:</span> 
	<select id="qModuleId" name="moduleId" class="querySelect"><option value="">----</option>
		<c:forEach items="${modules}" var="m">
			<option value="${m.moduleId}">${m.moduleName}</option>
		</c:forEach>
	</select>
	</label>
	<label><span>菜单:</span>
	<select id="qMenuId" name="menuId" class="querySelect">
		<option value="">----</option>
	</select>
	</label>
	<a href="javascript:search();" class="button search">搜索</a>
	<a href="javascript:resetUrl('right');" class="button reload">重置</a>
	<bmc:authority menu="right" right="create" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="aBtn" href="javascript:void(0);" class="button add">添加</a>
	</bmc:authority>
	<bmc:authority menu="right" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="eBtn" href="javascript:void(0);" class="button edit">编辑</a>
	</bmc:authority>
	<bmc:authority menu="right" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="dBtn" href="javascript:void(0);" class="button remove danger">删除</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>MID</th>
			<th>NID</th>
			<th>RID</th>
			<th>模块名称</th>
			<th>菜单名称</th>
			<th>菜单代码</th>
			<th>菜单URL</th>
			<th>功能名称</th>
			<th>功能方法</th>
			<th>状态</th>
			<th>创建时间</th>
			<th>更新时间</th>
		</tr>
	</thead>
</table>
