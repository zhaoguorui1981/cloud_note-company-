package cn.tedu.cloudnote.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.NoteDAO;
import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.util.NoteResult;
@Service("noteService")
public class NoteServiceImpl implements Serializable, NoteService {
	@Resource(name="noteDAO")
	private NoteDAO dao;
	public NoteResult loadNotes(String bookId) {
		NoteResult nr=new NoteResult();
		List<Map>list=dao.findNoteByBookId(bookId);
		nr.setStatus(0);
		nr.setMsg("笔记加载成功");
		nr.setData(list);
		return nr;
	}
	public NoteResult loadNote(String noteId) {
		Note note=dao.findNoteByNotId(noteId);
		NoteResult nr=new NoteResult();
		nr.setStatus(0);
		nr.setMsg("笔记加载成功:");
		nr.setData(note);
		return nr;
	}
	public NoteResult updateNote(String noteId, String body, String title) {
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_body(body);
		note.setCn_note_title(title);
		Long time=System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		int index=dao.updateNote(note);
		NoteResult nr=new NoteResult();
		if(index>0){
			nr.setStatus(0);
			nr.setMsg("更新成功");
		}else{
			nr.setStatus(1);
			nr.setMsg("更新失败");
		}
		return nr;
	}

}
