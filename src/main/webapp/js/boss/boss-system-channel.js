$(function(){
	
	var cols = [{ "mData": "id", "sClass": "dt-center", "mRender": function(data){ return "<input type='checkbox' name='ids' value='"+data+"' />"; }},
	            { "mData": "id", "sClass": "dt-center" },
	        	{ "mData": "name", "sClass": "dt-left" },
	        	{ "mData": "uuid", "sClass": "dt-center"},
	        	{ "mData": "fee_per_trans", "sClass": "dt-center", "defaultContent": "" },
	        	{ "mData": "fee_percent", "sClass": "dt-center", "defaultContent": "" },
	        	{ "mData": "discount", "sClass": "dt-center" },
	        	{ "mData": "status_str", "sClass": "dt-center" },
	        	{ "mData": "created_at", "sClass": "dt-center" },
	        	{ "mData": "updated_at", "sClass": "dt-center" }];
	
	createCheckDataTable("bossTable", "boss/system/channel/search", cols, false, null);

	$("#checkAll").click(function(){
		$("input[type='checkbox'][name='ids']").prop("checked", $(this).prop("checked"));
	});
	
	oDialog = $( "#dialog" ).dialog({
	  title: "支付渠道配置",
      dialogClass: "no-close",
      width: 460,
      height: 350,
      autoOpen: false,
	  resizable: false,
	  draggable: false,
      modal: true,
      buttons: [
        {text: "保存", class: "btn-approve", click: function() {
        	if ($('#inputForm').valid()) {
            	$.post("boss/system/channel/save", $("#inputForm").serialize(), function(data){
            		showInfoDialog(data);
            		search();
            	});
            	$(this).dialog("close").empty();
        	}
		}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
      ]
    });
	
  	//Create
	$('#cBtn').click(function(){
		oDialog.load("boss/system/channel/create").dialog( "open" );
    } );
	
	//Edit
	$('#eBtn').click(function(){
		var data = getSelectedData(oTable);
		if(data!=undefined && data.id!=""){
			oDialog.load("boss/system/channel/edit/"+data.id).dialog( "open" );
		}
    } );
	
	//Delete
	$('#dBtn').click(function(){
		var data = getCheckedData("ids");
		if(data!=undefined){
			deleteConfirm("boss/system/channel/delete", {ids:data});
		}
    } );
});
