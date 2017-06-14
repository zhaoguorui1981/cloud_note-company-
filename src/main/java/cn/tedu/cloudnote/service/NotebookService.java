package cn.tedu.cloudnote.service;

import cn.tedu.cloudnote.util.NoteResult;

public interface NotebookService {
	public NoteResult loadUserNotebooks(String userId);
	public NoteResult addNotebook(String userId,String name);
}
