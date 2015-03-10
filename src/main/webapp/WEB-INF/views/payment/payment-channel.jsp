<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script src="js/payment/payment-channel.js"></script>
<form id="queryForm" method="post" action="payment/channel/export" >
<div class="bossToolBar">
	<bmc:authority menu="payment/channel" right="create" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="aBtn" href="javascript:void(0);" class="button add">添加</a>
	</bmc:authority>
	<bmc:authority menu="payment/channel" right="edit" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="eBtn" href="javascript:void(0);" class="button edit">编辑</a>
	</bmc:authority>
	<bmc:authority menu="payment/channel" right="delete" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="dBtn" href="javascript:void(0);" class="button remove danger">删除</a>
	</bmc:authority>
	<bmc:authority menu="payment/channel" right="open" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="oBtn" href="javascript:void(0);" class="button unlock">开启</a>
	</bmc:authority>
	<bmc:authority menu="payment/channel" right="close" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="cBtn" href="javascript:void(0);" class="button lock">关闭</a>
	</bmc:authority>
	<bmc:authority menu="payment/channel" right="export" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a href="javascript:doExportCsv();" class="button log">导出</a>
	</bmc:authority>
</div>
</form>
<table id="bossTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th>ID</th>
			<th>代码</th>
			<th>名称</th>
			<th>别名</th>
			<th>渠道费率</th>
			<th>状态</th>
			<th>接口地址</th>
			<th>对应key</th>
			<th>创建时间</th>
			<th>更新时间</th>
		</tr>
	</thead>
</table>
