//加载支付状态数据
function loadPayDetail(mValue){
	$.get("payment/mobile/pay", {mobile: mValue}, function(res){
		
		if(res){
			
			//平台系统状态
			var local = res["status"];
			$("#localStatus").html(local=="1"?"<font color='blue'>已开通</font>":"<font color='red'>未开通</font>");
			
			//支付流水
			var orders = local=="2" ? [] : res["order"]["data"];
			var order_len = local=="2" ? 0 : res["order"]["totalCount"];
			var ordertable = "<table><thead><tr><th>支付流水ID</th><th>业务订单id</th><th>支付渠道</th><th>产品id</th><th>产品名称</th><th>账号</th><th>手机号</th><th>金额(元)</th><th>状态</th><th>创建时间</th><th>对账状态</th><th>业务方处理结果</th><th>操作</th></tr><thead><tbody>";
			for(var i=0;i<order_len;i++){
				var d = orders[i];
				ordertable += "<tr><td>"+d["id"]+"</td><td>"+d["business_order_id"]+"</td><td>"+d["channel_name"]+"</td><td>"
							+d["product_id"]+"</td><td>"+d["product_name"]+"</td><td>"+d["account"]+"</td><td>"+d["mobile"]+"</td><td>"
							+d["amount"]+"</td><td>"+d["status"]+"</td><td>"+d["create_time"]+"</td><td>"+d["recon_status"]+"</td><td>"
							+d["business_result"]+"</td>";
				//本地未开通 0 + 异步返回 3 + 未处理 0 的流水添加发货功能
				//并且需要是管理员权限
				if($("#isAdmin").val()=='Y' && local=="0" && d["pay_code"]=="3" && d["business_code"]=="0"){
					ordertable += "<td><a href='#' onclick='doDeliver('"+d["id"]+"');'>发货</a></td>";
				} else {
					ordertable += "<td>----</td>";
				}
				
				ordertable += "</tr>";
			}
			ordertable += "</tbody></table>";
			$("#myorders").append(ordertable);
			
			//异步通知
			var notifytable = "<table><thead><tr><th>ID</th><th>连接ID</th><th>消息时间</th><th>手机号</th><th>消息内容</th><th>订阅参数</th><th>通知类型</th><th>创建时间</th></tr><thead><tbody>";
			
			if(res["notify"]!=null){
				var notifys = local=="2" ? [] : res["notify"]["data"];
				var notify_len = local=="2" ? 0 : res["notify"]["totalCount"];
				for(var i=0;i<notify_len;i++){
					var d = notifys[i];
					notifytable += "<tr><td>"+d["id"]+"</td><td>"+d["connect_id"]+"</td><td>"+d["message_time"]+"</td><td>"+d["mobile"]+"</td><td>"
					+d["message"]+"</td><td>"+d["order_parameter"]+"</td><td>"+d["type"]+"</td><td>"+d["create_time"]+"</td></tr>"
				}
			}
			notifytable += "</tbody></table>";
			$("#mynotifys").append(notifytable);
			
		} else {
			$("#localStatus").html("查询失败.");
		}
		
	});
}

//获取9588异步通知状态
function loadSmsStatus(mValue){
	$.get("payment/mobile/sms", {mobile: mValue}, function(res){
		if(res){
			$("#remoteStatus").html(res["status"]=="1"?"<font color='blue'>已开通</font>":"<font color='red'>未开通</font>");
		} else {
			$("#remoteStatus").html("查询失败.");
		}
	});
}

//订单发货
function doDeliver(oid){
	$.post("payment/mobile/deliver", {orderid: oid}, function(res){
		showInfoDialog(res=='Y'?"操作成功":data.error);
		if(res=="Y"){
			$("#localStatus").html("");
			$("#myorders").html("");
			$("#mynotifys").html("");
			loadPayDetail();
		}
	});
}

$(function(){

	$("#mobileSearch").click(function(){

		//先清空之前的查询结果
		$("#localStatus").html("");
		$("#remoteStatus").html("");
		$("#myorders").html("");
		$("#mynotifys").html("");
		$("#qMobile").val($("#qMobile").val().trim());
		var mValue = $("#qMobile").val();
		if(mValue==""){
			showError("手机号不能为空");
		} else {
			$(".container").show();
			loadPayDetail(mValue);
			loadSmsStatus(mValue);
		}
		
	});
	
});