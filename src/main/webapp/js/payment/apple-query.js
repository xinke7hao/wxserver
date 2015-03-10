$(function(){

	$("#appleSearch").click(function(){
		
		//至少有一个查询条件
		if($("#orderId").val().trim()=='' && $("#payAccount").val().trim()=='' && $("#receipt").val().trim()==''){
			showError("至少填写一个查询条件");
			return false;
		}
		
		$("#myorders").empty();
		$.post("payment/apple/search", $("#appleQueryForm").serialize(), function(res){
			if(res){
				var orders_length = res["total_count"];
				var ordertbody = "";
				if(orders_length>0){
					var orders = res["data"];
					for(var i=0;i<orders_length;i++){
						var d = orders[i];
						ordertbody += "<tr><td>"+d["order_id"]+"</td><td>"+d["pay_account"]+"</td><td>"+d["status"]+"</td><td>"+d["status_msg"]+"</td></tr>";
						ordertbody += "<tr><td colspan='4' align='left' style='height:50px;border-top: #ccc solid 1px;white-space:normal;'>" +  d["response"] + "</td></tr>";
					}
				} else {
					ordertbody += "<tr><td colspan='4'><font color='blue'>没有搜索到符合条件的数据.</font></td></tr>";
				}
				$("#myorders").append(ordertbody);
			}
		});
	});
	
});