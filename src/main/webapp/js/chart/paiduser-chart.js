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

$( "#qStartDay" ).datepicker("setDate", $("#qStartDay").datepicker("option", "defaultDate"));
$("#qEndDay").datepicker("setDate", $("#qEndDay").datepicker("option", "defaultDate"));

//Chart Setting Start
var paiduserChart, paidcountChart;
var paiduserOptions, paidcountOptions;
var register1=false,register2=false;

require.config({ paths : { echarts : 'js/echarts' }});

// 使用(模块化按需加载)
require([ 'echarts', 'echarts/chart/line', 'echarts/chart/bar' ],
		function(ec) {
	paiduserChart = ec.init(document.getElementById('paiduserChart'));
	paidcountChart = ec.init(document.getElementById('paidcountChart'));
	reloadChart();	//页面初始化
});

function reloadChart() {
	if(!judgeDateDuration()) return false;
	$.post('chart/paiduser/search', $("#chartForm").serialize(), function(chartData) {
		if (chartData.series!=null && chartData.moneySeries!=null) {
			showStackChartWithTotal(paiduserChart, paiduserOptions, '历史累计付费用户总数: ', chartData.total, chartData.legend, chartData.category, chartData.series, chartData.totals, register1);
			register1=true;
			showStackChartWithTotal(paidcountChart, paidcountOptions, '当前有效付费用户总数: ', chartData.totalMoney, chartData.legend, chartData.category, chartData.moneySeries, chartData.totalMoneys, register2);
			register2=true;
		} else {
			showError("没有相关的统计数据!");
		}
	});
}
