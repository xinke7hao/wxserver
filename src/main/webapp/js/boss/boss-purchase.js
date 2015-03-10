//debugger;
$(function(){
	var cols = [{ "mData": "id", "sClass": "dt-center" },
	        	{ "mData": "account_passport", "sClass": "dt-left" },
	        	{ "mData": "order_uuid", "sClass": "dt-center" },
	        	{ "mData": "order_paid_at", "sClass": "dt-center" },
	        	{ "mData": "order_created_at", "sClass": "dt-center" },
	        	{ "mData": "channel_name", "sClass": "dt-left" },
	        	{ "mData": "product_name", "sClass": "dt-center" },
	        	{ "mData": "begin_time", "sClass": "dt-center", "defaultContent": "" },
	        	{ "mData": "end_time", "sClass": "dt-center", "defaultContent": "" },
	        	{ "mData": "quantity", "sClass": "dt-center" },
	        	{ "mData": "category_str", "sClass": "dt-center" },
	        	{ "mData": "delivered_at", "sClass": "dt-center" }];

	createDefaultDataTable("bossTable", "boss/purchase/search", cols, false, null);

});
