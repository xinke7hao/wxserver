$("#qStartDay").datepicker({
	maxDate : 0,
	defaultDate: firstDayOfMonth,
	dateFormat : 'yy-m-d',
	changeMonth : true,
	changeYear : true,
	onClose : function(selectedDate) {
		$("#qEndDay").datepicker("option", "minDate", selectedDate);
	}
});

$("#qEndDay").datepicker({
	defaultDate: +1,
	maxDate: +1,
	changeMonth : true,
	changeYear : true,
	dateFormat : 'yy-m-d',
	onClose : function(selectedDate) {
		$("#qStartDay").datepicker("option", "maxDate", selectedDate);
	}
});

$("#qStartDay").datepicker("setDate", $("#qStartDay").datepicker("option", "defaultDate"));
$("#qEndDay").datepicker("setDate", $("#qEndDay").datepicker("option", "defaultDate"));

$("#chkAllProduct").click(function(){
	$("input[type='checkbox'][name='productId']").prop("checked", $(this).prop("checked"));
});
$("#chkAllChannel").click(function(){
	$("input[type='checkbox'][name='channelId']").prop("checked", $(this).prop("checked"));
});

//Chart Setting Start
var paytimeChart, paymoneyChart;
var paytimeOptions, paymoneyOptions;
var register1=false, register2=false;

require.config({ paths : { echarts : 'js/echarts' }});

// 使用(模块化按需加载)
require([ 'echarts', 'echarts/chart/line', 'echarts/chart/bar' ],
		function(ec) {
	
	paytimeChart = ec.init(document.getElementById('paytimeChart'));
	paymoneyChart = ec.init(document.getElementById('paymoneyChart'));

	$("input[type='checkbox'][name='productId']").prop("checked", true);
	reloadChart();	//页面初始化
});

function reloadChart() {

	//generate product names
	var pnames = [];
	$("input[name='productId']:checked").each(function() {
		pnames.push($(this).attr('title'));
	});

	//产品条件必选
	if(pnames.length==0){
		showError("请勾选产品");
		return false;
	}

	if(!judgeDateDuration()) return false;
	$("#productName").val(pnames.join(','));

	$.post('chart/payvip/search', $("#chartForm").serialize(), function(chartData) {
		if (chartData) {
			if (chartData.series && chartData.series != "") {
				showStackChartWithTotal(paytimeChart, paytimeOptions, 'VIP总购买次数: ', chartData.total, chartData.legend, 
						chartData.category, chartData.series, chartData.totals, register1);
				register1=true;
				showStackChartWithTotal(paymoneyChart, paymoneyOptions, 'VIP总购买金额: ', chartData.totalMoney, chartData.legend, 
						chartData.category, chartData.moneySeries, chartData.totalMoneys, register2);
				register2=true;
			} 
		} else {
			showError("没有相关的统计数据!");
		}
	});
}
