<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
	$("#inputForm").validate({
		rules : {
			userCode: {
				required: true, 
				maxlength: 20, 
				alphanumeric: true,
				remote: {
			        url: "user/checkcode",
			        type: "post",
			        data: {
				      uid: -1, code: function(){ return $("#userCode").val(); }
			        }
			      }
			},
			departId: "required",
			userPass: "required",
			userName: {required: true, maxlength: 30},
			phone: {digits: true, rangelength: [6, 11]},
			email: {maxlength: 50, email: true},
			isAdmin: "required",
			userStatus: "required",
			userType: "required"
		},
		messages: {
			userCode: {
				remote: "用户名已存在"
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

	//Generate Random Password
	function genPass(){
		$.get("user/genpass", function(data){
			$("#userPass").val(data);
		});
	}
</script>
<spring:htmlEscape defaultHtmlEscape="false">
<spring:escapeBody htmlEscape="false">
<form:form commandName="userForm" id="inputForm" method="post">
<fieldset class="dialog-form-fieldset">
		<p>
			<form:label path="userCode" class="inputlabel"><font class="requiredMark">*</font>账号: </form:label>
			<form:input path="userCode" class="stringfield" />
		</p>
		<p>
			<form:label path="userPass" class="inputlabel"><font class="requiredMark">*</font>密码: </form:label>
			<form:input path="userPass" class="stringfield" />
			<a id="genPass" title="生成随机密码" onclick="genPass();">
				<img src="images/gen-pass.png" style="width:20px;height:20px;vertical-align:top;" />
			</a>
		</p>
		<p>
			<form:label path="userName" class="inputlabel"><font class="requiredMark">*</font>姓名: </form:label>
			<form:input path="userName" class="stringfield" />
		</p>
		<p>
			<form:label path="phone" class="inputlabel">联系电话: </form:label>
			<form:input path="phone" class="stringfield" />
		</p>
		<p>
			<form:label path="departId" class="inputlabel"><font class="requiredMark">*</font>部门: </form:label>
			<form:select path="departId">
				<form:option label="---请选择---" value="" />
				<form:options items="${departList}" itemLabel="departName" itemValue="departId" />
			</form:select>
		</p>
		<p>
			<form:label path="email" class="inputlabel">Email: </form:label>
			<form:input path="email" class="stringfield" />
		</p>
		<p>
			<form:label path="userType" class="inputlabel"><font class="requiredMark">*</font>工作台: </form:label>
			<form:select path="userType">
				<form:option label="---请选择---" value="" />
				<form:option label="普通工作台" value="0" />
				<form:option label="管理员工作台" value="1" />
				<form:option label="领导工作台" value="2" />
			</form:select>
		</p>
		<p>
			<form:label path="isAdmin" class="inputlabel"><font class="requiredMark">*</font>管理员: </form:label>
			<label class="selection"><form:radiobutton path="isAdmin" value="N" />否</label>
			<label class="selection"><form:radiobutton path="isAdmin" value="Y" />是</label>
		</p>
		<p>
			<form:label path="userStatus" class="inputlabel"><font class="requiredMark">*</font>状态: </form:label>
			<label class="selection"><form:radiobutton path="userStatus" value="Y" />启用</label>
			<label class="selection"><form:radiobutton path="userStatus" value="N" />禁用</label>
		</p>
	</fieldset>
</form:form>
</spring:escapeBody>
</spring:htmlEscape>
