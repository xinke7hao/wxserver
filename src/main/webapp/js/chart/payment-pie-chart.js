//Date Picker
	$("#qDay").datepicker({
		maxDate : 0,
		dateFormat : 'yy-m-d',
		changeMonth : true,
		changeYear : true
	});

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
		defaultDate: -1,
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-m-d',
		onClose : function(selectedDate) {
			$("#qStartDay").datepicker("option", "maxDate", selectedDate);
		}
	});

	$(".queryDate").datepicker({"maxDate": 0, dateFormat: 'yy-m-d'});
	
	$("#qEndDay").datepicker("setDate", $("#qEndDay").datepicker("option", "defaultDate"));
	$( "#qStartDay" ).datepicker("setDate", $("#qStartDay").datepicker("option", "defaultDate"));

	//支付渠道全选
	$("#chkAllChannel").click(function(){
		$("input[type='checkbox'][name='qChannel']").prop("checked", $(this).prop("checked"));
	});

	//Chart Setting Start
	var platformPieChart, channelPieChart;
	var platformOptions, channelOptions;

	require.config({ paths : { echarts : 'js/echarts' }});

	// 使用(模块化按需加载)
	require([ 'echarts', 'echarts/chart/pie' ],
			function(ec) {
				platformPieChart = ec.init(document.getElementById('platformPieChart'));
				channelPieChart = ec.init(document.getElementById('channelPieChart'));
				reloadChart();
			});

	function initPieOptions(mainTitle, subText) {
		var initOptions = {
			title : { text : mainTitle, subtext : subText, x : 'center'},
			tooltip : { trigger : 'item', formatter : '{a} <br/>{b} : {c} ({d}%)' },
			legend : { orient : 'vertical', x : 'left', data : [] },
			toolbox : {
				show : true,
				feature : {
					mark : { show : true },
					saveAsImage : { show : true }
				}
			},
			calculable : true,
			series : [ {
				name : '支付平台',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : []
			} ]
		};

		return initOptions;
	}

	function reloadChart() {
		//form params
		var pidArr = [], cidArr = [];

		$("input[name='qPlatform']:checked").each(function() {
			pidArr.push($(this).val());
		});
		$("input[name='qChannel']:checked").each(function() {
			cidArr.push($(this).val());
		});
		
		var pids = pidArr.join(","), cids = cidArr.join(",");

		// 基于准备好的dom，初始化echarts图表
		// 数据再次加载时需要clear来清空上一次的结果
		$("#platformPieChart").show();
		$("#channelPieChart").show();
		platformPieChart.clear();
		platformPieChart.showLoading({ image : "../images/loading1.gif" });
		channelPieChart.clear();
		channelPieChart.showLoading({ image : "../images/loading1.gif" });
		platformOptions = initPieOptions('支付数来源统计', '业务平台');
		channelOptions = initPieOptions('支付数来源统计', '支付渠道');

		$.post('chart/paypie/search', {
			startDay : $("#qStartDay").val(),
			endDay : $("#qEndDay").val(),
			platformId : pids,
			channelId : cids
		}, function(result) {
			if (result) {
				
				//Platform Pie Chart
				platformOptions.series[0].data = result[0].series;
				platformOptions.legend.data = result[0].legend;
				platformPieChart.hideLoading();
				platformPieChart.setOption(platformOptions);

				//Channel Pie Chart
				channelOptions.series[0].data = result[1].series;
				channelOptions.legend.data = result[1].legend;
				channelPieChart.hideLoading();
				channelPieChart.setOption(channelOptions);
				
			} else {
				showError("没有相关的统计数据!");
			}
		});
	}