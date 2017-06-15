package cn.tedu.cloudnote.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.NoteBook;
@Repository("noteBookDAO")
public interface NoteBookDAO{
	public List<NoteBook> findBookById(String id);
	public List<NoteBook> findBookByUserName(String name);
	public int saveNotebook(NoteBook book);
	public int reNameByBookId(NoteBook book);
}
