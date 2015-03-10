<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="./js/base64.js"></script>
<script type="text/javascript">

	var b = new Base64();  

	$("#passForm").validate({
		rules : {
			oldpass : {
				required: true,
				remote: {
			        url: "checkpass",
			        type: "post",
			        data: {
			          uid: function(){ return $("#uid").val(); },
			          pass: function(){ return b.encode($("#oldpass").val()); }
			        }
			      }
			},
			newpass : {required: true},
			confirmpass : {equalTo: "#newpass"}
		}, 
		messages: {
			oldpass: {
				remote: "密码不正确"
			}
		},
		errorPlacement: function(error, element) {
			if ( element.is(":radio") ){
				error.insertAfter( element.parent().next() );
			} else {
			    error.insertAfter(element);
			}
		}
	});
</script>
<form id="passForm" action="savepass" method="post" >
<input type="hidden" id="uid" value="${loginUser.userId}" />
<fieldset class="dialog-form-fieldset">
	<p>
		<label class="infolabel">用户名:</label>${loginUser.userCode}</p>
	<p>
		<label class="inputlabel"><font class="requiredMark">*</font>旧密码:</label>
		<input id="oldpass" type="password" name="oldpass" class="stringfield" />
	</p>
	<p>
		<label class="inputlabel"><font class="requiredMark">*</font>新密码:</label>
		<input id="newpass" type="password" name="newpass"	class="stringfield" />
	</p>
	<p>
		<label class="inputlabel"><font class="requiredMark">*</font>确认密码:</label>
		<input id="confirmpass" type="password" name="confirmpass" class="stringfield" />
	</p>
</fieldset>
</form>