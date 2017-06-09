package cn.tedu.cloudnote.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.NoteBook;
@Repository("NoteBookDAO")
public interface NoteBookDAO{
	public List<NoteBook> findBookById(String id);
	public List<NoteBook> findBookByUserName(String name);
}
