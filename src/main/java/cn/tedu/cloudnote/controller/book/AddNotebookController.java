package cn.tedu.cloudnote.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NotebookService;
import cn.tedu.cloudnote.util.NoteResult;

@Controller
public class AddNotebookController {
	@Resource(name="notebookService")
	private NotebookService notebookservice;
	@RequestMapping("/book/add.do")
	@ResponseBody
	public NoteResult execute(String userId,String name){
		NoteResult nr=notebookservice.addNotebook(userId, name);
		return nr;
	}
}
