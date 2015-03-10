<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
var cols = [{ "mData": "id", "sClass": "dt-center", "mRender": function(data){ return "<input type='checkbox' name='cids' value='"+data+"' />"; }},
            { "mData": "id", "sClass": "dt-center" },
        	{ "mData": "name", "sClass": "dt-center" }];

var innerTable = $('#bossInnerTable').DataTable({
	'dom': 'rtipl',
	'displayLength': 5,
	"lengthMenu": [5,10,15],
	'scrollY': '250px',
	'scrollX': true,
	'autoWidth': true,
	'filter': false,
	'searching':false,
	'bProcessing' : true,
	'serverSide' : true,
	'sEcho': 1,
	'sAjaxSource': 'boss/content/pack/search',
	'sServerMethod': 'POST',
	'ordering': false,
	"aoColumns": cols,
	'language': {"url": "js/page_lang.txt"}
});

//Make TR Selectable
$('#bossInnerTable tbody').on('click','tr',function(){
	$(this).toggleClass('selected');
    var ck = $(this).find("td:first input[type=checkbox]");
    ck.prop("checked", !ck.prop("checked"));
});

$("#checkAllChilds").click(function(){
	$("input[type='checkbox'][name='cids']").prop("checked", $(this).prop("checked"));
});
</script>
<table id="bossInnerTable" width="100%" class="display" cellspacing="0" style="margin: 0px;">
	<thead>
		<tr>
			<th style="width:20px;background-color: darkgray;"><input type="checkbox" id="checkAllChilds" /></th>
			<th style="background-color: darkgray;">ID</th>
			<th style="background-color: darkgray;">名称</th>
		</tr>
	</thead>
</table>
