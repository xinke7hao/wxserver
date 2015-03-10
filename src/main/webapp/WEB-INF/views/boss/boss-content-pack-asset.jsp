<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.assetTable {
	margin: 0px;
	width: 100%;
	text-align: center;
}
</style>
<script src="js/boss/boss-content-pack-asset.js"></script>
<form id="queryForm" action="boss/content/pack/asexport" method="post">
<input type="hidden" id="pid" name="pid" value="${pid}" />
<input type="hidden" id="oper" name="oper" value="" />
<div class="bossToolBar" style="margin-bottom:1px;padding-bottom:1px;">
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
	<a href="javascript:search();" class="button search">搜索</a>
	<a href="javascript:resetUrl('boss/content/pack');" class="button reload">返回</a>
	<a id="sBtn" class="button edit">打包</a>
	<a id="oBtn" class="button edit">运营方式</a>
	<a id="pBtn" class="button edit">按集收费</a>
	<a id="eBtn" onclick="exportPackAsset();" class="button log">导出集合片单</a>
</div>
</form>
<table class="display dataTable no-footer" cellspacing="0">
<thead id="assethead">
		<tr>
			<th class="dt-center" style="width:20px;">&nbsp;</th>
			<th class="dt-center">ID</th>
			<th class="dt-left">名称</th>
			<th class="dt-center">分类</th>
			<th class="dt-center">最低VIP身份</th>
			<th class="dt-center">媒资运营模式</th>
			<th class="dt-center">VIP</th>
			<th class="dt-center">集合</th>
			<th class="dt-center">单点</th>
			<th class="dt-center">是否按集收费</th>
			<th class="dt-center">状态</th>
			<th class="dt-center">最后修改时间</th>
		</tr>
</thead>
<tbody id="assets">
<c:forEach items="${assets}" var="as">
<tr class="astr" style="background-color: #BDFCC9;">
	<td class="dt-center"><input type='checkbox' name='ids' value='${as.id}' checked /></td>
	<td class="dt-center">${as.id}</td>
	<td class="dt-left">${as.name}</td>
	<td class="dt-center">${as.kind}</td>
	<td class="dt-center">${as.min_vip_str}</td>
	<td class="dt-center">${as.operation_mode_str}</td>
	<td class="dt-center">${as.is_vip_product?"有效":"无效"}</td>
	<td class="dt-center">${as.is_collection_product?"有效":"无效"}</td>
	<td class="dt-center">${as.is_single_product?"有效":"无效"}</td>
	<td class="dt-center">${as.start_charges>0?"是":"否"}</td>
	<td class="dt-center">${as.status_str}</td>
	<td class="dt-center">${as.updated_at}</td>
</tr>
</c:forEach>
</tbody>
</table>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th style="width:20px;">&nbsp;</th>
			<th>ID</th>
			<th>名称</th>
			<th>分类</th>
			<th>最低VIP身份</th>
			<th>媒资运营模式</th>
			<th>VIP</th>
			<th>集合</th>
			<th>单点</th>
			<th>是否按集收费</th>
			<th>状态</th>
			<th>最后修改时间</th>
		</tr>
	</thead>
</table>
