<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/boss/boss-product-vip.js"></script>
<form id="queryForm" method="post">
<div class="bossToolBar">
	<label><span>VIP产品名:</span><input name="cname" type="text" /></label> 
	<a href="javascript:search();" class="button search">搜索</a>
	<bmc:authority menu="boss/product/vip" right="create" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="cBtn" class="button add">添加</a>
	</bmc:authority>
	<bmc:authority menu="boss/product/vip" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="eBtn" class="button edit">编辑</a>
	</bmc:authority>
	<bmc:authority menu="boss/product/vip" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
		<a id="dBtn" class="button remove danger">删除</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>名称</th>
			<th>分类</th>
			<th>会员</th>
			<th>价格(元)</th>
			<th>时长</th>
			<th>前台可见</th>
			<th>创建时间</th>
			<th>最后修改时间</th>
		</tr>
	</thead>
</table>
