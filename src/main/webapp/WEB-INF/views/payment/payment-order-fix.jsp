<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="bmc" uri="http://www.hunantv.com/mbp-bmc-tags"%>
<script>
$('#fBtn').click( function () {
	var oid = $("#oid").val().trim();
	if(oid.length == 0){
		showError("请先填写订单号!");
	} else {
		$.post("payment/fix/dofix", {id:oid}, function(data){
			showInfoDialog(data);
			$("#oid").val('');
		});
	}
} );
</script>
<div class="bossToolBar">
	<label><span>订单号:</span><input id="oid" type="text" style="width:160px;" /></label>
	<bmc:authority menu="payment/fix" right="dofix" authorities="${userAuthorities}" isAdmin="${loginUser.isAdmin}" >
	<a id="fBtn" href="javascript:void(0);" class="button tag">补单</a>
	</bmc:authority>
</div>
