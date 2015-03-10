$(function(){
	
	var cols = [{ "mData": "moduleId", "sClass": "dt-center", "bVisible": false },
                { "mData": "menuId", "sClass": "dt-center", "bVisible": false },
                { "mData": "moduleName", "sClass": "dt-center", "bWidth": "100px" },
	            { "mData": "menuName", "sClass": "dt-left", "bWidth": "100px" },
	            { "mData": "menuDesc", "sClass": "dt-left", "defaultContent": "" }];
	
	var colDef = [ {
	    "targets": 4,
	    "render": function ( data, type, full, meta ) {
		   return type==='display' && data!=null && data.length > 60 ? '<span title="'+data+'">'+data.substr( 0, 58 )+'...</span>' : data;
	    }
	  } ];
	
	createDefaultDataTable("bossTable", "menu/search", cols, false, colDef);
	
	// 模态对话框的初始化：自定义按钮和一个重置内部表单的 "close" 回调
	oDialog = $( "#dialog" ).dialog({
	  title: "菜单信息",
      dialogClass: "no-close",
      width: 580,
      height: 320,
      autoOpen: false,
	  resizable: false,
	  draggable: false,
      modal: true,
      buttons: [
        {text: "保存", class: "btn-approve", click: function() {
        	if ($('#inputForm').valid()) {
            	$.post("menu/save", $("#inputForm").serialize(), function(data){
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
		if(data!=undefined && data.menuId!=""){
			oDialog.load("menu/edit/"+data.menuId).dialog( "open" );
		}
    } );
	
});
