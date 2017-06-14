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
//封装笔记相关操作
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
					//获取笔记ID和标题
					var noteId=notes[i].cn_note_id;
					var noteTitle=notes[i].cn_note_title;
					//创建笔记li元素
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