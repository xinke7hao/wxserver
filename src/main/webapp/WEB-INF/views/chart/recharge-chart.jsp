<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="js/echarts/echarts.js"></script>
<script src="js/chart/stack-chart.js"></script>
<script src="js/chart/recharge-chart.js"></script>
<div class="messagepop pop">
<table align="center">
	<tr>
		<td><select multiple id="unselectList">
			<c:forEach items="${facts}" var="m">
				<option value="${m.id}" title="${m.name}">${m.uuid}-${m.name}</option>
			</c:forEach>
			</select>
		</td>
		<td>
			<p><a id="addAll" href="#" ><img src="images/addall.png" /></a></p>
			<p><a id="addItem" href="#" ><img src="images/add.png" /></a></p>
			<p><a id="removeItem" href="#" ><img src="images/remove.png" /></a></p>
			<p><a id="removeAll" href="#" ><img src="images/removeall.png" /></a></p>
		</td>
		<td><select multiple id="selectedList"></select></td>
	</tr>
	<tr><td colspan="3" align="right">
	<a id="btnChoose" href="#" class="button approve">选择</a>
	<a id="btnCancel" href="#" class="button arrowleft">取消</a>
	</td></tr>
</table>
</div>
<form id="chartForm" method="post">
<input type="hidden" id="factId" name="factId" />
<input type="hidden" id="productName" name="productName" />
<div class="bossToolBar">
	<label><span>开始日期:</span><input id="qStartDay" name="startDay" type="text" class="queryDate" readonly="readonly" /></label> 
	<label><span>结束日期:</span><input id="qEndDay" name="endDay" type="text" class="queryDate" readonly="readonly" /></label>
	<label class="radio-group-line"><span>时间粒度:</span>
	<label><input name="timeType" type="radio" value="0" checked /> 天</label>
	<label><input name="timeType" type="radio" value="1" /> 月</label>
	</label>
	<label class="radio-group-line"><span>所有产品:</span>
	<label id="products" class="radio-group-line">
	<label><input type="checkbox" id="chkAllProduct" />全选</label>
	<label><input type="checkbox" name="productId" title="芒果币" value="1" />芒果币</label>
	</label>
	<a href="javascript:reloadChart();" class="button search">搜索</a>
	<a href="javascript:resetUrl('chart/recharge');" class="button reload">重置</a><br/>
	<label class="radio-group-line"><span>支付渠道(默认全选):</span>
	<label><input type="checkbox" id="chkAllChannel" />全选</label>
	<c:forEach items="${channels}" var="c">
		<label><input type="checkbox" name="channelId" value="${c.uuid}" title="${c.name}" />${c.name}</label>
	</c:forEach>
	</label>
	<br/>
	<label class="radio-group-line"><span style="padding-right:12px;">厂商(默认全选):</span>
	<a id="btnSelectFacts" href="#" class="button comment" style="margin-right:15px;">选择厂商</a>
	<label id="factLabel"></label></label>
</div>
</form>
<div id="paytimeChart" style="height:450px;"></div>
<div id="paymoneyChart" style="height:450px;"></div>
