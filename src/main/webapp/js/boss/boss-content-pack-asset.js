function getCheckedAssetIds(tname){
	var ids = [];
	$("#"+tname).find('input[type=checkbox]:checked').each(function(){
		ids.push($(this).val());
	});
	if(ids.length==0){
		showError("请至少选择一个媒资文件");
		return "";
	}
	return ids.join(",");
}

function exportPackAsset(){
	var noasset = true;
	$("#assets").find("tr").each(function(){
		if($(this).attr("class")=="astr"){
			noasset = false;
		}
	});
	if(noasset){
		showError("该集合没有可导出的片单");
	} else {
		$("#queryForm").submit();
	}
}

$(function(){

	var cols = [{ "mData": "id", "sClass": "dt-center", "mRender": function(data){ return "<input type='checkbox' name='ids' value='"+data+"' />"; }},
	            { "mData": "id", "sClass": "dt-center" },
	        	{ "mData": "name", "sClass": "dt-left" },
	        	{ "mData": "kind", "sClass": "dt-center" },
	        	{ "mData": "min_vip_str", "sClass": "dt-center", "defaultContent": "" },
	        	{ "mData": "operation_mode_str", "sClass": "dt-center" },
	        	{ "mData": "is_vip_product", "sClass": "dt-center", "mRender": function(data){ return data=="true"?"有效":""; } },
	        	{ "mData": "is_collection_product", "sClass": "dt-center", "mRender": function(data){ return data=="true"?"有效":""; } },
	        	{ "mData": "is_single_product", "sClass": "dt-center", "mRender": function(data){ return data=="true"?"有效":""; } },
	        	{ "mData": "start_charges", "sClass": "dt-center", "mRender": function(data){ return data>0?"是":"否"; } },
	        	{ "mData": "status_str", "sClass": "dt-center" },
	        	{ "mData": "updated_at", "sClass": "dt-center", "defaultContent":"" }];

	oTable = $('#bossTable').DataTable({
		'dom': 'rtipl',
		'displayLength': initPageSize,
		"lengthMenu": pageLengthMenu,
		'scrollY': dtHeight,
		'scrollX': true,
		'autoWidth': true,
		'filter': false,
		'searching':false,
		'paginationType': 'full_numbers',
		'bProcessing' : true,
		'serverSide' : true,
		'sEcho': 1,
		'sAjaxSource': 'boss/content/pack/assearch',
		'sServerMethod': 'POST',
		'fnServerParams': function (data){
				$("#queryForm *").filter(':input').each(function(){
					data.push( { "name": $(this).attr("name"), "value": $(this).val().trim() } );
				});
			},
		'ordering': false,
		"aoColumns": cols,
		'language': {"url": "js/page_lang.txt"}
	});

	//添加所选
	$('#bossTable tbody').on( 'click', 'tr', function () {
		$(this).toggleClass('selected');
        var ck = $(this).find("td:first input[type=checkbox]");
        ck.prop("checked", !ck.prop("checked"));
        if(ck.prop("checked")){
        	var flag = false;
        	$("#assets").find('tr').each(function(){
        		var temp = $(this).find('td:first input[type=checkbox]').val();
        		if(temp==ck.val()){
        			flag = true;
        		}
        	});
        	if(!flag) $("#assets").append($(this).clone());	//已经存在就不添加
        } else {
        	$("#assets").find('tr').each(function(){
        		var temp = $(this).find('td:first input[type=checkbox]').val();
        		if(temp==ck.val()){
        			$(this).remove();
        		}
        	});
        }
    } );
	
	//删除所选
	$('#assets').on('click','tr',function(){
		var rid = $(this).find("td:first input[type=checkbox]").val();
		$(this).remove();
		$('#bossTable').find('input[type=checkbox][value='+rid+']').prop("checked", false);
	});
	
	oDialog = $("#dialog").dialog({
	      dialogClass: "no-close",
	      width: 500,
	      height: 300,
	      autoOpen: false,
		  resizable: false,
		  draggable: false,
	      modal: true,
	      buttons: [
	        {text: "保存", class: "btn-approve", click: function() {
	        	if ($('#inputForm').valid()) {
	            	$.post($('#inputForm').attr("action"), $("#inputForm").serialize(), function(data){
	            		if(data=='Y'){
	            			showInfoDialog("操作成功");
		            		if($("#oper").val()=="1"){
		            			$.get("boss/content/pack/aslist", {pid:$("#pid").val()}, function(cdata){
		            				$("#mainDiv").html(cdata);
		            			});
		            		} else search();
	            		} else {
	            			showError(data.error);
	            		}
	            	});
	            	$(this).dialog("close").empty();
	        	}
			}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close").empty(); }}
	      ]
	    });
	
	//打包
	$('#sBtn').click( function () {
		$("#oper").val("1");
		var data = getCheckedAssetIds("assets");
		if(data!=undefined && data!=''){
			$("#dialog").dialog("option", "title", "模式设置");
			oDialog.load("boss/content/pack/setmode?pid="+$("#pid").val()+"&ids="+data).dialog("open");
		}
	});
	
	//按集收费
	$('#pBtn').click( function () {
		$("#oper").val("2");
		var data = getCheckedAssetIds("bossTable");
		if(data!=undefined && data!=''){
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
		$("#oper").val("3");
		var data = getCheckedAssetIds("bossTable");
		if(data!=undefined && data!=''){
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
