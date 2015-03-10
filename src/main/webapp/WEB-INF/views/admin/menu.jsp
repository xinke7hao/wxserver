<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/admin/menu.js"></script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<label><span>模块:</span> 
	<select id="qModuleId" name="moduleId" class="querySelect"><option value="">----</option>
		<c:forEach items="${modules}" var="m">
			<option value="${m.moduleId}">${m.moduleName}</option>
		</c:forEach>
	</select>
	</label>
	<a href="javascript:search();" class="button search">搜索</a>
	<bmc:authority menu="menu" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="eBtn" href="javascript:void(0);" class="button edit">编辑</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>MID</th>
			<th>NID</th>
			<th>模块名称</th>
			<th>菜单名称</th>
			<th>描述说明</th>
		</tr>
	</thead>
</table>
