$(function(){
	
	var cols = [{ "mData": "id", "sClass": "dt-center", "mRender": function(data){ return "<input type='checkbox' name='ids' value='"+data+"' />"; } },
	            { "mData": "id", "sClass": "dt-center" },
	            { "mData": "passport", "sClass": "dt-left" },
	        	{ "mData": "point", "sClass": "dt-center" },
	        	{ "mData": "level", "sClass": "dt-center" },
	        	{ "mData": "mgb", "sClass": "dt-center" },
	        	{ "mData": "business_str", "sClass": "dt-center" },
	        	{ "mData": "vip_str", "sClass": "dt-center", "defaultContent":"" },
	        	{ "mData": "status_str", "sClass": "dt-center" },
	        	{ "mData": "vip_begin_time", "sClass": "dt-center", "defaultContent":"" },
	        	{ "mData": "vip_end_time", "sClass": "dt-center", "defaultContent":"" }];

	createCheckDataTable("bossTable", "boss/account/search", cols, false, null);
	
	$("#checkAll").click(function(){
		$("input[type='checkbox'][name='ids']").prop("checked", $(this).prop("checked"));
	});
	
	oDialog = $( "#dialog" ).dialog({
	  title: "用户帐号信息",
      dialogClass: "no-close",
      width: 450,
      height: 380,
      autoOpen: false,
	  resizable: false,
	  draggable: false,
      modal: true,
      buttons: [
        {text: "保存", class: "btn-approve", click: function() {
        	if ($('#inputForm').valid()) {
            	$.post("boss/account/save", $("#inputForm").serialize(), function(data){
            		showInfoDialog(data);
            		search();
            	});
            	$(this).dialog("close").empty();
        	}
		}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
      ]
    });
	
  	//Cancel Service
	$('#cBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.id!=""){
			bossConfirm("boss/account/close/"+data.id, null, "您确认取消该账户的所有订购关系吗?");
		}
    } );
	
	//Edit
	$('#eBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.id!=""){
			oDialog.load("boss/account/edit/"+data.id).dialog( "open" );
		}
    } );
	
	//Delete
	$('#dBtn').click( function () {
		var data = getCheckedData("ids");
		if(data!=undefined){
			deleteConfirm("boss/account/delete/"+data, null);
		}
    } );
});
