//Chart Setting Start
var payuserChart;
var payuserOptions;

require.config({ paths : { echarts : 'js/echarts', 'echarts/theme/infographic' : 'js/echarts/theme/infographic' }});

// 使用(模块化按需加载)
require([ 'echarts', 'echarts/theme/infographic', 'echarts/chart/map' ], function(ec, theme) {
	payuserChart = ec.init(document.getElementById('payuserChart'), theme);
	reloadChart();
});

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
	defaultDate: +1,
	dateFormat: 'yy-m-d',
	changeMonth: true,
	changeYear: true,
	onClose: function( selectedDate ) {
		$( "#qStartDay" ).datepicker( "option", "maxDate", selectedDate );
	}
});

$( "#qStartDay" ).datepicker("setDate", $("#qStartDay").datepicker("option", "defaultDate"));
$( "#qEndDay" ).datepicker("setDate", $("#qEndDay").datepicker("option", "defaultDate"));

function initMapOptions(mainTitle) {
	var initOptions = {
			theme : 'infographic',
			title : { text: mainTitle, x:'center' },
            tooltip : { trigger: 'item' },
            legend: { orient: 'vertical', x:'left', data:[] },
            dataRange: { min: 0, x: 'left', y: 'bottom', text:['高','低'], calculable : true },
            toolbox: {
                show: true, orient : 'vertical', x: 'right', y: 'center',
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            series : []
	};

	return initOptions;
}

function reloadChart() {
	$.post('chart/payuser/search', $("#chartForm").serialize(), function(result) {
		if (result) {
			payuserChart.clear();
			payuserChart.showLoading({ image : "../images/loading1.gif" });
			payuserOptions = initMapOptions('');

			payuserOptions.legend.data = result.legend;
			payuserOptions.dataRange.max = result.max;
			
			var len = result.legend.length;
			for(var i=0;i<len;i++){
				payuserOptions.series.push({
                    name: result.legend[i],
                    type: 'map', mapType: 'china', roam: false, itemStyle:{ normal:{label:{show:true}}, emphasis:{label:{show:true}} },
                    data:result.series[result.legend[i]]
                });
			}

			payuserChart.hideLoading();
			payuserChart.setOption(payuserOptions);
		}
	});
}
