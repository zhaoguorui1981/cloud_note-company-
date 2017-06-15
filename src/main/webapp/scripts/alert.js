/**
 * alert.js封装对话框处理
 */
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