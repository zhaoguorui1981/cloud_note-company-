package cn.tedu.cloudnote.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.CnUserService;
import cn.tedu.cloudnote.util.NoteResult;
@Controller
@RequestMapping("/user")
public class CnUserLoginController {
	@Resource(name="CnUserService")
	private CnUserService cus;
	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult execute(String account,String password){
		NoteResult nr=cus.checkLogin(account, password);
		return nr;
	}
}
