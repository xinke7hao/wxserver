<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

	var logstatus = {"Y":"正常","N":"错误"};
	var logtype = {"D":"删除","U":"编辑","Q":"查询","H":"HTTP调用"};

	$(function(){
		
		//加载日志控件
		$.get("desk/logs", function(data){
			if(data){
				var logtable = "<table>";
				logtable += "<thead><tr><th>状态</th><th>操作人</th><th>操作类型</th><th>模块名</th><th>操作时间</th></tr><thead><tbody>";
				for(var i=0;i<data.length;i++){
					var d = data[i];
					if(d.logStatus=='N'){
						logtable += "<tr class='error'>";
					} else {
						logtable += "<tr>";
					}
					logtable += "<td>"+logstatus[d.logStatus]+"</td><td>"+d.logUser+"</td><td>"+logtype[d.logType]+"</td><td>"+d.moduleName+"</td><td>"+formatDatetime(d.logTime)+"</td></tr>";
				}
				logtable += "</tbody></table>";
				$("#logs").append(logtable);
			} else {
				$("#logs").html("没有数据可显示.");
			}
		});
		
	});
</script>
**************************************************<br/>
***************** Admin Desktop ***************<br/>
**************************************************<br/>
<div id="logs" class="content-div"></div>