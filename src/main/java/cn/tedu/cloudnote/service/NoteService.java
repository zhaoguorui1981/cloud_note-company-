package cn.tedu.cloudnote.service;
 
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.NoteDAO;
import cn.tedu.cloudnote.util.NoteResult;
public interface NoteService {
	public NoteResult searchNotes(String title,String status,String beginStr,String endStr);
	public NoteResult loadNotes(String bookId);
	public NoteResult loadNote(String noteId);
	public NoteResult updateNote(String noteId,String body,String title);
	public NoteResult addNote(String userId,String title,String bookId);
	public NoteResult deleteNote(String noteId);
	public NoteResult moveNote(String noteId,String bookId);
	public NoteResult shareNote(String noteId);
	public NoteResult searchShareNote(String keyword,int page);
	public NoteResult loadShare(String shareId);
}
