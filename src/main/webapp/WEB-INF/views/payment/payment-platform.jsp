<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/payment/payment-platform.js"></script>
<form id="queryForm" action="payment/platform/export" method="post">
<div class="bossToolBar">
	<bmc:authority menu="payment/platform" right="create" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="aBtn" href="javascript:void(0);" class="button add">添加</a>
	</bmc:authority>
	<bmc:authority menu="payment/platform" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="eBtn" href="javascript:void(0);" class="button edit">编辑</a>
	</bmc:authority>
	<bmc:authority menu="payment/platform" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="dBtn" href="javascript:void(0);" class="button remove danger">删除</a>
	</bmc:authority>
	<bmc:authority menu="payment/platform" right="open" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="oBtn" href="javascript:void(0);" class="button unlock">开启</a>
	</bmc:authority>
	<bmc:authority menu="payment/platform" right="close" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="cBtn" href="javascript:void(0);" class="button lock">关闭</a>
	</bmc:authority>
	<bmc:authority menu="payment/platform" right="export" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a href="javascript:doExportCsv();" class="button log">导出</a>
	</bmc:authority>
</div>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>平台名</th>
			<th>平台简介</th>
			<th>签名</th>
			<th>状态</th>
			<th>创建时间</th>
			<th>更新时间</th>
			<th>附加信息</th>
			<th>接口人</th>
		</tr>
	</thead>
</table>
</form>