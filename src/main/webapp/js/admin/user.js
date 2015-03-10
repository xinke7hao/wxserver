$(function(){

	var cols = [{ "mData": "userId", "sClass": "dt-center", "bVisible": false },
	             { "mData": "userCode", "sClass": "dt-center" },
	             { "mData": "userName", "sClass": "dt-center", "defaultContent": "" },
	             { "mData": "phone", "sClass": "dt-center", "defaultContent": "" },
	             { "mData": "departName", "sClass": "dt-center", "defaultContent": "" },
	             { "mData": "email", "sClass": "dt-center", "defaultContent": "" },
	             { "mData": "isAdmin", "sClass": "dt-center", "mRender": function(data){ return data=='Y'?'是':'否'} },
	             { "mData": "userStatus", "sClass": "dt-center", "mRender": function(data){ return data=='Y'?'启用':'禁用'} },
	             { "mData": "createTime", "sClass": "dt-center" },
	             { "mData": "updateTime", "sClass": "dt-center" }];
	
	createDefaultDataTable("bossTable", "user/search", cols, false, null);
	
	// 模态对话框的初始化：自定义按钮和一个重置内部表单的 "close" 回调
	oDialog = $( "#dialog" ).dialog({
		title: "用户信息",
		width: 480,
		height: 430,
		autoOpen: false,
		resizable: false,
		draggable: false,
		modal: true,
		buttons: [ 
			{text: "确认", class: "btn-approve", click: function() {
				if ($('#inputForm').valid()) {
					$.post("user/save", $("#inputForm").serialize(), function(data){
						showInfoDialog(data);
						search();
					});
					$(this).dialog("close").empty();
//					$(this).dialog("destroy").remove();
				}
			}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
			
		]
	});
	
	//Create
	$('#aBtn').click( function () {
		oDialog.load("user/create").dialog( "open" );
	} );

	//Edit
	$('#eBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.userId!=""){
			if(data.userId=="1"){
				showError("[admin]用户禁止操作!");
			} else {
				oDialog.load("user/edit/"+data.userId).dialog( "open" );	
			}
		}
	} );

	//Delete (One)
	$('#dBtn').click( function () {
		var datas = oTable.rows('.selected').data();
		if(datas.length == 0){
			showError("请至少选择一条记录!");
		} else {
			var uids = "", hasAdmin = false;
			for(var i=0;i<datas.length;i++){
				if(datas[i].userId=="1"){
					hasAdmin = true;
					showError("[admin]用户禁止操作!");
				}
				if(i!=0) uids += ",";
				uids += datas[i].userId;
			}
			if(!hasAdmin){
				deleteConfirm("user/delete", {ids:uids});
			}
		}
	} );

	// 模态对话框的初始化：自定义按钮和一个重置内部表单的 "close" 回调
	cDialog = $( "#dialog-other" ).dialog({
		title: "设置用户角色",
		dialogClass: "no-close",
		width: 360,
		height: 450,
		autoOpen: false,
		resizable: false,
		draggable: false,
		modal: true,
		buttons: [
			{text: "保存", class: "btn-approve", click: function() {
				var rids = "", chks = [], i = 0;
				$("input[name='rchks']:checked").each(function(){
					chks[i++] = $(this).val();
				});
				rids = chks.join(",");
				$.post('user/roleconfig', {id:$("#roleUserId").val(), roleIds:rids}, function(data){
					showInfoDialog(data=='Y'?"设置成功":"设置失败");
				});
				$(this).dialog("close").empty();
			}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
		]
	});

	//Set Roles
	$('#sBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.userId!=""){
			if(data.userId=="1"){
				showError("[admin]用户禁止操作!");
			} else {
				cDialog.load("user/rolelist?id="+data.userId).dialog( "open" );	
			}
		}
	} );
	
	gDialog = $( "#dialog-group" ).dialog({
		title: "设置数据角色",
		dialogClass: "no-close",
		width: 360,
		height: 450,
		autoOpen: false,
		resizable: false,
		draggable: false,
		modal: true,
		buttons: [
			{text: "保存", class: "btn-approve", click: function() {
				var rids = "", chks = [], i = 0;
				$("input[name='rchks']:checked").each(function(){
					chks[i++] = $(this).val();
				});
				rids = chks.join(",");
				$.post('user/groupconfig', {id:$("#groupUserId").val(), groupIds:rids}, function(data){
					showInfoDialog(data=='Y'?"设置成功":"设置失败");
				});
				$(this).dialog("close").empty();
			}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
		]
	});

	//Set Groups
	$('#gBtn').click( function () {
		var data = getSelectedData(oTable);
		if(data!=undefined && data.userId!=""){
			gDialog.load("user/grouplist?id="+data.userId).dialog( "open" );	
		}
	} );

});
