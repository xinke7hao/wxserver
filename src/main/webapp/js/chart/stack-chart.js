// **** 厂商选择控件 Start ****
$("#chkAllManuFacturners").click(function(){
	var flag = $(this).prop("checked");
	$("input[type='checkbox'][name='qFacturer']").each(function(){
		$(this).prop("checked", flag);
	});			
});

function deselect(e) {
	$('.pop').slideFadeToggle(function() {
		e.removeClass('selected');
	});    
}

$("#btnSelectFacts").click(function(){
	if($(this).hasClass('selected')) {
		deselect($(this));               
	} else {
		$(this).addClass('selected');
		$('.pop').slideFadeToggle();
	}
	return false;
});

$('#btnCancel').click(function() {
	deselect($('#btnCancel'));
	return false;
});

$.fn.slideFadeToggle = function(easing, callback) {
	return this.animate({ opacity: 'toggle', height: 'toggle' }, 'fast', easing, callback);
};

var leftSel = $("#unselectList"), rightSel = $("#selectedList");
$("#addAll").click(function(){
	rightSel.append(leftSel.html());
	leftSel.empty();
});
$("#removeAll").click(function(){
	leftSel.append(rightSel.html());
	rightSel.empty();
});
$("#addItem").click(function(){
	leftSel.find("option:selected").clone().appendTo(rightSel);
	leftSel.find("option:selected").remove();
});
$("#removeItem").click(function(){
	rightSel.find("option:selected").clone().appendTo(leftSel);
	rightSel.find("option:selected").remove();
});

$("#btnChoose").click(function(){
	$("#factLabel").empty();
	var ids = [], labels = [];
	rightSel.find("option").each(function(){
		ids.push($(this).val());
		labels.push($(this).attr("title"));
	});
	$("#factId").val(ids.join(','));
	$("#factLabel").append(labels.join("&nbsp;&nbsp;|&nbsp;&nbsp;"));
	deselect($('#btnCancel'));
	return false;
});
// **** 厂商选择控件 End ****

//日期条件范围判断
function judgeDateDuration(){
	var sdate = new Date($("#qStartDay").val());
	var edate = new Date($("#qEndDay").val());
	var distance = (Date.parse(edate)-Date.parse(sdate)) / (24*60*60*1000);
	var tType = $("input[type='radio'][name='timeType']:checked").val();
	var maxDays = (sdate.getFullYear()%4==0&&sdate.getFullYear()%100!=0 || sdate.getFullYear()%400==0) ? 366 : 365;
	if(tType==1 && distance>maxDays){
		showError("日期相差不能超过1年"); return false;
	} else if(tType==0 && distance>31){
		showError("日期相差不能超过31天");	return false;
	}
	return true;
}

//初始化堆积图（带和值）
function initStackChartOptions() {
	var option = {
			title: {text: '', x: 'center' }, tooltip: { trigger: 'axis' }, calculable: true,
			legend: {y: 35, data:[]},
			toolbox: {show: true, feature: { magicType: {show: true, type: ['line', 'bar']}, saveAsImage: {show: true}}},
			xAxis: [
			    {type: 'category', data: [] },
			    {type : 'category', axisLine: {show:false}, axisTick: {show:false}, axisLabel: {show:false}, splitArea: {show:false}, splitLine: {show:false}, data : []}
			],
			yAxis: [{type: 'value', axisLabel:{formatter:'{value}'} } ],
			series: []
	};

	return option;
}

// 格式化数值
function toDecimal(x) {  
    var f = parseFloat(x);  
    f = Math.round(x*100)/100;
    return f;
}

/**
 * 显示堆积图, Echart基础上定制
 * 并且增加图例开关响应，和值显示位置自动调整
 * @param chart
 * @param chartoptions
 * @param title
 * @param totalValue
 * @param chartLegend
 * @param chartCategory
 * @param chartSeries
 * @param totals
 */
function showStackChartWithTotal(chart, chartoptions, title, totalValue, chartLegend, chartCategory, chartSeries, totals, eventRegistered){

	chart.clear();
	chartoptions = initStackChartOptions();
	chart.showLoading({ image : "../images/loading1.gif" });

	chartoptions.title.text = title + totalValue;
	chartoptions.legend.data = chartLegend;
	chartoptions.xAxis[0].data = chartCategory;
	chartoptions.xAxis[1].data = chartCategory;
	chartoptions.series = chartSeries;
	
	var clen = chartoptions.xAxis[0].data.length, slen = chartoptions.legend.data.length;
	for(var i=0;i<slen;i++){
		chartoptions.series[i].itemStyle = {normal: {label:{show:true, position: 'inside'}}};
	}
	chartoptions.series.push({	
		name:'Total', type:'bar', xAxisIndex:1,
		itemStyle: {normal: {color:'white', label:{show:true, textStyle:{color:'#27727B'}, position: 'top'}}},
		data:totals
	});
	chart.hideLoading();
	chart.setOption(chartoptions);
	
	//防止事件重复注册
	if(!eventRegistered){
		registerLegendEvent(chart);
	}
}

/**
 * 注册图例开关响应事件
 * @param chartObj
 */
function registerLegendEvent(chartObj){
	chartObj.on("legendSelected", function(param){
		var flag = param.selected[param.target];
		var index = -1;
		var tmpLegend = chartObj.component.legend.legendOption.data;
		var tmpCategory = chartObj.component.xAxis.getAxis(0).option.data;
		for(var i=0;i<tmpLegend.length;i++){
			if(tmpLegend[i]==param.target){
				index = i; break;
			}
		}
		var tmpOption = chartObj._option;
		var slen = tmpOption.series.length;
		var tmpSeries = tmpOption.series[index].data;
		var tmpTotalSeries = tmpOption.series[slen-1].data;
		for(var i=0;i<tmpCategory.length;i++){
			if(flag){
				tmpTotalSeries[i] += tmpSeries[i];
			} else {
				tmpTotalSeries[i] -= tmpSeries[i];
			}
		}
		for(var i=0;i<tmpCategory.length;i++){
			tmpTotalSeries[i] = toDecimal(tmpTotalSeries[i]);
		}
		chartObj.setOption(tmpOption);
	});
}