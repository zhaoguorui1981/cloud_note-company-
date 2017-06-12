package cn.tedu.cloudnote.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NotebookService;
import cn.tedu.cloudnote.util.NoteResult;

@Controller
public class LoadNotebookController {
	@Resource(name="notebookService")
	private NotebookService nbservice;
	@RequestMapping("/book/loadNotebook.do")
	@ResponseBody
	public NoteResult execute(String userId){
		NoteResult nr=nbservice.loadUserNotebooks(userId);
		return nr;
	}
}
