package cn.tedu.cloudnote.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.CnUser;
@Repository("collectionDAO")
public interface CollectionDAO {
	public CnUser findById(String userid);
	public List<CnUser> findAllUser();
}
