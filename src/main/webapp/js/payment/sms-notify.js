$(function(){

	var cols = [{ "mData": "id", "sClass": "dt-center" },
                { "mData": "connect_id", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "message_time", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "mobile", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "message", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "order_parameter", "sClass": "dt-center" },
                { "mData": "type", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "create_time", "sClass": "dt-center" }];
	
	createDefaultDataTable("bossTable", "payment/sms/search", cols, false, null);
	
	//Date Picker
	$( "#qStartDay" ).datepicker({
		maxDate: 0,
		defaultDate: firstDayOfMonth,
		dateFormat: 'yy-m-d',
		changeMonth: true,
		changeYear: true,
		onClose: function( selectedDate ) {
			$("#qEndDay").datepicker( "option", "minDate", selectedDate );
		}
	});
	$("#qEndDay").datepicker({
		defaultDate: +1,
		changeMonth: true,
		changeYear: true,
		dateFormat: 'yy-m-d',
		onClose: function( selectedDate ) {
			$( "#qStartDay" ).datepicker( "option", "maxDate", selectedDate );
		}
	});

	//设置默认时间差为1周
	$( "#qStartDay" ).datepicker("setDate", $("#qStartDay").datepicker("option", "defaultDate"));
	$( "#qEndDay" ).datepicker("setDate", $("#qEndDay").datepicker("option", "defaultDate"));
	
});