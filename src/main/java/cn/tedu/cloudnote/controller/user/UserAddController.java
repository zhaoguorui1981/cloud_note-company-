package cn.tedu.cloudnote.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.CnUserService;
import cn.tedu.cloudnote.util.NoteResult;
@Controller
@RequestMapping("/user")
public class UserAddController {
	@Resource(name="cnUserService")
	private CnUserService cnuserservice;
	@RequestMapping("/regist.do")
	@ResponseBody
	public NoteResult execute(String name,String password,String nick){
		NoteResult nr=cnuserservice.addUser(name, nick, password);
		return nr;
	}
}
