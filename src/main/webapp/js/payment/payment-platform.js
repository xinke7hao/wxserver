$(function(){
	
	var cols = [{ "mData": "platform_id", "sClass": "dt-center" },
                { "mData": "platform_name", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "remark", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "secret", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "status", "sClass": "dt-center", "mRender": function(data){ return data=="0"?"不可用":(data=="1"?"可用":"删除");} },
                { "mData": "create_time", "sClass": "dt-center" },
                { "mData": "update_time", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "ext_date", "sClass": "dt-center", "defaultContent": "" },
                { "mData": "contact", "sClass": "dt-center", "defaultContent": "" }];
	
	createDefaultDataTable("bossTable", "payment/platform/search", cols, false, null);
	
	// 模态对话框的初始化：自定义按钮和一个重置内部表单的 "close" 回调
	oDialog = $( "#dialog" ).dialog({
	  title: "业务平台信息",
      dialogClass: "no-close",
      width: 500,
      height: 400,
      autoOpen: false,
	  resizable: false,
	  draggable: false,
      modal: true,
      buttons: [
        {text:"保存", class: "btn-approve", click: function() {
        	if ($('#inputForm').valid()) {
            	$.post("payment/platform/save", $("#inputForm").serialize(), function(data){
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
		oDialog.load("payment/platform/create").dialog( "open" );
    } );
	
	//Edit
	$('#eBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.platform_id!=""){
			oDialog.load("payment/platform/edit/"+data.platform_id).dialog( "open" );
		}
    } );
	
	//Open
	$("#oBtn").click(function(){
		var data = getSelectedData(oTable);
		if(data!=undefined && data.platform_id!=""){
			$.get("payment/platform/open/" + data.platform_id, function(data){
  			  showInfoDialog(data);
  			  search();
  		  });
		}
	});
	
	//Close
	$("#cBtn").click(function(){
		var data = getSelectedData(oTable);
		if(data!=undefined && data.platform_id!=""){
			$.get("payment/platform/close/" + data.platform_id, function(data){
  			  showInfoDialog(data);
  			  search();
  		  });
		}
	});
	
	//Delete
	$('#dBtn').click(function(){
		var data = getSelectedData(oTable);
		if(data!=undefined && data.platform_id!=""){
			deleteConfirm("payment/platform/delete/" + data.platform_id, null);
		}
    } );
	
});
