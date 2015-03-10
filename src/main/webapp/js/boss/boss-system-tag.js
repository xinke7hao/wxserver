$(function(){
	
	var cols = [{ "mData": "id", "sClass": "dt-center", "mRender": function(data){ return "<input type='checkbox' name='ids' value='"+data+"' />"; } },
	            { "mData": "id", "sClass": "dt-center" },
	        	{ "mData": "name", "sClass": "dt-center" },
	        	{ "mData": "kind_str", "sClass": "dt-center"},
	        	{ "mData": "default_flag_str", "sClass": "dt-center" }];
	
	createCheckDataTable("bossTable", "boss/system/tag/search", cols, false, null);

	$("#checkAll").click(function(){
		$("input[type='checkbox'][name='ids']").prop("checked", $(this).prop("checked"));
	});
	
	oDialog = $( "#dialog" ).dialog({
	  title: "角标配置",
      dialogClass: "no-close",
      width: 420,
      height: 280,
      autoOpen: false,
	  resizable: false,
	  draggable: false,
      modal: true,
      buttons: [
        {text: "保存", class: "btn-approve", click: function() {
        	if ($('#inputForm').valid()) {
            	$.post("boss/system/tag/save", $("#inputForm").serialize(), function(data){
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
		oDialog.load("boss/system/tag/create").dialog( "open" );
    } );
	
	//Edit
	$('#eBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.id!=""){
			oDialog.load("boss/system/tag/edit/"+data.id).dialog( "open" );
		}
    } );
	
	//Delete
	$('#dBtn').click( function () {
		var data = getCheckedData("ids");
		if(data!=undefined){
			deleteConfirm("boss/system/tag/delete?ids="+data, null);
		}
    } );
});
