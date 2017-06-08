package cn.tedu.cloudnote.dao;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.CnUser;
public interface CnUserDAO {
	public CnUser findByCnUserName(String name);
	
}
