package cn.tedu.cloudnote.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.CnUserDAO;
import cn.tedu.cloudnote.entity.CnUser;
import cn.tedu.cloudnote.util.NoteResult;
@Service("CnUserService")
public class CnUserServiceMysqlImpl implements CnUserService{
	@Resource(name="CnUserDAO")
	private CnUserDAO dao;
	public NoteResult checkLogin(String account,String password){
		NoteResult nr=new NoteResult();
		CnUser user=new CnUser();
		user=dao.findByCnUserName(account);
		//判断用户名
		if(user==null){
			nr.setStatus(1);
			nr.setMsg("用户名不存在");
		}else if(!user.getCn_user_password().equals(password)){
			nr.setStatus(2);
			nr.setMsg("密码错误");
		}else{
			nr.setStatus(0);;
			nr.setMsg("登陆成功");
			nr.setData(user);
		}
		return nr;
	}
}
