$(function(){
	
	var cols = [{ "mData": "id", "sClass": "dt-center", "mRender": function(data){ return "<input type='checkbox' name='ids' value='"+data+"' />"; }},
				{ "mData": "id", "sClass": "dt-center" },
	        	{ "mData": "channel_name", "sClass": "dt-center" },
	        	{ "mData": "quality_name", "sClass": "dt-center" },
	        	{ "mData": "min_vip_id", "sClass": "dt-center", "bVisible": false },
	        	{ "mData": "min_vip_str", "sClass": "dt-center" },
	        	{ "mData": "channel_flag", "sClass": "dt-center", "mRender": function(data){ return data=="1"?"轮播":"直播"; } },
	        	{ "mData": "status_str", "sClass": "dt-center" },
	        	{ "mData": "updated_at", "sClass": "dt-center" }];

	createCheckDataTable("bossTable", "boss/content/source/search", cols, false, null);

	$("#checkAll").click(function(){
		$("input[type='checkbox'][name='ids']").prop("checked", $(this).prop("checked"));
	});
	
	oDialog = $( "#dialog" ).dialog({
		  title: "VIP收费",
	      dialogClass: "no-close",
	      width: 400,
	      height: 200,
	      autoOpen: false,
		  resizable: false,
		  draggable: false,
	      modal: true,
	      buttons: [
	        {text: "保存", class: "btn-approve", click: function() {
	        	if ($('#inputForm').valid()) {
	            	$.post("boss/content/source/save", $("#inputForm").serialize(), function(data){
	            		showInfoDialog(data);
	            		search();
	            	});
	            	$(this).dialog("close").empty();
	        	}
			}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
	      ]
	    });
	
	//VIP Settings
	$('#pBtn').click( function () {
		var data = getCheckedData("ids");
		if(data!=undefined){
			
			var url = "boss/content/source/edit?ids="+data;

			//Single
			if(data!="" && data.indexOf(',')==-1){
				var obj = oTable.rows('.selected').data();
				url += "&minvip=" + obj[0].min_vip_id;
			} else {
				url += "&minvip=";
			}
			
			oDialog.load(url).dialog("open");
		}
    } );
});
