<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(function(){
	$("#chkAll").click(function(){
		$("input[type='checkbox']").prop("checked", $(this).prop("checked"));
	});
	
	$("input[name='mchks']").click(function(){
		$("input[name='nchks'][mvalue="+$(this).val()+"]").prop("checked", $(this).prop("checked"));
		$("input[name='rchks'][mvalue="+$(this).val()+"]").prop("checked", $(this).prop("checked"));
	});
	
	$("input[name='nchks']").click(function(){
		$("input[name='rchks'][nvalue="+$(this).val()+"]").prop("checked", $(this).prop("checked"));
	});
	
	$("#saveRoleRights").click(function(){
		var rids = "", chks = [];
		$("input[name='rchks']:checked").each(function(){
			chks.push($(this).val());
		});
		rids = chks.join(",");
		$.post('role/doconfig', {id:$("#roleId").val(), rightIds:rids}, function(data){
			$("#mainDiv").html(data);
		});
	});
	
	$("#backBtn").click(function(){
		$.get('role/list', function(data){
			$("#mainDiv").html(data);
		});
	});
	
});
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<div id="roleConfigDiv" style="overflow: auto;">
	<input type="hidden" id="roleId" value="${role.roleId}" />
	<p>
		<a id="saveRoleRights" href="#" class="button approve">保存</a>
		<a id="backBtn" href="#" class="button reload">返回</a>
	</p>
	<fieldset style="padding: 5px; margin-left: 0px; margin: 15px; width: 95%; color: #333; border: #06c dashed 1px;">
		<legend>&nbsp;&nbsp;【${role.roleName}】角色功能设置&nbsp;&nbsp;<label><input type="checkbox" id="chkAll" /> 全选</label></legend>
		<table style="width: 100%;">
			<thead>
				<tr style="text-align: left;line-height: 30px;">
					<th style="width:20%; background-color: gray; color: white; padding-left:10px;">模块名称</th>
					<th style="width:20%; background-color: gray; color: white; padding-left:10px;">菜单名称</th>
					<th style="background-color: gray; color: white; padding-left:10px;">功能名称</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="pModuleId" value="-1" />
				<c:set var="pMenuId" value="-1" />
				<c:forEach items="${roleRights}" var="rr">
					<tr>
						<c:if test="${pModuleId==rr.moduleId}">
							<td></td>
						</c:if>
						<c:if test="${pModuleId!=rr.moduleId}">
							<td style="border-top: solid 1px darkgray;">
								<label><input type="checkbox" name="mchks" value="${rr.moduleId}" /> ${rr.moduleName}</label>
							</td>
							<c:set var="pModuleId" value="${rr.moduleId}" />
						</c:if>
						<c:if test="${pMenuId==rr.menuId}">
							<td style="border-left: solid 1px darkgray;"></td>
						</c:if>
						<c:if test="${pMenuId!=rr.menuId}">
							<td style="border-left: solid 1px darkgray;border-top: solid 1px darkgray;">
								<label><input type="checkbox" name="nchks" mvalue="${rr.moduleId}" value="${rr.menuId}" /> ${rr.menuName}</label>
							</td>
							<c:set var="pMenuId" value="${rr.menuId}" />
						</c:if>
						<td style="border-left: solid 1px darkgray;border-bottom: solid 1px darkgray;">
							<label><input type="checkbox" name="rchks" mvalue="${rr.moduleId}" nvalue="${rr.menuId}" <c:if test="${rr.hasRight}">checked</c:if> value="${rr.rightId}" />${rr.rightName}</label>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
</div>
</spring:escapeBody>
</spring:htmlEscape>