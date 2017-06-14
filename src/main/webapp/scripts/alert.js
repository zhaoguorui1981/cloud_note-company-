/**
 * alert.js封装对话框处理
 */
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