<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(function(){
		$("#chkAllPlatform").click(function(){
			$("input[type='checkbox'][name='chkP']").prop("checked", $(this).prop("checked"));
		});
		$("#chkAllChannel").click(function(){
			$("input[type='checkbox'][name='chkC']").prop("checked", $(this).prop("checked"));
		});
		$("#chkAllDepart").click(function(){
			$("input[type='checkbox'][name='chkD']").prop("checked", $(this).prop("checked"));
		});
		$("#backBtn").click(function(){
			$.get('group/list', function(data){
				$("#mainDiv").html(data);
			});
		});
		$("#saveGroupDatas").click(function(){
			var pids = "", chkps = [], i = 0;
			var cids = "", chkcs = [], j = 0;
			var dids = "", chkds = [], k = 0;
			$("input[name='chkP']:checked").each(function(){
				chkps[i++] = $(this).val();
			});
			$("input[name='chkC']:checked").each(function(){
				chkcs[j++] = $(this).val();
			});
			$("input[name='chkD']:checked").each(function(){
				chkds[k++] = $(this).val();
			});
			pids = chkps.join(",");
			cids = chkcs.join(",");
			dids = chkds.join(",");
			$.post('group/doconfig', {id: $("#groupId").val(), pids: pids, cids: cids, dids: dids}, function(data){
				$("#mainDiv").html(data);
			});
		});
	});
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<div id="groupConfigDiv" style="overflow: auto;">
	<input type="hidden" id="groupId" value="${group.groupId}" />
	<p>
		<a id="saveGroupDatas" href="#" class="button approve">保存</a>
		<a id="backBtn" href="#" class="button reload">返回</a>
	</p>
	<fieldset style="padding: 5px; margin-left: 0px; margin: 15px; width: 95%; color: #333; border: #06c dashed 1px;">
		<legend>&nbsp;&nbsp;数据角色设置【${group.groupName}】</legend>
		<table>
		<tr>
				<c:set var="tmpModule" value="" />
				<c:set var="tmpCount" value="0" />
				<c:forEach items="${groupDatas}" var="gd" varStatus="ind">
					<c:if test="${tmpModule!=gd.dataType}">
						<c:set var="tmpModule" value="${gd.dataType}" />
						<c:if test="${tmpCount>0}"></ul></fieldset></td></c:if>
						<td style="width: 300px; vertical-align: top;">
						<fieldset style="padding: 5px; margin-left: 0px; margin-top: 0px; margin: 15px; color: #333; border: #06c dashed 1px;">
						<legend>
						<c:if test="${tmpModule=='P'}">&nbsp;&nbsp;业务平台 <label><input type="checkbox" id="chkAllPlatform"  /> 全选</label></c:if>
						<c:if test="${tmpModule=='C'}">&nbsp;&nbsp;支付渠道 <label><input type="checkbox" id="chkAllChannel" /> 全选</label></c:if>
						<c:if test="${tmpModule=='D'}">&nbsp;&nbsp;组织机构 <label><input type="checkbox" id="chkAllDepart" /> 全选</label></c:if>
						</legend>
						<ul style="list-style-type: none; padding: 0.5em;">
						<c:set var="tmpCount" value="1" />
					</c:if>
					<li style="padding-bottom: 0.4em;"><label><input type="checkbox" name="chk${gd.dataType}" <c:if test="${gd.groupId!=null}">checked</c:if> value="${gd.dataId}" />${gd.dataName}</label></li>
					<c:if test="${ind.last}"></ul></fieldset></td></c:if>
				</c:forEach>
		</tr>
		</table>
	</fieldset>
</div>
</spring:escapeBody>
</spring:htmlEscape>