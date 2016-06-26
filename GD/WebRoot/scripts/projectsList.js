//当前总页数
var current_pages=0;
//显示的页码，默认显示第一页
var page=1;
//记录上一次的页码
var oldpage=1;
//记录本次查询的关键字
var keywords = null;
//复选框选中状态

var flag = false;
$(function() {
	var userId = getCookie("userId");
	if(userId==null){
		window.location.href="login.html";
		return;
	}
	$("#body").slideDown("slow");
	//1.初始表显示
	pageList(page);
	//2.分页显示
	$("#pages").on("click","a",doPage);
	//3.复选框
	$("#tb1").on("click","input:checkbox",doCheckbox);
	//4.搜索事件
	$(".btn_search").click(doSearch);
	//5.提交
	$("#btn_submit").click(doSubmit);
	//6.删除
	$("#tb1").on("click",".btn_delete",doDelete);
	//7.更改密码
	$("#cpwd").click(function(){
		$("#cpwddiv").load("pwdwindow.html");
	});
	$(".cancle").click(function(){
		alert("123");
		$("#cpwddiv").empty();
	});
});
//显示页码-------------------------------------------------------------------------------
function showPages(pages){
	//显示页码函数
	//清空页码
	$("#pages").empty();
	//添加新的页码
	$("#pages").append($('<a>上一页</a>'));
	//循环添加页码
	for( var i = 1; i <= pages;i++){
		var a='<a id="a'+i+'">'+i+'</a>';
		if(i==1){
			a='<a class="current_page" id="a1">1</a>';
		}
		var $a = $(a);
		$("#pages").append($a);
	}
	$("#pages").append($('<a>下一页</a>'));
}
//处理结果显示列表(1:页码.2:列表)-----------------------------------------------------------
function getprojectlist(result){
	//返回数据成功
	if (result.status == 0) {
		//清空显示
		$("#tb1").empty();
		//记录当前总页数
		current_pages=result.pages;

		// 循环result.data生成列表
		var pros = result.data;
		for ( var i = 0; i < pros.length; i++) {
			var proId = pros[i].pro_id;//课题序号
			var proTitle = pros[i].pro_title;// 课题名称
			var proDesc = pros[i].pro_desc;// 课题描述
			var proTeacher = pros[i].pro_teacher;// 指导老师
			var proStatus = pros[i].pro_status;//课题选中状态
			
			var cb = '<input type="checkbox" value="'+proId+'"';
			//已被选择的课题无法操作
			if(proStatus==1){
				cb+= 'disabled="disabled" checked="checked"';
			}
			cb+='/>';
			var tr = '<tr>';
			tr += '<td>'+cb+'</td>'+
				  '<td id="id">'+ proId+ '</td>'+
				  '<td>'+ proTitle+ '</td>'+
				  '<td>'+ proDesc+ '</td>'+
				  '<td><a href="javascript:;">'+ proTeacher + '</a></td>'+
				  '<td>'+                                
				  	'<input type="button" value="修改" class="btn_modify"/>'+
                    '<input type="button" value="删除" class="btn_delete"/>'+
				  '</td>'+
				'</tr>';
			// 将tr绑定到tr元素上
			var $tr = $(tr);// 将s_li字符串转成jQuery对象
			// 追加新的元素
			$("#tb1").append($tr);
		}
		//保证表格定高显示，追加空白行
		for(var i=0;i<20-pros.length;i++){
			var tr='<tr><td></td><td></td><td></td><td></td><td></td></tr>';
			$("#tb1").append($(tr));
		}
	}
}
//初始化显示（默认显示第一页）--------------------------------------------------------
function pageList(page) {
	// 发送ajax请求
	$.ajax({
			url : "http://localhost:8088/GraduationDesign/projects.do",
			type : "post",
			data:{"page":page,"keywords":keywords},
			dataType : "json",
			success : function(result){
				getprojectlist(result);
				showPages(result.pages);
			},
			error : function() {
				 
			}
		});
}

