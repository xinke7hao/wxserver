$(function(){

	var cols = [{ "mData": "id", "sClass": "dt-center" },
                { "mData": "item", "sClass": "dt-left", "defaultContent": "" },
                { "mData": "type", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "platform_name", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "channel_name", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "status", "sClass": "dt-center" },
                { "mData": "create_time", "sClass": "dt-center" },
                { "mData": "update_time", "sClass": "dt-center", "defaultContent": "" }];
	
	createDefaultDataTable("bossTable", "payment/blacklist/search", cols, false, null);
	
	// 模态对话框的初始化：自定义按钮和一个重置内部表单的 "close" 回调
	oDialog = $( "#dialog" ).dialog({
	  title: "黑名单信息",
      dialogClass: "no-close",
      width: 450,
      height: 320,
      autoOpen: false,
	  resizable: false,
	  draggable: false,
      modal: true,
      buttons: [
        {text:"保存", class: "btn-approve", click: function() {
        	if ($('#inputForm').valid()) {
            	$.post("payment/blacklist/save", $("#inputForm").serialize(), function(data){
            		showInfoDialog(data);
            		search();
            	});
            	$( this ).dialog( "close" ).empty();
        	}
		}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
      ]
    });
	
	//Create
	$('#aBtn').click( function () {
		oDialog.load("payment/blacklist/create").dialog( "open" );
    } );
	
	//Edit
	$('#eBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.id!=""){
			oDialog.load("payment/blacklist/edit/"+data.id).dialog( "open" );
		}
    } );
	
	//Delete
	$('#dBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.id!=""){
			deleteConfirm("payment/blacklist/delete/" + data.id, null);
		}
    } );
	
});
