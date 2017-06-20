package cn.tedu.cloudnote.dao;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.Share;

@Repository("shareDAO")
public interface ShareDAO {
	public int saveShareNote(Share snote);
	public Share findNoteByKeyword(String keyword);
}
