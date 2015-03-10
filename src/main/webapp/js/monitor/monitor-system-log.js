$(function(){

	var logstyle = {"L":"LOCAL","H":"HTTP","R":"RESTFul"};
	var logstatus = {"Y":"正常","N":"错误"};
	var logtype = {"D":"删除","U":"编辑","Q":"查询","H":"HTTP调用"};
	
	var cols = [{ "mData": "moduleName", "sClass": "dt-center", "width": "120px" },
	             { "mData": "logTime", "sClass": "dt-center", "width": "150px" },
	             { "mData": "logStyle", "sClass": "dt-center", "width": "100px", "mRender": function(data){ return logstyle[data]; } },
	             { "mData": "logStatus", "sClass": "dt-center", "width": "100px", "mRender": function(data){ return logstatus[data]; } },
	             { "mData": "logType", "sClass": "dt-center", "width": "100px", "mRender": function(data){ return logtype[data]; } },
	             { "mData": "logUser", "sClass": "dt-center", "width": "100px" },
	             { "mData": "logIp", "sClass": "dt-center", "width": "120px" },
	             { "mData": "logDesc", "sClass": "dt-left", "defaultContent": "" }];
	
	var colDef = [ {
	    "targets": 7,
	    "render": function ( data, type, full, meta ) {
	      return type==='display' && data!=null && data.length > 60 ? '<span title="'+data+'">'+data.substr( 0, 58 )+'...</span>' : data;
	    }
	  } ];
	
	createDefaultDataTable("bossTable", "monitor/log/search", cols, false, colDef);
	
	//Date Picker
	$( "#qStartDay" ).datepicker({
		dateFormat: 'yy-m-d',
		defaultDate: firstDayOfMonth,
		changeMonth: true,
		changeYear: true,
		onClose: function( selectedDate ) {
			$("#qEndDay").datepicker( "option", "minDate", selectedDate );
		}
	});
	$("#qEndDay").datepicker({
		dateFormat: 'yy-m-d',
		defaultDate: +1,
		changeMonth: true,
		changeYear: true,
		onClose: function( selectedDate ) {
			$( "#qStartDay" ).datepicker( "option", "maxDate", selectedDate );
		}
	});

	//设置默认时间差为1周
	$( "#qStartDay" ).datepicker("setDate", $("#qStartDay").datepicker("option", "defaultDate"));
	$( "#qEndDay" ).datepicker("setDate", $("#qEndDay").datepicker("option", "defaultDate"));
	
	$("#dBtn").click(function(){
		if($("#qType").val()=="D"){
			showError("[删除]操作日志不允许清空!");
		} else {
			bossConfirmPost("monitor/log/delete", 
					{module:$("#qMenuId").val(),style:$("#qStyle").val(),status:$("#qStatus").val(),type:$("#qType").val(),startDay:$("#qStartDay").val(),endDay:$("#qEndDay").val()}, 
					"选中的项目将被永久删除，您确认执行此操作吗?");	
		}
	});

});
