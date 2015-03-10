function importPack(id){
	$('#pid').val(id);
	cDialog.load("boss/content/pack/import?pid="+id).dialog( "open" );
}

function editPackAssets(id){
	$.get("boss/content/pack/aslist", {pid:id}, function(data){
		$("#mainDiv").html(data);
	});
}

$(function(){
	
	var cols = [{ "mData": "id", "sClass": "dt-center", "mRender": function(data){ return "<input type='checkbox' name='ids' value='"+data+"' />"; }},
	            { "mData": "id", "sClass": "dt-center" },
	        	{ "mData": "name", "sClass": "dt-left" },
	        	{ "mData": "updated_at", "sClass": "dt-center" },
	        	{ "mData": "yuan_price", "sClass": "dt-center", "mRender": function(data){ return data+"元"; } },
	        	{ "mData": "days", "sClass": "dt-center", "mRender": function(data){ return data+"天"; } },
	        	{ "mData": "id", "sClass": "dt-center", 
	        		"mRender": function(data){
	        			return "<a href='javascript:importPack("+data+");'>导入集合产品</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='javascript:editPackAssets("+data+");'>编辑集合媒资</a>"; 
	        		}
	        	}];

	createCheckDataTable("bossTable", "boss/content/pack/search", cols, false, null);

	$("#checkAll").click(function(){
		$("input[type='checkbox'][name='ids']").prop("checked", $(this).prop("checked"));
	});
	
	oDialog = $( "#dialog" ).dialog({
		  title: "集合收费内容",
	      dialogClass: "no-close",
	      width: 420,
	      height: 300,
	      autoOpen: false,
		  resizable: false,
		  draggable: false,
	      modal: true,
	      buttons: [
	        {text: "保存", class: "btn-approve", click: function() {
	        	if ($('#inputForm').valid()) {
	            	$.post("boss/content/pack/save", $("#inputForm").serialize(), function(data){
	            		showInfoDialog(data);
	            		search();
	            	});
	            	$(this).dialog("close").empty();
	        	}
			}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
	      ]
	    });
	
	//导入集合产品子窗体
	cDialog = $( "#dialog-other" ).dialog({
		title: "导入集合产品",
		dialogClass: "no-close",
		width: 550,
		height: 450,
		autoOpen: false,
		resizable: false,
		draggable: false,
		modal: true,
		buttons: [
			{text: "确定", class: "btn-approve", click: function() {
				var cids = [];
				$("input[type='checkbox'][name='cids']:checked").each(function(){
					cids.push($(this).val());
				});
				$.post('boss/content/pack/doimport', {pid: $('#pid').val(), ids:cids.join(",")}, function(data){
					showInfoDialog(data);
				});
				$(this).dialog("close").empty();
			}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
		]
	});
		
	  	//Create
		$('#cBtn').click( function () {
			oDialog.load("boss/content/pack/create").dialog( "open" );
	    } );
		
		//Edit
		$('#eBtn').click( function () {
			var data = getSelectedData(oTable);
			if(data!=undefined && data.id!=""){
				oDialog.load("boss/content/pack/edit/"+data.id).dialog( "open" );
			}
	    } );
		
		//Delete
		$('#dBtn').click( function () {
			var data = getCheckedData("ids");
			if(data!=undefined){
				deleteConfirm("boss/content/pack/delete", {ids: data});
			}
	    } );
});
