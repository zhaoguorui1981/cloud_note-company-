//分享笔记
function shareNote(){
	//获取请求参数
	var $li=$('#note_ul a.checked').parent();
	var noteId=$li.data('noteId');
	//校验请求参数
	var ok=true;
	if(!noteId){
		alert('请选择笔记');
		ok=false;
	}
	//校验成功,发送ajax请求
	if(ok){
		$.ajax({
			url:base_path+"/note/share.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status!=1){
					closeAlertWindow();
					var noteTitle=$li.text();
					var $sli=createShareNoteLi(noteTitle, noteId);
					$li.after($sli);
					$li.remove();
					alert(result.msg);
				}
			},
			error:function(){alert("笔记分享异常")}
		})
	}
}
			
//移动笔记至其他笔记本
function moveNote(){
	//获取请求参数
	var $li=$('#note_ul a.checked').parent()
	var noteId=$li.data('noteId');
	var bookId=$('#moveSelect option:selected').data('bookId');
	var ok=true;
	if(!noteId){
		alert("请选择笔记");
		ok=false;
	}
	if(!bookId){
		$('#moveNote_msg').html('请选择笔记本').css({"color":"red"});

		ok=false;
	}
	//发送ajax请求
	if(ok){
		$.ajax({
			url:base_path+"/note/move.do",
			type:"post",
			data:{"noteId":noteId,"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					closeAlertWindow();
					$li.remove();
				}
			},
			error:function(){alert("移动异常")},
		})
	}
}

//将笔记移动至回收站
function deleteNote(){
	//获取请求参数
	var noteId=$('#note_ul a.checked').parent().data('noteId');
	//校验请求参数
	var ok=true;
	if(!noteId){
		alert('请选择笔记本');
		ok=false;
	}
	if(ok){
		$.ajax({
			url:base_path+"/note/delete.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					$('#note_ul a.checked').parent().remove();
					alert(result.msg);
				}
				
			},
			error:function(){alert("删除笔记异常")}
		})
	}
}
//隐藏笔记菜单
function hideNoteMenu(){
	$('#note_ul div').hide()
}
//弹出笔记操作菜单
function showNoteMenu(){
	$('#note_ul div').hide();
	var $menu=$(this).parent().next();
	$menu.slideDown(500);
	//高亮选中LI
	$('#note_ul a').removeClass();
	$(this).parent().addClass("checked")
	//防止冒泡至BODY
	return false;
			
}
//创建笔记
function addNote(){
	//获取请参数
	var title=$('#input_note').val().trim();
	var userId=getCookie('uid');
	var bookId=$('#book_ul a.checked').parent().data("bookId");
	//校验请求参数
	var ok=true;
	if(!title){//题目不能为空
		$('#createnote_msg').html("请输入笔记标题").css({"color":"red"});
		ok=false;
	}
	if(!notebookId){//笔记本ID不能为空
		alert("请选择笔记本")
		ok=false;
	}
	if(!userId){//用户ID不能为空
		ok=false;
		alert("服务器繁忙,请重新登录");
		window.location.href="log_in.html";
	}
	var lis=$('#note_ul').find('li');
	for (var i = 0; i < lis.length; i++) {
		var existTitle=$(lis[i]).text().trim();
		
		if(title==existTitle){
			$('#createnote_msg').html("笔记重名,请更换名称").css({"color":"red"});
			ok=false;
			break;
		}
	}
	//发送ajax请求
	if(ok){
		$.ajax({
			url:base_path+"/note/add.do",
			type:"post",
			data:{"userId":userId,"title":title,"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//关闭对话框
					closeAlertWindow();
					//生成笔记列表li
					var noteId=result.data.cn_note_id;
					var title=result.data.cn_note_title;
					createNoteLi(title,noteId);
					//弹出提示
					alert(result.msg);
				}
			},
			error:function(){alert("笔记添加失败")}
		})
	}
}

//更新笔记
function updateNote(){
	//获取请求参数
	var title=$('#input_note_title').val();
	var body=um.getContent();
	var noteId=$('#note_ul a.checked').parent().data("noteId");
	//校验请求参数
	if(!noteId){
		alert("请选择笔记")
	}else if(!title){
		$('#note__msg').html("笔记标题为空").css({"color":"red","font-size":"10px"})
	}else{
		$.ajax({
			url:base_path+"/note/update.do",
			type:"post",
			data:{"title":title,"body":body,"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					alert(result.msg)
					var sli="";
					sli+='		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					sli+=title;
					sli+='		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					$('#note_ul').find('a.checked').html(sli);
				}else{
					alert(result.msg);
				}
			},
			error:function(){
				alert("笔记更新异常")
			}
		})
	}
}
//载入笔记列表
function loadBookNotes(){
	//获得请求参数
	$("#book_ul").find("a").removeClass();
	var bookId=$(this).data("bookId");
	$(this).children("a").addClass("checked");
	//参数检查(如果是用户输入或者可能失效的需要检查,否则可以忽略)
	//发送ajax请求
	$.ajax({
		url:base_path+"/note/loadnotes.do",
		type:"post",
		data:{"bookId":bookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var notes=result.data;
				$("#note_ul").empty()
					
				for (var i = 0; i < notes.length; i++) {
					//获取笔记ID,标题和分享类型号
					var noteId=notes[i].cn_note_id;
					var noteTitle=notes[i].cn_note_title;
					var noteTypeId=notes[i].cn_note_type_id;
					if(noteTypeId=='2'){
						var $li=createShareNoteLi(noteTitle,noteId);
						$("#note_ul").append($li);
					}else{
					//创建笔记li元素
						createNoteLi(noteTitle,noteId);
					}
				}
			}		
		},
		error:function(){
			alert("加载笔记列表异常")
		}
	})

}
//根据笔记ID加载笔记信息
function loadNotes(){
	var noteId=$(this).data("noteId");
	$('#note_ul').find('a').removeClass();
	$(this).find('a').addClass("checked");
	$.ajax({
		url:base_path+"/note/load.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var title=result.data.cn_note_title;//获取笔记标题
				var content=result.data.cn_note_body;//获取笔记内容
				//设置编辑区域
				$('#input_note_title').val(title);
				if(content==null){
					um.setContent("");
				}else{
					um.setContent(content);
				}
			}
		},
		error:function(){alert("笔记内容加载异常")}
	})
}
//封装创建笔记Li方法
function createNoteLi(noteTitle,noteId){
	var sli="";
	sli+='<li class="online">';
	sli+='	<a>';
	sli+='		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli+=				noteTitle;
	sli+='		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	sli+='	</a>';
	sli+='	<div class="note_menu" tabindex="-1">';
	sli+='		<dl>';
	sli+='			<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli+='			<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli+='			<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli+='		</dl>';
	sli+='	</div>';
	sli+='</li>';
	var $li=$(sli);
		$li.data("noteId",noteId);
	$("#note_ul").append($li);
}
//封装创建分享笔记Li方法
function createShareNoteLi(noteTitle,noteId){
	var sli="";
	sli+='<li class="online">';
	sli+='	<a>';
	sli+='		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli+=noteTitle+'<i class="fa fa-sitemap"></i>';
	sli+='		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	sli+='	</a>';
	sli+='	<div class="note_menu" tabindex="-1">';
	sli+='		<dl>';
	sli+='			<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli+='			<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli+='			<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli+='		</dl>';
	sli+='	</div>';
	sli+='</li>';
	var $li=$(sli);
		$li.data("noteId",noteId);
	return $li;
}