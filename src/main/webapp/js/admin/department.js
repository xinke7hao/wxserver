//debugger;
$(function(){
	
	var cols = [{ "mData": "departId", "sClass": "dt-center", "bVisible": false },
	            { "mData": "departCode", "sClass": "dt-center" },
	            { "mData": "departName", "sClass": "dt-center", "defaultContent": "" }, 
	            { "mData": "createTime", "sClass": "dt-center" },
	            { "mData": "updateTime", "sClass": "dt-center" }];
	
	createDefaultDataTable("bossTable", "depart/search", cols, false, null);
	
	// 模态对话框的初始化：自定义按钮和一个重置内部表单的 "close" 回调
	oDialog = $( "#dialog" ).dialog({
		title: "部门信息",
		width: 450,
		height: 240,
		autoOpen: false,
		resizable: false,
		draggable: false,
		modal: true,
		buttons: [ 
			{text: "确认", class: "btn-approve", click: function() {
				if ($('#inputForm').valid()) {
					$.post("depart/save", $("#inputForm").serialize(), function(data){
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
		oDialog.load("depart/create").dialog( "open" );
	} );

	//Edit
	$('#eBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.departId!=""){
			oDialog.load("depart/edit/"+data.departId).dialog( "open" );	
		}
	} );

	//Delete (One)
	$('#dBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.departId!=""){
			deleteConfirm("depart/delete/"+data.departId, null);
		}
	} );
	
});
