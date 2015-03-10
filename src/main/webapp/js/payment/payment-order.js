$(function(){

	var cols = [{ "mData": "id", "width": "130px", "sClass": "dt-center"},
                { "mData": "business_order_id", "width": "130px", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "channel_name", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "product_id", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "product_name", "width": "130px", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "account", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "mobile", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "amount", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "status", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "create_time", "width": "130px", "sClass": "dt-center"},
                { "mData": "recon_status", "sClass": "dt-center", "defaultContent": ""},
                { "mData": "business_result", "sClass": "dt-center", "defaultContent": ""}];
	
	createDefaultDataTable("bossTable", "payment/order/search", cols, false, null);
	
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
	
	// 模态对话框的初始化：自定义按钮和一个重置内部表单的 "close" 回调
    cDialog = $( "#dialog-detail" ).dialog({
      title: "支付流水详情",
      width: 400,
      height: 420,
      autoOpen: false,
      modal: true
    });
	
	//Detail
	$('#ddBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.id!="") {
			$( "#dialog-detail" ).load('payment/order/view/'+data.id).dialog({
			    title: "支付流水详情",
			    autoOpen: true,
			    width: 400,
				height: 450,
				resizable: false,
				draggable: false,
				modal: true,
				buttons: [{
					text:"关闭", class:"btn-cancel", click:function() {
						$(this).dialog("close").empty();
					}
				}]
			});
		}
    } );
	
	//Delete (Single One)
	$('#dBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.id!="") {
			deleteConfirm("payment/order/delete/" + data.id, null);
		}
	} );
	
	// 模态对话框的初始化：自定义按钮和一个重置内部表单的 "close" 回调
	oDialog = $( "#dialog" ).dialog({
		title: "支付流水信息",
		width: 400,
		height: 500,
		autoOpen: false,
		resizable: false,
		draggable: false,
        closeOnEscape: true,
		modal: true,
		buttons: [
			{text: "保存", class: "btn-approve", click: function() {
				if($("#inputForm").valid()){
					$.post("payment/order/save", $("#inputForm").serialize(), function(data){
						showInfoDialog(data);
						search();
					});
					$(this).dialog("close").empty();
				}
			}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
		]
	});
	
	//Edit
	$('#eBtn').click(function(){
		var data = getSelectedData(oTable);
		if(data!=undefined && data.id!="") {
			oDialog.load("payment/order/edit/"+data.id).dialog( "open" );
		}
	} );
	
});
