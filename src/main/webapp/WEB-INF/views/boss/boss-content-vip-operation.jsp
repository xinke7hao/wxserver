<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(function(){
	$("#inputForm").validate({
		rules : { codes: "required" }, 
		errorPlacement: function(error, element) {
			error.insertAfter( element.parent().next().next() );
		}
	});
	
	var ops = $("#ops").val();
	if(ops!=""){
		for(var i=1;i<4;i++){
			$("#c"+i).prop("checked", ops.charAt(i-1)=='1');
		}
	} else {
		$("input[type='checkbox'][name='codes']").prop("checked", false);
	}
});

</script>
<form id="inputForm" action="boss/content/vip/setoperation" method="post">
<input type="hidden" name="ids" value="${ids}" />
<input type="hidden" id="ops" value="${ops}" />
	<fieldset class="dialog-form-fieldset">
		<p>
			<label class="selection"><input type="checkbox" id="c1" name="codes" value="1" />VIP运营</label>
			<label class="selection"><input type="checkbox" id="c2" name="codes" value="2" />单点产品运营</label>
			<label class="selection"><input type="checkbox" id="c3" name="codes" value="3" />集合产品运营</label>
		</p>
	</fieldset>
</form>
