$(function(){
	
	var cols = [{ "mData": "roleId", "sClass": "dt-center", "bVisible": false },
                { "mData": "roleName", "sClass": "dt-center" },
                { "mData": "roleStatus", "sClass": "dt-center", "mRender": function(data){ return data=='Y'?'启用':'禁用'} },
	            { "mData": "createTime", "sClass": "dt-center", "width": "150px" },
	            { "mData": "updateTime", "sClass": "dt-center", "width": "150px" }];
	
	createDefaultDataTable("bossTable", "role/search", cols, false, null);
	
	// 模态对话框的初始化：自定义按钮和一个重置内部表单的 "close" 回调
	oDialog = $( "#dialog" ).dialog({
	  title: "角色信息",
      dialogClass: "no-close",
      width: 450,
      height: 220,
      autoOpen: false,
	  resizable: false,
	  draggable: false,
      modal: true,
      buttons: [
        {text: "保存", class: "btn-approve", click: function() {
        	if ($('#inputForm').valid()) {
            	$.post("role/save", $("#inputForm").serialize(), function(data){
            		showInfoDialog(data);
            		search();
            	});
            	$(this).dialog("close").empty();
        	}
		}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
      ]
    });
	
  	//Role Rights Settings
	$('#sBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.roleId!=""){
			$.get("role/config/"+data.roleId, function(data){
				$("#mainDiv").html(data);
			});
		}
    } );
	
	//Create
	$('#aBtn').click( function () {
		oDialog.load("role/create").dialog( "open" );
    } );
	
	//Edit
	$('#eBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.roleId!=""){
			oDialog.load("role/edit/"+data.roleId).dialog( "open" );
		}
    } );
	
	//Delete (One)
	$('#dBtn').click( function () {
		var datas = oTable.rows('.selected').data();
		if(datas.length == 0){
			showError("请至少选择一条记录!");
		} else {
			var uids = "";
			for(var i=0;i<datas.length;i++){
				if(i!=0) uids += ",";
				uids += datas[i].roleId;
			}
			deleteConfirm("role/delete", {ids:uids});
		}
    } );
	
});
