<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/boss/boss-content-source.js"></script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<label><span>名称:</span><input name="cname" type="text" /></label> 
	<label><span>状态:</span>
	<select name="cstatus">
		<option value="">全部</option>
		<option value="0">上映</option>
		<option value="1">下线</option>
	</select>
	</label>
	<a href="javascript:search();" class="button search">搜索</a>
	<bmc:authority menu="boss/content/source" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="pBtn" class="button edit">VIP收费</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th style="width:20px;"><input type="checkbox" id="checkAll" /></th>
			<th>ID</th>
			<th>名称</th>
			<th>清晰度</th>
			<th>VIPID</th>
			<th>最低VIP</th>
			<th>频道标识</th>
			<th>状态</th>
			<th>最后修改时间</th>
		</tr>
	</thead>
</table>
