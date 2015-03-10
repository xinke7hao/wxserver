<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	var cols = [{ "mData": "id", "sClass": "dt-center" },
	        	{ "mData": "asset_name", "sClass": "dt-center" },
	        	{ "mData": "asset_partial_number", "sClass": "dt-center" },
	        	{ "mData": "hashcode", "sClass": "dt-center", "defaultContent": "" },
	        	{ "mData": "quality_str", "sClass": "dt-center" },
	        	{ "mData": "min_vip_str", "sClass": "dt-center" },
	        	{ "mData": "created_at", "sClass": "dt-center" },
	        	{ "mData": "updated_at", "sClass": "dt-center", "defaultContent": ""}];
	
	createDefaultDataTable("bossTable", "boss/content/vip/viewasset", cols, false, null);
</script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<label><span>媒资文件ID:</span><input name="id" type="text" /></label>
	<label><span>主媒资ID:</span><input name="asset_id" type="text" value="${id}" /></label>
	<label><span>主媒资名:</span><input name="asset_name" type="text" /></label>
	<label><span>文件Hash:</span><input name="hashcode" type="text" /></label>
	<a href="javascript:search();" class="button search">搜索</a>
	<a href="javascript:resetUrl('boss/content/vip');" class="button reload">返回</a>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>主媒资名</th>
			<th>第几集</th>
			<th>文件Hash</th>
			<th>清晰度(码率)</th>
			<th>最低VIP</th>
			<th>创建时间</th>
			<th>更新时间</th>
		</tr>
	</thead>
</table>
