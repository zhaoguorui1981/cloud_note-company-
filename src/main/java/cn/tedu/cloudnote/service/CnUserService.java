package cn.tedu.cloudnote.service;

import cn.tedu.cloudnote.util.NoteResult;

public interface CnUserService {
	public NoteResult checkLogin(String account,String password);
}