$(function(){
	
	var cols = [{ "mData": "id", "sClass": "dt-center" },
	        	{ "mData": "name", "sClass": "dt-center" },
	        	{ "mData": "kind_str", "sClass": "dt-center" },
	        	{ "mData": "vip_str", "sClass": "dt-center" },
	        	{ "mData": "yuan_price", "sClass": "dt-center"},
	        	{ "mData": "days", "sClass": "dt-center" },
	        	{ "mData": "show_str", "sClass": "dt-center" },
	        	{ "mData": "created_at", "sClass": "dt-center" },
	        	{ "mData": "updated_at", "sClass": "dt-center" }];
	
	createDefaultDataTable("bossTable", "boss/product/vip/search", cols, false, null);

	oDialog = $( "#dialog" ).dialog({
		  title: "VIP产品配置",
	      dialogClass: "no-close",
	      width: 500,
	      height: 380,
	      autoOpen: false,
		  resizable: false,
		  draggable: false,
	      modal: true,
	      buttons: [
	        {text: "保存", class: "btn-approve", click: function() {
	        	if ($('#inputForm').valid()) {
	            	$.post("boss/product/vip/save", $("#inputForm").serialize(), function(data){
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
			oDialog.load("boss/product/vip/create").dialog( "open" );
	    } );
		
		//Edit
		$('#eBtn').click( function () {
			var data = getSelectedData(oTable);
			if(data!=undefined && data.id!=""){
				oDialog.load("boss/product/vip/edit/"+data.id).dialog( "open" );
			}
	    } );
		
		//Delete
		$('#dBtn').click( function () {
			var data = getSelectedData(oTable);
			if(data!=undefined && data.id!=""){
				deleteConfirm("boss/product/vip/delete?ids="+data.id, null);
			}
	    } );
});
