function checkSingle(){
	var single = $("#single").val();
	if(single!=1){
		showError("只能导出单点产品片单!");
		return false;
	}
	return true;
}

$(function(){

	var flag = true;
	var cols = [{ "mData": "id", "sClass": "dt-center", "mRender": function(data){ return "<input type='checkbox' name='ids' value='"+data+"' />"; }},
	            { "mData": "id", "sClass": "dt-center" },
	        	{ "mData": "name", "sClass": "dt-left" },
	        	{ "mData": "kind", "sClass": "dt-center" },
	        	{ "mData": "min_vip_str", "sClass": "dt-center", "defaultContent": "" },
	        	{ "mData": "mark_id", "sClass": "dt-center", "defaultContent": "", "bVisible": false },
	        	{ "mData": "operation_mode_str", "sClass": "dt-center" },
	        	{ "mData": "is_vip_product", "sClass": "dt-center", "mRender": function(data){ return data=="true"?"有效":""; } },
	        	{ "mData": "is_collection_product", "sClass": "dt-center", "mRender": function(data){ return data=="true"?"有效":""; } },
	        	{ "mData": "is_single_product", "sClass": "dt-center", "mRender": function(data){ return data=="true"?"有效":""; } },
	        	{ "mData": "start_charges", "sClass": "dt-center", "mRender": function(data){ return data>0?"是":"否"; } },
	        	{ "mData": "status_str", "sClass": "dt-center" },
	        	{ "mData": "updated_at", "sClass": "dt-center", "defaultContent":"" }];

	$("#qBtn").click(function(){
		if(flag){
			$("#bossTable").show();
			createCheckDataTable("bossTable", "boss/content/single/search", cols, false, null);
			flag = false;
		} else search();
	});
	
	$("#checkAll").click(function(){
		$("input[type='checkbox'][name='ids']").prop("checked", $(this).prop("checked"));
	});
	
	oDialog = $("#dialog").dialog({
	      dialogClass: "no-close",
	      width: 540,
	      height: 340,
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
	
	//单点收费
	$('#sBtn').click( function () {
		var data = getCheckedData("ids");
		if(data!=undefined){
			$("#dialog").dialog("option", "title", "生成单点");
			oDialog.load("boss/content/single/pay?ids="+data).dialog("open");
		}
	});
	
	//按集收费
	$('#pBtn').click( function () {
		var data = getCheckedData("ids");
		if(data!=undefined){
			$("#dialog").dialog("option", "title", "按集收费设置");
			var url = "boss/content/single/episode?ids="+data;

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
	
	//取消收费
	$('#cBtn').click( function () {
		var data = getCheckedData("ids");
		if(data!=undefined){
			bossConfirmPost("boss/content/single/cancel", {ids: data}, "您确认取消所选产品的收费设置吗?");
		}
	});
	
});
