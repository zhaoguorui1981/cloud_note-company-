package cn.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.Note;
@Repository("noteDAO")
public interface NoteDAO {
	public int batchDelete(String[] ids);
	public int dynamicUpdate(Note note);
	public List<Map> findNoteByBookId(String bookId);
	public Note findNoteByNotId(String noteId);
	//public int updateNote(Note note);
	public int saveNote(Note note);
//	public int updateStatus(String noteId);
//	public int updateBookId(Map map);
//	public int updateNoteTypeId(Note note);
	public List<Note> findNotes(Map params);
}
