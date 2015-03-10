<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="js/payment/mobile-query.js"></script>
<div class="bossToolBar">
	<label><span>手机号:</span><input id="qMobile" type="text" /></label>
	<a href="#" id="mobileSearch" class="button search">查询</a>
	<a href="javascript:resetUrl('payment/mobile');" class="button reload">重置</a>
</div>
<div class="container" style="display:none;">
<div id="smsStatus" class="content-div">
<label>平台状态：</label><span id="localStatus"></span>
<label>9588状态：</label><span id="remoteStatus"></span>
</div>
<label>支付流水:</label>
<input type="hidden" id="isAdmin" value="${loginUser.isAdmin}" />
<div id="myorders" class="content-div"></div>
<label>异步通知:</label>
<div id="mynotifys" class="content-div"></div>
</div>