package cn.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.Share;

@Repository("shareDAO")
public interface ShareDAO {
	public int saveShareNote(Share snote);
	public List<Share> findNoteByKeyword(Map map);
	public Share findShareNoteById(String shareId);
}
