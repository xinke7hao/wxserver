$(function(){

	var cols = [{ "mData": "id", "sClass": "dt-center", "defaultContent": "" },
	             { "mData": "total_count", "sClass": "dt-center", "defaultContent": "" },
	             { "mData": "pay_count", "sClass": "dt-center", "defaultContent": "" },
	             { "mData": "sync_count", "sClass": "dt-center", "defaultContent": "" },
	             { "mData": "async_count", "sClass": "dt-center", "defaultContent": "" },
	             { "mData": "close_count", "sClass": "dt-center", "defaultContent": "" },
	             { "mData": "business_count", "sClass": "dt-center", "defaultContent": "" },
	             { "mData": "monitor_date", "sClass": "dt-center" },
	             { "mData": "monitor_time", "sClass": "dt-center" },
	             { "mData": "create_time", "sClass": "dt-center" }];
	
	createDefaultDataTable("bossTable", "monitor/payorder/search", cols, false, null);
	
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

	//设置默认时间差为1周
	$( "#qStartDay" ).datepicker("setDate", $("#qStartDay").datepicker("option", "defaultDate"));
	$( "#qEndDay" ).datepicker("setDate", $("#qEndDay").datepicker("option", "defaultDate"));
	
});
