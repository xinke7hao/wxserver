$(function(){
	
	var cols = [{ "mData": "id", "sClass": "dt-center" },
	        	{ "mData": "name", "sClass": "dt-left" },
	        	{ "mData": "yuan_price", "sClass": "dt-center"},
	        	{ "mData": "days", "sClass": "dt-center" },
	        	{ "mData": "created_at", "sClass": "dt-center" },
	        	{ "mData": "updated_at", "sClass": "dt-center" }];
	
	createDefaultDataTable("bossTable", "boss/product/single/search", cols, false, null);

	oDialog = $( "#dialog" ).dialog({
		  title: "单点产品配置",
	      dialogClass: "no-close",
	      width: 450,
	      height: 300,
	      autoOpen: false,
		  resizable: false,
		  draggable: false,
	      modal: true,
	      buttons: [
	        {text: "保存", class: "btn-approve", click: function() {
	        	if ($('#inputForm').valid()) {
	            	$.post("boss/product/single/save", $("#inputForm").serialize(), function(data){
	            		showInfoDialog(data);
	            		search();
	            	});
	            	$(this).dialog("close").empty();
	        	}
			}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
	      ]
	    });
		
		//Edit
		$('#eBtn').click( function () {
			var data = getSelectedData(oTable);
			if(data!=undefined && data.id!=""){
				oDialog.load("boss/product/single/edit/"+data.id).dialog( "open" );
			}
	    } );
		
		//Delete
		$('#dBtn').click( function () {
			var data = getSelectedData(oTable);
			if(data!=undefined && data.id!=""){
				deleteConfirm("boss/product/single/delete?ids="+data.id, null);
			}
	    } );
});