// 分页实现-------------------------------------------------------------------
function doPage() {
	$("#pages a").removeClass("current_page");
	//获取当前页
	page = $(this).text();
	//记录当前页(数字页码)
	if(!isNaN(page)){
		oldpage = page;
	}
	//点击的是上一页或者下一页
	if(page=="上一页"||page=="下一页"){
		if(oldpage<=1&&page=="上一页"){
			new Toast({context:$('body'),message:'当前页为第一页'}).show();
			page = oldpage;
		}else if(oldpage==current_pages&&page=="下一页"){
			new Toast({context:$('body'),message:'当前页为最后页'}).show();
			page = oldpage;
		}else if(page=="上一页"&&oldpage>1){
			oldpage=parseInt(oldpage)-1;
			page=oldpage;
		}else if(page=="下一页"&&oldpage<current_pages){
			oldpage=parseInt(oldpage)+1;
			page=oldpage;
		}
	}
	
	$("#a"+page+"").addClass("current_page");
	$.ajax({
		url : "http://localhost:8088/GraduationDesign/projects.do",
		type : "post",
		data:{"page":page,"keywords":keywords},
		dataType : "json",
		success : function(result){
			getprojectlist(result);
		},
		error : function() {
			 
		}
	});
}
//复选框-----------------------------------------------------------------
var selectedval = null;
function doCheckbox(){
	//选中
	if($(this).is(':checked')){
		//设置其他复选框无法选中
		$("#tb1 input:checkbox[value !="+$(this).val()+"]").attr("disabled",true);
		//获取复选框的值
		selectedval = $(this).val();
	}
	//未选中
	else{
		selectedval = null;
		$("#tb1 input:checkbox[value !="+$(this).val()+"]").attr("disabled",false);
		$("#tb1 input:checkbox:checked").attr("disabled",true);
	}
		
	$("input:checkbox").on('change',function(){
	});
}
//提交事件-----------------------------------------------------------------
function doSubmit(){
	if(selectedval==null){
		new Toast({context:$('body'),message:'未选择课题'}).show();
		return;
	}
	$.ajax({
		url : "http://localhost:8088/GraduationDesign/submit.do",
		type : "post",
		data:{"userId":page,"selectedId":selectedval},
		dataType : "json",
		success : function(result){
			$("#tb1 input:checkbox[value ="+selectedval+"]").attr({"disabled":true,"checked":true});
			new Toast({context:$('body'),message:result.msg}).show();
		},
		error : function() {
			new Toast({context:$('body'),message:'提交失败'}).show();
		}
	});
}
//删除按钮-----------------------------------------------------------------
function doDelete(){
	var f = window.confirm("确定要删除此管理员吗？");
	if(f){
		var id = $(this).parent().parent().find("#id").text();
		$(this).parent().parent().remove();
		$.ajax({
			url:"http://localhost:8088/GraduationDesign/delete.do",
			type : "post",
			data:{"id":id},
			dataType : "json",
			success : function(result){
				if(result.status==0){
					new Toast({context:$('body'),message:result.msg}).show();
				}
			},
			error : function() {
				new Toast({context:$('body'),message:'提交失败'}).show();
			}
		});
	}
}
//搜索按钮点击事件-----------------------------------------------------------
var oldKeywords=null;
function doSearch(){
	//获取当前关键字
	var keyword=$("#keyword select option:selected").val();
	//记录关键字
	keywords = keyword;
	//将页码置1
	page = 1;	
	//发送请求
	$.ajax({
		url : "http://localhost:8088/GraduationDesign/projects.do",
		type : "post",
		data:{"keywords":keyword},
		dataType : "json",
		success : function(result){
			//无匹配关键字
			if(result.pages==0){
				new Toast({context:$('body'),message:'无匹配关键字'}).show();
				keywords = oldKeywords;
				return;
			}
			//查询成功重置页码
			page = 1;
			oldpage = 1;
			current_pages=result.pages;
			//显示页面内容
			getprojectlist(result);
			showPages(result.pages);
			//关键字赋值
			oldKeywords = keywords;
		},
		error : function() {
			alert("查询失败");
		}
	});
}
/** 
 * 模仿android里面的Toast效果，主要是用于在不打断程序正常执行的情况下显示提示数据 
 * @param config 
 * @return 
 */  
var Toast = function(config){  
    this.context = config.context==null?$('body'):config.context;//上下文  
    this.message = config.message;//显示内容  
    this.time = config.time==null?3000:config.time;//持续时间  
    this.left = config.left;//距容器左边的距离  
    this.top = config.top;//距容器上方的距离  
    this.init();  
}  ;
var msgEntity;  
Toast.prototype = {  
    //初始化显示的位置内容等  
    init : function(){  
        $("#toastMessage").remove();  
        //设置消息体  
        var msgDIV = new Array();  
        msgDIV.push('<div id="toastMessage">');  
        msgDIV.push('<span>'+this.message+'</span>');  
        msgDIV.push('</div>');  
        msgEntity = $(msgDIV.join('')).appendTo(this.context);  
        //设置消息样式  
        var left = this.left == null ? this.context.width()/2-msgEntity.find('span').width()/2 : this.left;  
//        var top = this.top == null ? '20px' : this.top;  
        msgEntity.css({position:'fixed',
        			   top:0,
        			   'z-index':'99',
        			   left:left,'background-color':'black',
        			   color:'white',
        			   'font-size':'10px',
        			   padding:'10px',
        			   margin:'10px',
        			   'border-radius':'5px'
        			});  
        msgEntity.hide();  
    },  
    //显示动画  
    show :function(){  
    	msgEntity.slideDown(1000);  
//        msgEntity.fadeIn(this.time/2); 
//    	msgEntity.slideUp(2000);
        msgEntity.fadeOut(this.time);  
    }  
          
}  ;