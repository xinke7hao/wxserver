$(function(){
	
	var cols = [{ "mData": "id", "sClass": "dt-center" },
	        	{ "mData": "name", "sClass": "dt-center" },
	        	{ "mData": "weight", "sClass": "dt-center"},
	        	{ "mData": "status_str", "sClass": "dt-center"},
	        	{ "mData": "qualities_str", "sClass": "dt-left" },
	        	{ "mData": "created_at", "sClass": "dt-center" },
	        	{ "mData": "updated_at", "sClass": "dt-center" }];
	
	createDefaultDataTable("bossTable", "boss/system/vip/search", cols, false, null);

	$("#checkAll").click(function(){
		$("input[type='checkbox'][name='ids']").prop("checked", $(this).prop("checked"));
	});
	
	oDialog = $( "#dialog" ).dialog({
	  title: "VIP配置",
      dialogClass: "no-close",
      width: 550,
      height: 280,
      autoOpen: false,
	  resizable: false,
	  draggable: false,
      modal: true,
      buttons: [
        {text: "保存", class: "btn-approve", click: function() {
        	if ($('#inputForm').valid()) {
            	$.post("boss/system/vip/save", $("#inputForm").serialize(), function(data){
            		showInfoDialog(data);
            		search();
            	});
            	$(this).dialog("close").empty();
        	}
		}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
      ]
    });
	
  	//Create
	$('#cBtn').click( function () {
		oDialog.load("boss/system/vip/create").dialog( "open" );
    } );
	
	//Edit
	$('#eBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.id!=""){
			oDialog.load("boss/system/vip/edit/"+data.id).dialog( "open" );
		}
    } );
	
	//Delete
	$('#dBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.id!=""){
			deleteConfirm("boss/system/vip/delete?ids="+data.id, null);
		}
    } );
});
