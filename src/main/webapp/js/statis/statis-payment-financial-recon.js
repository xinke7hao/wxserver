var platformids = [];

$(function(){
		$("#year").val(today.getFullYear());
		$("input[type='hidden'][name='platformIds']").each(function(){
			platformids.push($(this).val());
		});
});

function searchFinance(url, tname, pname){
	$.post(url, {year: $("#year").val()}, function(result) {
		var data = eval(result);
		$("#"+tname).html("");	//clear tbody
		if(data){
			var noData = true;
			$.each(data, function(rkey, values){
					var keyMap =  data[rkey];
					$.each(keyMap, function(akey, avalues){
						noData = false;
						var monthMap = keyMap[akey];
						var tempTotal = 0.0;
						var trow = "";
						for(var j=0;j<platformids.length;j++){
							var s = monthMap[platformids[j]];
							if(s!=null){
								tempTotal += s.total_amount;
								trow += "<td class='p0'>"+s.total_amount+"</td><td class='p1'>"+s.total_income+"</td><td class='p2'>"+s.total_poundage+"</td><td class='p3'>"+s.total_count+"</td>";
							} else {
								trow += "<td class='p0'>0.0</td><td class='p1'>0.0</td><td class='p2'>0.0</td><td class='p3'>0</td>";
							}
						}
						tempTotal =  Math.round(tempTotal*100)/100;
						var row = "<tr><td class='accountTd'>" + rkey + "</td><td>" + akey + "</td><td class='ps'>" + tempTotal + "</td>" + trow + "</tr>";
						$("#"+tname).append(row);					
					});
			});
			
			if(noData){
				$("#"+pname).show();
			} else {
				$("#"+pname).hide();
				tableRowspan(tname, 0);
			}
		}
	});
}

function doStatis(){
	
	//按账号统计
	searchFinance("statis/payment/finanrecon/search", "tData", "accountPrompt");
	
	//按渠道统计
	searchFinance("statis/payment/finanrecon/channel", "cData", "channelPrompt");
}
