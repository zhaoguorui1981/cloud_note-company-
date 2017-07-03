package cn.tedu.cloudnote.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.NoteBook;
@Repository("associationDAO")
public interface AssociationDAO {
	public List<NoteBook> findAllBook();
	public NoteBook findById(String bookId);
}
