package cn.tedu.cloudnote.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.CnUserDAO;
import cn.tedu.cloudnote.entity.CnUser;
import cn.tedu.cloudnote.util.NoteException;
import cn.tedu.cloudnote.util.NoteResult;
import cn.tedu.cloudnote.util.NoteUtil;
@Service("cnUserService")
public class CnUserServiceMysqlImpl implements CnUserService{
	@Resource(name="cnUserDAO")
	private CnUserDAO dao;
	public NoteResult checkLogin(String account,String password){
		NoteResult nr=new NoteResult();
		CnUser user=new CnUser();
		user=dao.findByCnUserName(account);
		//判断用户名
		String md5_pwd=null;
		try {
			md5_pwd=NoteUtil.md5(password);
		} catch (Exception e) {
			throw new NoteException("密码加密异常", e);
		}
	
		if(user==null){
			nr.setStatus(1);
			nr.setMsg("用户名不存在");
		}else if(!user.getCn_user_password().equals(md5_pwd)){
			nr.setStatus(2);
			nr.setMsg("密码错误");
		}else{
			nr.setStatus(0);;
			nr.setMsg("登陆成功");
			user.setCn_user_password("");//清空密码,返回信息不包括密码
			nr.setData(user); //返回user信息
		}
		return nr;
	}
	public NoteResult addUser(String account, String nick, String password) {
		NoteResult nr=new NoteResult();
		if(dao.findByCnUserName(account)!=null){
			nr.setStatus(1);
			nr.setMsg("用户名已被占用");
			return nr;
		}else{
			CnUser user=new CnUser();
			user.setCn_user_name(account);
			user.setCn_user_nick(nick);
			String md5_pwd=null;
			try {
				 md5_pwd=NoteUtil.md5(password);
			} catch (Exception e) {
				throw new NoteException("加密异常", e);
			}
			user.setCn_user_password(md5_pwd);
			user.setCn_user_id(NoteUtil.createId());
			dao.save(user);
			
			nr.setStatus(0);
			nr.setMsg("注册成功");
			return nr;
		 }
	}

}
