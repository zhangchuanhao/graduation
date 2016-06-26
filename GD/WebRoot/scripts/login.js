var name = "";
var password="";
var flag=true;
$(function() {
	$('.login_box').slideDown('slow');
	$(".name_msg,.pwd_msg").css({color:'red'});
	$('.username,.password').blur(function(){
		name = $('.username').val().trim();
		password = $('.password').val().trim();
		if(name!=""){
			$(".name_msg").html("");
		}
		if(password!=""){
			$(".pwd_msg").html("");
		}
	});
	$('.username,.pwd_msg').blur(function(){
		name = $('.username').val().trim();
		password = $('.password').val().trim();
		if(name!=""){
			$(".name_msg").html("");
		}
		if(password!=""){
			$(".pwd_msg").html("");
		}
	});
	$('.btn_login').click(function() {
	     //清空先前span的提示信息
		$(".name_msg,.pwd_msg").html("");
	    name = $('.username').val().trim();
		password = $('.password').val().trim();
		if(name==""){
			$(".name_msg").html("用户名不能为空");
			flag=false;
		}else{
			flag=true;
		}
		if(password==""){
			$(".pwd_msg").html("密码不能为空");
			flag = false;
		}else{
			flag=true;
		}
		if(flag){
			$.ajax({
				url : "http://localhost:8088/GraduationDesign/login.do",
				type : "post",
				data : {
					"name" : name,
					"password" : password
				},
				dataType : "json",
				success : function(result) {
					if(result.status ==1){
						$(".name_msg").html("");
						$(".name_msg").html(result.msg);
					}
					if(result.status==2){
						$(".pwd_msg").html("");
						$(".pwd_msg").html(result.msg);
					}
					if (result.status == 0) {
						var userid = result.data;
						addCookie("userId", userid, 0.2);
						$('.login_box').slideUp('slow');
						setTimeout(function() {
							window.location.href = "projects.html";
						}, 1000);
					}
				}
			});
		}
	});
});