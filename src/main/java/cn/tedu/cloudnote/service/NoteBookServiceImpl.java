package cn.tedu.cloudnote.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloudnote.dao.NoteBookDAO;
import cn.tedu.cloudnote.entity.NoteBook;
import cn.tedu.cloudnote.util.NoteResult;
import cn.tedu.cloudnote.util.NoteUtil;
@Service("notebookService")
@Transactional
public class NoteBookServiceImpl implements NotebookService {
	@Resource(name="noteBookDAO")
	private NoteBookDAO dao;
	public NoteResult loadUserNotebooks(String userId) {
		List<NoteBook> list=dao.findBookById(userId);
		//可以设置分支,如果查询无记录可给区别
		NoteResult nr=new NoteResult();
		nr.setStatus(0);
		nr.setMsg("查询完毕");
		nr.setData(list);
		return nr;
	}
	public NoteResult addNotebook(String userId, String name) {
		NoteBook book=new NoteBook();
		book.setCn_notebook_id(NoteUtil.createId());
		book.setCn_notebook_name(name);
		book.setCn_user_id(userId);
		Timestamp time=new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);
		book.setCn_notebook_type_id("5");
		book.setCn_notebook_desc("");
		int index=dao.saveNotebook(book);
		NoteResult nr=new NoteResult();
		if(index>0){
			nr.setStatus(0);
			nr.setMsg("添加成功");
			nr.setData(book);
		}else{
			nr.setStatus(1);
			nr.setMsg("添加失败");
		}
		return nr;
	}
	public NoteResult renameNotebook(String bookId, String name) {
		NoteBook book=new NoteBook();
		book.setCn_notebook_id(bookId);
		book.setCn_notebook_name(name);
		int index=dao.reNameByBookId(book);
		NoteResult nr=new NoteResult();
		if(index>0){
			nr.setStatus(0);
			nr.setMsg("重命名成功");
		}else{
			nr.setStatus(1);
			nr.setMsg("重命名失败");
		}
		return nr;
	}

}
