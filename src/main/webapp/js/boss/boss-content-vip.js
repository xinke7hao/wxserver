function viewAsset(id){
	$.get("boss/content/vip/showasset?id="+id, function(data){
		$("#mainDiv").html(data);
	});
}

function syncAsset(id){
	$.get("boss/content/vip/syncasset?id="+id, function(data){
		showInfoDialog(data);
		search();
	});
}

$(function(){
	
	var flag = true;
	var cols = [{ "mData": "id", "sClass": "dt-center", "mRender": function(data){ return "<input type='checkbox' name='ids' value='"+data+"' />"; }},
	            { "mData": "id", "sClass": "dt-center" },
	        	{ "mData": "name", "sClass": "dt-left" },
	        	{ "mData": "kind", "sClass": "dt-center" },
	        	{ "mData": "min_vip_str", "sClass": "dt-center", "defaultContent": "" },
	        	{ "mData": "operation_mode", "sClass": "dt-center", "defaultContent": "", "bVisible": false },
	        	{ "mData": "mark_id", "sClass": "dt-center", "defaultContent": "", "bVisible": false },
	        	{ "mData": "operation_mode_str", "sClass": "dt-center" },
	        	{ "mData": "is_vip_product", "sClass": "dt-center", "mRender": function(data){ return data=="true"?"有效":""; } },
	        	{ "mData": "is_collection_product", "sClass": "dt-center", "mRender": function(data){ return data=="true"?"有效":""; } },
	        	{ "mData": "is_single_product", "sClass": "dt-center", "mRender": function(data){ return data=="true"?"有效":""; } },
	        	{ "mData": "start_charges", "sClass": "dt-center", "mRender": function(data){ return data>0?"是":"否"; } },
	        	{ "mData": "status_str", "sClass": "dt-center" },
	        	{ "mData": "updated_at", "sClass": "dt-center", "defaultContent": ""},
	        	{ "mData": "id", "sClass": "dt-center", 
	        		"mRender": function(data){
	        			return "<a href='javascript:viewAsset("+data+");'>查看媒资文件</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='javascript:syncAsset("+data+");'>手工同步</a>"; 
	        		}
	        	}];

	$("#qBtn").click(function(){
		if(flag){
			$("#bossTable").show();
			createCheckDataTable("bossTable", "boss/content/vip/search", cols, false, null);
			flag = false;
		} else search();
	});
	
	$("#checkAll").click(function(){
		$("input[type='checkbox'][name='ids']").prop("checked", $(this).prop("checked"));
	});
	
	oDialog = $("#dialog").dialog({
	      dialogClass: "no-close",
	      width: 420,
	      height: 270,
	      autoOpen: false,
		  resizable: false,
		  draggable: false,
	      modal: true,
	      buttons: [
	        {text: "保存", class: "btn-approve", click: function() {
	        	if ($('#inputForm').valid()) {
	            	$.post($('#inputForm').attr("action"), $("#inputForm").serialize(), function(data){
	            		showInfoDialog(data);
	            		search();
	            	});
	            	$(this).dialog("close").empty();
	        	}
			}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
	      ]
	    });
	
	//收费设置
	$('#sBtn').click( function () {
		var data = getCheckedData("ids");
		if(data!=undefined){
			$("#dialog").dialog("option", "title", "收费设置");

			var url = "boss/content/vip/pay?ids="+data;

			//Single
			if(data!="" && data.indexOf(',')==-1){
				var obj = oTable.rows('.selected').data();
				console.log(obj[0]);
				url += "&minvip=" + (obj[0].min_vip_str=="免费" ? "-1" : "0");
				url += "&markid=" + obj[0].mark_id;
				url += "&prev=" + obj[0].preview;
			} else {
				url += "&minvip=&markid=&prev=";
			}
			
			oDialog.load(url).dialog("open");
		}
	});
	
	//按集收费
	$('#pBtn').click( function () {
		var data = getCheckedData("ids");
		if(data!=undefined){
			$("#dialog").dialog("option", "title", "按集收费设置");
			var url = "boss/content/vip/episode?ids="+data;

			//Single
			if(data!="" && data.indexOf(',')==-1){
				var obj = oTable.rows('.selected').data();
				url += "&start=" + obj[0].start_charges;
				url += "&markid=" + obj[0].mark_id;
			} else {
				url += "&start=&markid=";
			}
			
			oDialog.load(url).dialog("open");
		}
	});
	
	//运营方式
	$('#oBtn').click( function () {
		var data = getCheckedData("ids");
		if(data!=undefined){
			$("#dialog").dialog("option", "title", "运营方式设置");
			var url = "boss/content/vip/operation?ids="+data;
			
			//Single
			if(data!="" && data.indexOf(',')==-1){
				var obj = oTable.rows('.selected').data();
				url += "&ops=" + obj[0].operation_mode;
			} else {
				url += "&ops=";
			}
			
			oDialog.load(url).dialog("open");
		}
	});
});
