//jQuery validate error messages
jQuery.extend(jQuery.validator.messages, {
    required: "必填",
	remote: "校验未通过",
	email: "Email格式",
	url: "网址格式",
	date: "日期格式",
	dateISO: "日期格式",
	number: "只能数字",
	digits: "只能数字",
	creditcard: "请输入合法的信用卡号",
	equalTo: "密码不一致",
	accept: "后缀不对",
	maxlength: jQuery.validator.format("长度不能大于 {0}"),
	minlength: jQuery.validator.format("长度不能少于 {0}"),
	rangelength: jQuery.validator.format("超出长度:{0}~{1}"),
	range: jQuery.validator.format("超出范围:{0}~{1}"),
	max: jQuery.validator.format("不能大于 {0}"),
	min: jQuery.validator.format("不能小于 {0}")
});