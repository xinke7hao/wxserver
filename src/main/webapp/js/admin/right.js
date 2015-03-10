$(function(){

	var cols = [{ "mData": "moduleId", "sClass": "dt-center", "bVisible": false},
	             { "mData": "menuId", "sClass": "dt-center", "bVisible": false },
	             { "mData": "rightId", "sClass": "dt-center", "bVisible": false },
	             { "mData": "moduleName", "sClass": "dt-center" },
	             { "mData": "menuName", "sClass": "dt-left" },
	             { "mData": "menuCode", "sClass": "dt-left" },
	             { "mData": "menuUrl", "sClass": "dt-left" },
	             { "mData": "rightName", "sClass": "dt-left" },
	             { "mData": "rightMethod", "sClass": "dt-left" },
	             { "mData": "rightStatus", "sClass": "dt-center", "mRender": function(data){ return data=='Y'?'启用':'禁用'} },
	             { "mData": "createTime", "sClass": "dt-center", "width": "150px" },
	             { "mData": "updateTime", "sClass": "dt-center", "width": "150px" }];
	
	createDefaultDataTable("bossTable", "right/search", cols, false, null);
	
	// 模态对话框的初始化：自定义按钮和一个重置内部表单的 "close" 回调
	oDialog = $( "#dialog" ).dialog({
		title: "功能信息",
		dialogClass: "no-close",
		width: 430,
		height: 320,
		autoOpen: false,
		resizable: false,
		draggable: false,
		modal: true,
		buttons: [
			{text: "保存", class: "btn-approve", click: function() {
	        	if ($('#inputForm').valid()) {
	            	$.post("right/save", $("#inputForm").serialize(),	function(data){
	            		showInfoDialog(data);
	            		search();
	            	});
	            	$(this).dialog("close").empty();
	        	}
			}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
		]
	});

	//Create
	$('#aBtn').click( function () {
		oDialog.load("right/create").dialog( "open" );
	} );

	//Edit
	$('#eBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.rightId!=""){
			oDialog.load("right/edit/"+data.rightId).dialog( "open" );
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
				uids += datas[i].rightId;
			}
			deleteConfirm("right/delete", {ids:uids});
		}
	} );

	// Module --> Menu
	$("#qModuleId").change(function(){
		var mid = $(this).val();
		if(mid!=null){
			$.get("right/loadchild", {mid:mid}, function(data){
				$("#qMenuId").empty();
				//$("#qMenuId").find("option:selected")
				$("#qMenuId").append("<option value=''>----</option>");
				for(var i=0;i<data.length;i++){
					$("#qMenuId").append("<option value='"+data[i].menuId+"'>"+data[i].menuName+"</option>");
				}
			});
		}
	});

});

function loadChilds(){
	var mid = $("#cModuleId").val();
	if(mid!=null){
		$.get("right/loadchild", {mid:mid}, function(data){
			$("#cMenuId").empty();
			for(var i=0;i<data.length;i++){
				$("#cMenuId").append("<option value='"+data[i].menuId+"'>"+data[i].menuName+"</option>");
			}
		});
	}
}