$(function(){
	
	var mtype = {"OH":"1小时","HH":"半小时","5M":"每五分钟"};	//监控类型

	var cols = [{ "mData": "id", "sClass": "dt-center" },
	             { "mData": "api_name", "sClass": "dt-center", "width": "150px", "defaultContent": "---" },
	             { "mData": "call_count", "sClass": "dt-center", "defaultContent": "---" },
	             { "mData": "error_count", "sClass": "dt-center", "defaultContent": "---" },
	             { "mData": "warning_count", "sClass": "dt-center", "defaultContent": "---" },
	             { "mData": "avg_time", "sClass": "dt-center", "defaultContent": "---" },
	             { "mData": "monitor_type", "sClass": "dt-center", "defaultContent": "---", "mRender": function(data){ return mtype[data];} },
	             { "mData": "monitor_date", "sClass": "dt-center" },
	             { "mData": "monitor_time", "sClass": "dt-center"},
	             { "mData": "create_time", "sClass": "dt-center" }];
	
	createDefaultDataTable("bossTable", "monitor/payment/search", cols, false, null);
	
	//Date Picker
	$( "#qStartDay" ).datepicker({
		dateFormat: 'yy-m-d',
		defaultDate: firstDayOfMonth,
		changeMonth: true,
		changeYear: true,
		onClose: function( selectedDate ) {
			$("#qEndDay").datepicker( "option", "minDate", selectedDate );
		}
	});
	$("#qEndDay").datepicker({
		defaultDate: +1,
		dateFormat: 'yy-m-d',
		changeMonth: true,
		changeYear: true,
		onClose: function( selectedDate ) {
			$( "#qStartDay" ).datepicker( "option", "maxDate", selectedDate );
		}
	});

	$( "#qStartDay" ).datepicker("setDate", $("#qStartDay").datepicker("option", "defaultDate"));
	$( "#qEndDay" ).datepicker("setDate", $("#qEndDay").datepicker("option", "defaultDate"));
	
});
