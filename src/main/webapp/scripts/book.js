//将笔记本重命名
function renameNotebook(){
				
	//获取请求参数
	var name=$('#input_notebook_rename').val().trim();
	var bookId=$('#book_ul a.checked').parent().data("bookId")		
	console.log(bookId);
	//校验请求参数
	var ok=true;
	if(!name){
		$('#renamenotebook_msg').html("请输入新的笔记本名称")
		ok=false;
	}
	if(!bookId){
		alert("系统异常,请重新选择笔记本")
		ok=false;
	}
	//发送ajax请求
	if(ok){
		$.ajax({
			url:base_path+"/book/rename.do",
			type:"post",
			data:{"name":name,"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.status==0){//回调成功
					closeAlertWindow();
				var si='';
				si+=    '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
				si+=    '</i>'+name
				$('#book_ul a.checked').html(si);
				var bookId2=$('#book_ul a.checked').parent().data("bookId")		
				console.log(bookId2);
				alert(result.msg)
				}
			},
			error:function(){alert("重命名异常")}
		})

	}
}
//创建笔记本功能		
function addNotebook(){
	//获取请求参数
	var userId=getCookie("uid");
	var name=$('#input_notebook').val().trim();
	//校验请求参数
	var ok=true;
	if(!name){//名称不能为空
		$('#addnotebook_msg').html("名称为空").css({"color":"red"});
		ok=false;
	}
	if(!userId){//用户ID不能为空
		alert("系统繁忙,请重新登录");
		ok=false;
		window.location.href="log_in.html";
	}
	var lis=$('#book_ul').find('li');
	for (var i = 0; i < lis.length; i++) {
		var existname= $(lis[i]).text();
		if(name==existname){//笔记本名称不能重复
			ok=false;
			$('#addnotebook_msg').html("名称已存在").css({"color":"red"});
			break;
		}
	}			
	//发送ajax请求
	if(ok){
		$.ajax({
			url:base_path+"/book/add.do",
			type:"post",
			data:{"userId":userId,"name":name},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					closeAlertWindow();
					var bookId=result.data.cn_notebook_id;
					var bookName=result.data.cn_notebook_name;
					createBookLi(bookId,bookName)
					alert(result.msg);
				}else{
					alert(result.msg);
				}
				
			},
			error:function(){alert("添加笔记本失败")}
		})
	}
	
}
//封装笔记本相关处理
function loadUserBooks(){
	//页面载入发送ajax请求加载用户笔记本liebiao
	//获取请求参数
	var userId=getCookie("uid")
	//检查数据格式
	if(!userId){
		window.location.href="log_in.html";
	}else{
		//发送ajax请求
		$.ajax({
			url:base_path+"/book/loadNotebook.do",
			type:"post",
			data:{"userId":userId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					var books=result.data;
					for(var i=0;i<books.length;i++){
						var bookId=books[i].cn_notebook_id;//获取笔记本ID
						var bookName=books[i].cn_notebook_name;//获取笔记本名称
						//构建列表元素
						createBookLi(bookId,bookName)
					}
				}
			},
			error:function(){
			alert("加载笔记本列表异常");
			}
		})
	}	
}
//封装创建笔记本Li方法
function createBookLi(bookId,bookName){
	var sli="";
	sli+='<li class="online">';
	sli+=  '<a>';
	sli+=    '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli+=    '</i>'+bookName
	sli+=  '</a>';
	sli+='</li>';
var $li=$(sli);
	$li.data("bookId",bookId);
	$("#book_ul").append($li);
}