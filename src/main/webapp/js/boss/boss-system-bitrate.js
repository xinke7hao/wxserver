$(function(){
	
	var cols = [{ "mData": "id", "sClass": "dt-center" },
	        	{ "mData": "name", "sClass": "dt-center" },
	        	{ "mData": "sharp_id", "sClass": "dt-center", "defaultContent": ""},
	        	{ "mData": "created_at", "sClass": "dt-center" },
	        	{ "mData": "updated_at", "sClass": "dt-center" }];
	
	createDefaultDataTable("bossTable", "boss/system/bitrate/search", cols, false, null);

	$("#checkAll").click(function(){
		$("input[type='checkbox'][name='ids']").prop("checked", $(this).prop("checked"));
	});
	
	oDialog = $( "#dialog" ).dialog({
	  title: "清晰度配置",
      dialogClass: "no-close",
      width: 430,
      height: 240,
      autoOpen: false,
	  resizable: false,
	  draggable: false,
      modal: true,
      buttons: [
        {text: "保存", class: "btn-approve", click: function() {
        	if ($('#inputForm').valid()) {
            	$.post("boss/system/bitrate/save", $("#inputForm").serialize(), function(data){
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
		oDialog.load("boss/system/bitrate/create").dialog( "open" );
    } );
	
	//Edit
	$('#eBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.id!=""){
			oDialog.load("boss/system/bitrate/edit/"+data.id).dialog( "open" );
		}
    } );
	
	//Delete
	$('#dBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.id!=""){
			deleteConfirm("boss/system/bitrate/delete?ids="+data.id, null);
		}
    } );
});
