<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/boss/boss-content-vip.js"></script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<label><span>媒资ID:</span><input name="cid" type="text" /></label> 
	<label><span>媒资名称:</span><input name="cname" type="text" /></label> 
	<label><span>一级分类:</span>
	<select name="ctype">
		<option value="">全部</option>
		<c:forEach items="${types}" var="m">
			<option value="${m}">${m}</option>
		</c:forEach>
	</select>
	</label>
	<label><span>是否按集收费:</span>
	<select name="charge">
		<option value="0">全部</option>
		<option value="1">是</option>
		<option value="2">否</option>
	</select>
	</label>
	<label><span>状态:</span>
	<select name="cstatus">
		<option value="">全部</option>
		<option value="0">上映</option>
		<option value="1">下线</option>
	</select>
	</label>
	<label class="radio-group-line">
	<label><input name="onlyValid" type="radio" value="N" checked />所有</label>
	<label><input name="onlyValid" type="radio" value="Y" />有效</label>
	</label>
	<a id="qBtn" class="button search">搜索</a>
	<bmc:authority menu="boss/content/vip" right="pay" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="sBtn" class="button edit">收费设置</a>
	</bmc:authority>
	<bmc:authority menu="boss/content/vip" right="episode" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="pBtn" class="button edit">按集收费</a>
	</bmc:authority>
	<bmc:authority menu="boss/content/vip" right="operation" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="oBtn" class="button edit">运营方式</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" style="display:none;" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th style="width:20px;"><input type="checkbox" id="checkAll" /></th>
			<th>ID</th>
			<th>名称</th>
			<th>分类</th>
			<th>最低VIP身份</th>
			<th>运营模式代码</th>
			<th>角标ID</th>
			<th>媒资运营模式</th>
			<th>VIP</th>
			<th>集合</th>
			<th>单点</th>
			<th>是否按集收费</th>
			<th>状态</th>
			<th>最后修改时间</th>
			<th>操作</th>
		</tr>
	</thead>
</table>
