$(function(){

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
		changeMonth: true,
		changeYear: true,
		dateFormat: 'yy-m-d',
		onClose: function( selectedDate ) {
			$( "#qStartDay" ).datepicker( "option", "maxDate", selectedDate );
		}
	});

	$( "#qStartDay" ).datepicker("setDate", $("#qStartDay").datepicker("option", "defaultDate"));
	$( "#qEndDay" ).datepicker("setDate", $("#qEndDay").datepicker("option", "defaultDate"));
	
	doReconStatis();
});

function doReconStatis(){
	$.post("statis/payment/recon/search", {platformId: $("#qPlatformId").val(), channelId: $("#qChannelId").val(), startDay: $("#qStartDay").val(), endDay: $("#qEndDay").val()}, function(data) {
		$("#tData").html("");	//clear tbody
		if(data){
			var noData = true;
			var total = [0.0,0.0,0.0,0.0];
			var pname = "";
			debugger;
			for(var i=0;i<data.length;i++){
				
				var d = data[i];
				var row = "<tr><td>"+d.platform_name+"</td><td style='border-left:solid 1px gray;'>"+d.channel_alias+"</td><td>"+d.total_count+"</td><td>"+d.total_amount+"</td><td>"+d.total_income+"</td><td>"+d.total_poundage+"</td></tr>";
				
				if(pname=="") pname = d.platform_name;
				if(pname!=d.platform_name){
					for(var j=0;j<4;j++){
						total[j] = Math.round(total[j]*100)/100;
					}
					var sumrow = "<tr><td>"+pname+"</td><td class='sumtd' style='border-left:solid 1px gray;'>总计</td><td class='sumtd'>"+total[0]+"</td><td class='sumtd'>"+total[1]+"</td><td class='sumtd'>"+total[2]+"</td><td class='sumtd'>"+total[3]+"</td></tr>";
					$("#tData").append(sumrow);
					$("#tData").append(row);
					pname = d.platform_name;
					total[0] = parseFloat(d.total_count);
					total[1] = parseFloat(d.total_amount);
					total[2] = parseFloat(d.total_income);
					total[3] = parseFloat(d.total_poundage);
				} else {
					$("#tData").append(row);
					total[0] += parseFloat(d.total_count);
					total[1] += parseFloat(d.total_amount);
					total[2] += parseFloat(d.total_income);
					total[3] += parseFloat(d.total_poundage);
					if(i==data.length-1){
						for(var j=0;j<4;j++){
							total[j] = Math.round(total[j]*100)/100;
						}
						var sumrow = "<tr><td>"+pname+"</td><td class='sumtd'>总计</td><td class='sumtd'>"+total[0]+"</td><td class='sumtd'>"+total[1]+"</td><td class='sumtd'>"+total[2]+"</td><td class='sumtd'>"+total[3]+"</td></tr>";
						$("#tData").append(sumrow);
					}
				}
				
				noData = false;
			}
			
			if(noData){
				$("#noData").show();
			} else {
				$("#noData").hide();
				tableRowspan("tData", 0);
			}
		}
	});
}

function submitRecon(){
	var sdate = new Date($("#qStartDay").val());
	var edate = new Date($("#qEndDay").val());
	var distance = (Date.parse(edate)-Date.parse(sdate)) / (24*60*60*1000);
	var maxDays = (sdate.getFullYear()%4==0&&sdate.getFullYear()%100!=0 || sdate.getFullYear()%400==0) ? 366 : 365;
	if(distance>maxDays){
		showError("日期相差不能超过1年"); return false;
	} else {
		doReconStatis();
	}
}
