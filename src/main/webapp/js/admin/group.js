$(function(){
	
	var cols = [{ "mData": "groupId", "sClass": "dt-center", "bVisible": false },
                 { "mData": "groupName", "sClass": "dt-center" },
                 { "mData": "groupStatus", "sClass": "dt-center", "mRender": function(data){ return data=='Y'?'启用':'禁用'} },
	             { "mData": "createTime", "sClass": "dt-center", "width": "150px" },
	             { "mData": "updateTime", "sClass": "dt-center", "width": "150px" }
               ];
	
	createDefaultDataTable("bossTable", "group/search", cols, false, null);
	
	// 模态对话框的初始化：自定义按钮和一个重置内部表单的 "close" 回调
	oDialog = $( "#dialog" ).dialog({
	  title: "数据角色信息",
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
            	$.post("group/save", $("#inputForm").serialize(), function(data){
            		showInfoDialog(data);
            		search();
            	});
            	$(this).dialog("close").empty();
        	}
		}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
      ]
    });
	
  	//Group Rights Settings
	$('#sBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.groupId!=""){
			$.get("group/config/"+data.groupId, function(data){
				$("#mainDiv").html(data);
			});
		}
    } );
	
	//Create
	$('#aBtn').click( function () {
		oDialog.load("group/create").dialog( "open" );
    } );
	
	//Edit
	$('#eBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.groupId!=""){
			oDialog.load("group/edit/"+data.groupId).dialog( "open" );
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
				uids += datas[i].groupId;
			}
			deleteConfirm("group/delete", {ids:uids});
		}
    } );

  	//Group Data Settings
	$('#sBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.groupId!=""){
			$.get("group/config/"+data.groupId, function(data){
				$("#mainDiv").html(data);
			});
		}
    } );
	
});
