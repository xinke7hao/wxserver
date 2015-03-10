$(function(){

	var logtype = {"D":"删除","U":"编辑","Q":"查询","H":"HTTP调用"};
	
	var cols = [{ "mData": "id", "sClass": "dt-center" },
	             { "mData": "loginUser", "sClass": "dt-center" },
	             { "mData": "loginTime", "sClass": "dt-center" },
	             { "mData": "loginStatus", "sClass": "dt-center", "mRender": function(data){ return data=="0"?"成功":"失败"; } },
	             { "mData": "loginIp", "sClass": "dt-center" },
	             { "mData": "loginDesc", "sClass": "dt-left", "defaultContent": "" }];
	
	createDefaultDataTable("bossTable", "monitor/login/search", cols, false, null);
	
	//Date Picker
	$('#qStartDay').datetimepicker({dateFormat: 'yy-m-d'});
	$('#qEndDay').datetimepicker({dateFormat: 'yy-m-d'});
});
