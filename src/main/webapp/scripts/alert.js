/**
 * alert.js封装对话框处理
 */
//弹出移动笔记对话框
function alertMoveNoteWindow(){
	$('#can').load("alert/alert_move.html",function(){
		var lis=$('#book_ul').children('li');
		for (var i = 0; i < lis.length; i++) {
			var name=$(lis[i]).text().trim();
			var bookId=$(lis[i]).data('bookId');
			var opt='';
			opt+='<option value='+i+'>'+name+'</option>';
			var $opt=$(opt)
			$opt.data("bookId",bookId);
			console.log($opt.data('bookId'));
			$('#moveSelect').append($opt);
		}
	});
	$('.opacity_bg').show();
}
//弹出删除笔记对话框
function alertDeleteNoteWindow(){
	$('#can').load("alert/alert_delete_note.html");
	$('.opacity_bg').show();
}
//弹出重命名笔记本对话框
function renameNotebookWindow(){
	$('#can').load("alert/alert_rename.html");
	$('.opacity_bg').show();
}
//弹出创建笔记本对话框
function alertAddNotebookWindow(){
	$('#can').load("alert/alert_notebook.html");
	$('.opacity_bg').show();
}
//关闭对话框
function closeAlertWindow(){
	$('#can').empty();
	$('.opacity_bg').hide();
}

//弹出创建笔记对话框
function alertAddNoteWindow(){
	//判断是否已经选中笔记
	var flag=$('#book_ul a.checked')
	
	if(flag.length==0){
		alert("请选择笔记本")
	}else{
	$('#can').load("alert/alert_note.html");
	$('.opacity_bg').show();
    }
}