package cn.tedu.cloudnote.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.NoteBookDAO;
import cn.tedu.cloudnote.entity.NoteBook;
import cn.tedu.cloudnote.util.NoteResult;
@Service("notebookService")
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

}
