$(function(){
	
	$("#monitor").click(function(){
		window.open("monitoring");
	});
	
	$("#changePass").click(function(){
		$( "#dialog-password" ).load('topass').dialog({
		    title: "更改密码",
		    autoOpen: true,
		    width: 420,
			height: 280,
			resizable: false,
			draggable: false,
			modal: true,
			buttons: {
				"确定": function() {
					if($("#passForm").valid()){
						$.post("savepass", {uid:$("#uid").val(), pass:$("#newpass").val()}, function(data){
							showInfoDialog(data);
						});
						$(this).dialog("close").empty();
					}
				},
				"取消": function() {
					$(this).dialog("close").empty();
				}
			}
		});
	});
	
});
