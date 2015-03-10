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
	var paymentAChart, paymentBChart, paymentCChart;
	var paymentAOptions, paymentBOptions, paymentCOptions;

	require.config({ paths : { echarts : 'js/echarts' }});

	// 使用(模块化按需加载)
	require([ 'echarts', 'echarts/chart/line' ],
			function(ec) {
				paymentAChart = ec.init(document.getElementById('paymentAChart'));
				paymentBChart = ec.init(document.getElementById('paymentBChart'));
				paymentCChart = ec.init(document.getElementById('paymentCChart'));
				reloadChart();
			});

	function initLineOptions() {
		option = {
// 			title: { text:'各支付渠道支付成功订单数', x:'center', y:'bottom' },
			tooltip : { trigger : 'axis' },
			legend : { x: 'center', y: 35, data : [] },
			toolbox : {
				show : true,
				feature : {
					mark : { show : true },
					saveAsImage : { show : true }
				}
			},
			calculable : true,
			xAxis : [ { type : 'category', boundaryGap : false, data : [] } ],
			yAxis : [ { type : 'value' } ],
			series : []
		};

		return option;
	}

	function reloadChart() {
		//form params
		var pidArr = [], cidArr = [], fieldArr = [];

		$("input[name='qPlatform']:checked").each(function() {
			pidArr.push($(this).val());
		});
		$("input[name='qChannel']:checked").each(function() {
			cidArr.push($(this).val());
		});
		$("input[name='qField']:checked").each(function() {
			fieldArr.push($(this).val());
		});
		
		if(fieldArr.length<1){
			showError("请至少勾选一个统计项");
			return false;
		}

		var pids = pidArr.join(","), cids = cidArr.join(","), fields = fieldArr.join(",");
		var showA = fields.indexOf('A')!=-1, showB = fields.indexOf('B')!=-1, showC = fields.indexOf('C')!=-1;

		// 基于准备好的dom，初始化echarts图表
		// 数据再次加载时需要clear来清空上一次的结果
		if(showA) {
			$("#paymentAChart").show(); 
			paymentAChart.clear();
			paymentAChart.showLoading({ image : "../images/loading1.gif" });
			paymentAOptions = initLineOptions();
		} else {
			$("#paymentAChart").hide();
		}
		if(showB) {
			$("#paymentBChart").show(); 
			paymentBChart.clear();
			paymentBChart.showLoading({ image : "../images/loading1.gif" });
			paymentBOptions = initLineOptions();
		} else {
			$("#paymentBChart").hide();
		}
		if(showC) {
			$("#paymentCChart").show(); 
			paymentCChart.clear();
			paymentCChart.showLoading({ image : "../images/loading1.gif" });
			paymentCOptions = initLineOptions();
		} else {
			$("#paymentCChart").hide();
		}

		$.post('chart/main/search', {
			startDay : $("#qStartDay").val(),
			endDay : $("#qEndDay").val(),
			platformId : pids,
			channelId : cids,
			fields : fields
		}, function(result) {
			if (result) {
				debugger;
				//Payment Line Chart A -- 订单数/结单数
				if(showA){
					var chartData = result['A'];
					if (chartData.series && chartData.series != "") {
						paymentAOptions.xAxis[0].data = chartData.category;
						paymentAOptions.series = chartData.series;
						paymentAOptions.legend.data = chartData.legend;
						paymentAChart.hideLoading();
						paymentAChart.setOption(paymentAOptions);
					}
				}

				//Payment Line Chart B -- 掉单率/成功率
				if(showB){
					var chartData = result['B'];
					if (chartData.series && chartData.series != "") {
						paymentBOptions.xAxis[0].data = chartData.category;
						paymentBOptions.series = chartData.series;
						paymentBOptions.legend.data = chartData.legend;
						paymentBChart.hideLoading();
						paymentBChart.setOption(paymentBOptions);
					}
				}

				//Payment Line Chart C -- 客单价/支付金额
				if(showC){
					var chartData = result['C'];
					if (chartData.series && chartData.series != "") {
						paymentCOptions.xAxis[0].data = chartData.category;
						paymentCOptions.series = chartData.series;
						paymentCOptions.legend.data = chartData.legend;
						paymentCChart.hideLoading();
						paymentCChart.setOption(paymentCOptions);
					}
				}
					
			} else {
				showError("没有相关的统计数据!");
			}
		});
	}