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

$("#qEndDay").datepicker("setDate", $("#qEndDay").datepicker("option", "defaultDate"));
$( "#qStartDay" ).datepicker("setDate", $("#qStartDay").datepicker("option", "defaultDate"));

//Chart Setting Start
var paycountChart;
var paycountOptions;
var register=false;

require.config({ paths : { echarts : 'js/echarts' }});

// 使用(模块化按需加载)
require([ 'echarts', 'echarts/chart/line', 'echarts/chart/bar' ],
		function(ec) {
	paycountChart = ec.init(document.getElementById('paycountChart'));
	reloadChart();	//页面初始化
});

function reloadChart() {
	if(!judgeDateDuration()) return false;
	$.post('chart/paycount/search', $("#chartForm").serialize(), function(chartData) {
		if (chartData) {
			if (chartData.series && chartData.series != "") {
				showStackChartWithTotal(paycountChart, paycountOptions, 'VIP购买总人数: ', chartData.total, chartData.legend, chartData.category, chartData.series, chartData.totals, register);
				register = true;
			} 
		} else {
			showError("没有相关的统计数据!");
		}
	});
}
