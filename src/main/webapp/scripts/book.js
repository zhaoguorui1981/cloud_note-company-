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
				}
			},
			error:function(){
			alert("加载笔记本列表异常");
			}
		})
	}	
}