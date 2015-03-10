<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="js/home.js"></script>
<div style="width: 99%; padding-top: 2px; padding-bottom: 2px; margin-top: 1px;">
	<table style="width: 100%;">
	<tr>
		<td align="left" rowspan="2">
			<div style="width: 300px; height: 70px;">
				<img src="images/logo.jpg" style="width: 220px; height: 70px;" />
				<img id="sysLoadImage" src="images/loading.gif" style="margin-left: 20px; width: 50px; height: 50px; overflow: auto; display:none;" />
			</div>
		</td>
		<td></td>
		<td align="right" valign="bottom">
			<div style="font-size: 20px; font-weight: bold; padding-bottom: 5px;">Billing Management Center</div>
		</td>
	</tr>
	<tr>
		<td>
			<div id="dialog-info"></div>
			<div id="dialog-error"></div>
		</td>
		<td align="right">
		<div style="font-size: 14px; padding-bottom: 5px;">
			Welcome, ${loginUser.userCode}. 
			<c:if test="${loginUser.isAdmin=='Y'}">
				<a id="monitor" title="系统监控"><img src="images/monitor.png" width="32" height="32" /></a>
			</c:if>
			<a id="changePass" title="更改密码"><img src="images/change_pass.png" width="32" height="32" /></a>
			<a href="logout" title="退出系统"><img src="images/shutdown.png" /></a>
		</div>
		</td>
	</tr>
	</table>
</div>
<div id="dialog" style="display:none;"></div>
<div id="dialog-password" style="display:none;"></div>
<div id="dialog-other" style="display:none;"></div>
<div id="dialog-group" style="display:none;"></div>
<div id="dialog-detail" style="display:none;"></div>
<div id="dialog-confirm" title="警告" style="display:none;"></div>
