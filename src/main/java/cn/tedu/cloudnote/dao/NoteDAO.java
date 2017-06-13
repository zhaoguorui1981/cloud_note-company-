package cn.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.Note;
@Repository("noteDAO")
public interface NoteDAO {
	public List<Map> findNoteByBookId(String bookId);
	public Note findNoteByNotId(String noteId);
}
