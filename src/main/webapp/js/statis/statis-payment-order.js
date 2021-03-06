$(function(){

	var cols = [{ "mData": "day", "sClass": "dt-center"},
                { "mData": "total_count", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "finish_count", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "total_amount", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "sync_count", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "async_count", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "close_count", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "recon_count", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "business_count", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "create_time", "width": "130px", "sClass": "dt-center"}];
	
	createDefaultDataTable("bossTable", "statis/payment/order/search", cols, false, null);

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
