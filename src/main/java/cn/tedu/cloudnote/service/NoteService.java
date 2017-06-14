package cn.tedu.cloudnote.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.NoteDAO;
import cn.tedu.cloudnote.util.NoteResult;
public interface NoteService {
	public NoteResult loadNotes(String bookId);
	public NoteResult loadNote(String noteId);
	public NoteResult updateNote(String noteId,String body,String title);
}
