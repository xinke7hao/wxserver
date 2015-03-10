<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/monitor/monitor-system-log.js"></script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<label><span>操作模块:</span> 
	<select id="qMenuId" name="module" class="querySelect" style="width:180px;">
		<option value="">----------------------</option>
		<c:forEach items="${menus}" var="m">
			<option value="${m.menuId}">${m.moduleName}--${m.menuName}</option>
		</c:forEach>
	</select>
	</label>
	<label><span>动作类型: </span>
	<select id="qStyle" name="style" class="querySelect">
		<option value="">---------</option>
		<option value="L">LOCAL</option>
		<option value="H">HTTP</option>
		<option value="R">RESTFul</option>
	</select>
	</label>
	<label><span>操作类型: </span>
	<select id="qType" name="type" class="querySelect">
		<option value="">---------</option>
		<option value="U">编辑</option>
		<option value="D">删除</option>
		<option value="Q">查询</option>
	</select>
	</label>
	<label><span>日志状态: </span>
	<select id="qStatus" name="status" class="querySelect">
		<option value="">---------</option>
		<option value="Y">正常</option>
		<option value="N">错误</option>
	</select>
	</label>
	<label><span>开始日期:</span><input id="qStartDay" name="startDay" type="text" class="queryDate" readonly="readonly" /></label> 
	<label><span>结束日期:</span><input id="qEndDay" name="endDay"  type="text" class="queryDate" readonly="readonly" /></label>
	<a href="javascript:search();" class="button search">搜索</a>
	<a href="javascript:resetUrl('monitor/log');" class="button reload">重置</a>
	<bmc:authority menu="monitor/log" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="dBtn" href="javascript:void(0);" class="button remove danger">删除</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>操作模块</th>
			<th>日志时间</th>
			<th>动作类型</th>
			<th>日志状态</th>
			<th>操作类型</th>
			<th>操作人</th>
			<th>IP</th>
			<th>操作描述</th>
		</tr>
	</thead>
</table>
