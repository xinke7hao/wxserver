var oTable;		//DataTables
var oDialog;	//JQuery Dialog
var cDialog;	//JQuery Second Dialog
var gDialog;	//User Group Dialog
var today = new Date();
var firstDayOfMonth = new Date(today.getFullYear()+"-"+(today.getMonth()+1)+"-01");
var initPageSize = 15;
var exportCsvMax = 1000;
var pageLengthMenu = [15,30,50];
var dtHeight = $(window).height() * 0.65;
var timeOutDuration = 60 * 1000 * 60; // 60 minutes
var lastOperationTime = new Date();

//减小验证压力,仅设置AJAX手工提交时验证
$.validator.setDefaults({
	onsubmit: false,
	onkeyup: false,
	onclick: false
});

$.validator.addMethod("alpha", function(value, element) {
	return this.optional(element) || value == value.match(/^[a-zA-Z]+$/);
},"只能输入英文字母");

$.validator.addMethod("alphanumeric", function(value, element) {
	return this.optional(element) || value == value.match(/^[a-z0-9A-Z#]+$/);
},"只能输入字母和数字");

//Session Checker
window.setInterval(
	function(){
		if(new Date().getTime() > lastOperationTime.getTime() + timeOutDuration){
			location.href = "logout";
		}
	}, 5000);

//AJAX start handler -- showing loading
$(document).ajaxStart(function() {
	lastOperationTime = new Date();
	$("#sysLoadImage").show();
});

//AJAX Stop handler 
$(document).ajaxStop(function(){
	$("#sysLoadImage").hide();
});

//AJAX Complete handler 
$(document).ajaxComplete(function(){});

//AJAX Error handler 
$(document).ajaxError(function(event,xhr,options,exc){
	$("#sysLoadImage").hide();
	var msg = "系统错误， 请联系管理员。";
	if(xhr.status==401){
		msg = "对不起，您没有权限访问指定资源。";
	}
	showError(msg);
});

//format DateTime long
function formatDatetime(time){
	var d = new Date(time);
	return d.getFullYear()+"-"+fillZero(d.getMonth()+1)+"-"+fillZero(d.getDate())+" "+fillZero(d.getHours())+":"+fillZero(d.getMinutes())+":"+fillZero(d.getSeconds());
}

function fillZero(value){
	return value<10?("0"+value):value;
}

//info dialog
function showInfoDialog(info){
	if(info=='Y'){
		$('#dialog-info').html('操作成功').fadeIn("fast").fadeOut(2000);
	} else {
		showError(info.error);
	}
}

//error dialog
function showError(err){
	$('#dialog-error').html(err).fadeIn("fast").fadeOut(4000);
}

//get selected data (single select)
function getSelectedData(table){
	var datas = table.rows('.selected').data();
	if(datas.length == 0) showError("请至少选择一条记录!");
	else if(datas.length > 1) showError("一次只能选择一条记录!");
	else return datas[0];
}

//get checked data
function getCheckedData(cksName){
	var datas = [];
	$("input[type='checkbox'][name='"+cksName+"']:checked").each(function(){
		datas.push($(this).val());
	});
	if(datas.length == 0){
		showError("请至少选择一条记录!");
	} else {
		return datas.join(",");
	}
}

/**
 * 统一构造DataTable
 * @param tableId
 * @param url
 * @param cols
 * @param ordering
 * @param colDefs
 */
function createDefaultDataTable(tableId, url, cols, ordering, colDefs){
	
	oTable = $('#'+tableId).DataTable({
		'dom': 'rtipl',
		'displayLength': initPageSize,	//分页数量
		"lengthMenu": pageLengthMenu,	//分页下拉选项
		'scrollY': dtHeight,			//Y轴高度
		'scrollX': true,
		'autoWidth': true,
		'filter': false,				//去除自带的Filter
		'searching':false,				//去除自带的Search
		'paginationType': 'full_numbers',	//分页样式
		'bProcessing' : true,
		'serverSide' : true,
		'sEcho': 1,
		'sAjaxSource': url,				//AJAX请求地址
		'sServerMethod': 'POST', 		//请求方式
		'fnServerParams': function (data){	//自定义参数
				$("#queryForm *").filter(':input').each(function(){
					if($(this).attr("type")=="radio"){
						if($(this).prop("checked")){
							data.push( { "name": $(this).attr("name"), "value": $(this).val() } );
						}
					} else {
						data.push( { "name": $(this).attr("name"), "value": $(this).val().trim() } );
					}
				});
			},
		'ordering': ordering,			//是否排序
//		'order': [[sortIndex, "desc"]],	//默认排序列以及排序方式
		"aoColumns": cols,				//数据定义
		"columnDefs": colDefs,
		'language': {"url": "js/page_lang.txt"}
	});

	//Invalid Json Repsonse
	oTable.on('xhr.dt', function (e,settings,json) {
	    if(json.error!=null){
	    	showError(json.error);	//异常信息
	    	$("#sysLoadImage").hide();	//隐藏加载中图片
	    	$("#"+tableId+"_processing").hide(); //隐藏进度条
	    }
	});
	
	//Make TR Selectable (Single Row)
	$('#'+tableId+' tbody').on( 'click', 'tr', function () {
		if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            oTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );
	
	//Make TR Selectable(Multiple)
//	$('#bossTable tbody').on( 'click', 'tr', function () {
//		$(this).toggleClass('selected');
//	} );
}

function createCheckDataTable(tableId, url, cols, ordering, colDefs){
	oTable = $('#'+tableId).DataTable({
		'dom': 'rtipl',
		'displayLength': initPageSize,	//分页数量
		"lengthMenu": pageLengthMenu,	//分页下拉选项
		'scrollY': dtHeight,			//Y轴高度
		'scrollX': true,
		'autoWidth': true,
		'filter': false,				//去除自带的Filter
		'searching':false,				//去除自带的Search
		'paginationType': 'full_numbers',	//分页样式
		'bProcessing' : true,
		'serverSide' : true,
		'sEcho': 1,
		'sAjaxSource': url,				//AJAX请求地址
		'sServerMethod': 'POST', 		//请求方式
		'fnServerParams': function (data){
				$("#queryForm *").filter(':input').each(function(){
					if($(this).attr("type")=="radio"){
						if($(this).prop("checked")){
							data.push( { "name": $(this).attr("name"), "value": $(this).val() } );
						}
					} else {
						data.push( { "name": $(this).attr("name"), "value": $(this).val().trim() } );
					}
				});
			},		//自定义参数
		'ordering': ordering,			//是否排序
//		'order': [[sortIndex, "desc"]],	//默认排序列以及排序方式
		"aoColumns": cols,				//数据定义
		"columnDefs": colDefs,
		'language': {"url": "js/page_lang.txt"}
	});

	//Invalid Json Repsonse
	oTable.on('xhr.dt', function (e,settings,json) {
	    if(json.error!=null){
	    	showError(json.error);	//异常信息
	    	$("#sysLoadImage").hide();	//隐藏加载中图片
	    	$("#"+tableId+"_processing").hide(); //隐藏进度条
	    }
	});

	//Make TR Selectable
	$('#'+tableId+' tbody').on( 'click', 'tr', function () {
		$(this).toggleClass('selected');
        var ck = $(this).find("td:first input[type=checkbox]");
        ck.prop("checked", !ck.prop("checked"));
    } );
	
}

//click search
function search(){
	$("input[type='text']").each(function(){
		$(this).val($.trim($(this).val()));
	});
	oTable.draw();
}

//统一构造带回调的DataTable
function createDataTableWithCallback(tableId, url, cols, ordering, drawCallback){
	oTable = $('#'+tableId).DataTable({
		'dom': 'rtipl',
		'displayLength': initPageSize,	//分页数量
		"lengthMenu": pageLengthMenu,	//分页下拉选项
		'scrollY': dtHeight,			//Y轴高度
		'scrollX': true,
		'autoWidth': true,
		'filter': false,				//去除自带的Filter
		'searching':false,				//去除自带的Search
		'paginationType': 'full_numbers',	//分页样式
		'bProcessing' : true,
		'serverSide' : true,
		'sEcho': 1,
		'sAjaxSource': url,				//AJAX请求地址
		'sServerMethod': 'POST', 		//请求方式
		'fnServerParams': function (data){
			$("#queryForm *").filter(':input').each(function(){
				data.push( { "name": $(this).attr("name"), "value": $(this).val().trim() } );
			});
		},		//自定义参数
		'ordering': ordering,			//是否排序
		"aoColumns": cols,				//数据定义
		'language': {"url": "js/page_lang.txt"},
		'drawCallback': drawCallback
	});
}

//form reset
function resetUrl(url){
	$.get(url+"/list", function(data) {
		$("#mainDiv").html(data);
	});
}

//delete confirm
function deleteConfirm(url, data){
	bossConfirm(url, data, "选中的项目将被永久删除，您确认执行此操作吗?");
}

//show confirm dialog
function bossConfirm(url, data, info){
	$("#dialog-confirm").html('<p style="margin-top: 15px;"><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span><font color="red"><b>'+info+'</b></font></p>');
	$("#dialog-confirm").dialog({
		resizable: false,
		height:180,
		width:350,
		modal: true,
	    dialogClass: 'no-close',
		buttons: [ 
		{text: "确认", class: "btn-alarm", click: function() {
			$.get(url, data, function(data){
				showInfoDialog(data);
				search();
			});
			$(this).dialog("close");
		}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close"); }}
	]
	});
}

function bossConfirmPost(url, data, info){
	$("#dialog-confirm").html('<p style="margin-top: 15px;"><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span><font color="red"><b>'+info+'</b></font></p>');
	$("#dialog-confirm").dialog({
		resizable: false,
		height:180,
		width:350,
		modal: true,
	    dialogClass: 'no-close',
		buttons: [ 
		{text: "确认", class: "btn-alarm", click: function() {
			$.post(url, data, function(data){
				showInfoDialog(data);
				search();
			});
			$(this).dialog("close");
		}}, {text: "取消", class: "btn-cancel", click: function() { $(this).dialog("close"); }}
	]
	});
}

//合并值相同的行
function tableRowspan(tbodyName, col){
	var pre = null, key = null, rowspan = 0;
	var t = document.getElementById(tbodyName);
	for(var i=0;i<t.rows.length;i++) {
		var val = t.rows[i].cells[col].innerHTML;

		if (val != key) {
			key = val;
			pre = t.rows[i].cells[col];
			rowspan = 0;
		}

		if (val == key) rowspan++;

		if (pre !== null && val == key && rowspan > 1) {
			t.rows[i].deleteCell(col);
			pre.rowSpan = rowspan;
		}
	}
}

function doExportCsv(){
	if(oTable){
		var total = oTable.page.info().recordsTotal;
		if(total > exportCsvMax){
			showError("导出记录数("+total+")超过限制条数("+exportCsvMax+")");
		} else {
			$("#queryForm").submit();
		}
	}
}