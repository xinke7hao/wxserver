$(function(){
	
	var cols = [{ "mData": "channel_id", "sClass": "dt-center" },
                { "mData": "channel_code", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "channel_name", "sClass": "dt-left", "defaultContent": "" },
                { "mData": "channel_alias", "sClass": "dt-left", "defaultContent": "" },
                { "mData": "order_rate", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "is_available", "sClass": "dt-center", "mRender": function(data){ return data=="1"?"开启":"关闭";} },
                { "mData": "pay_url", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "key", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "create_time", "sClass": "dt-center" },
                { "mData": "update_time", "sClass": "dt-center", "defaultContent": "" }];
	
	createDefaultDataTable("bossTable", "payment/channel/search", cols, false, null);
	
	// 模态对话框的初始化：自定义按钮和一个重置内部表单的 "close" 回调
	oDialog = $( "#dialog" ).dialog({
	  title: "支付渠道信息",
      dialogClass: "no-close",
      width: 520,
      height: 600,
      autoOpen: false,
	  resizable: false,
	  draggable: false,
      modal: true,
      buttons: [
        {text: "保存", class: "btn-approve", click: function() {
        	if ($('#inputForm').valid()) {
            	$.post("payment/channel/save", $("#inputForm").serialize(), function(data){
            		showInfoDialog(data);
            		search();
            	});
            	$(this).dialog("close").empty();
        	}
		}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
      ]
    });
	
	//Create
	$('#aBtn').click( function () {
		oDialog.load("payment/channel/create").dialog( "open" );
    } );
	
	//Edit
	$('#eBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.channel_id!=""){
			oDialog.load("payment/channel/edit/"+data.channel_id).dialog( "open" );
		}
    } );
	
	//Open
	$("#oBtn").click(function(){
		var data = getSelectedData(oTable);
		if(data!=undefined && data.channel_id!=""){
			$.get("payment/channel/open/" + data.channel_id, function(data){
  			  showInfoDialog(data);
  			  search();
  		  });
		}
	});
	
	//Close
	$("#cBtn").click(function(){
		var data = getSelectedData(oTable);
		if(data!=undefined && data.channel_id!=""){
			$.get("payment/channel/close/" + data.channel_id, function(data){
  			  showInfoDialog(data);
  			  search();
  		  });
		}
	});
	
	//Delete
	$('#dBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.channel_id!=""){
			deleteConfirm("payment/channel/delete/" + data.channel_id, null);
		}
    } );
	
});
