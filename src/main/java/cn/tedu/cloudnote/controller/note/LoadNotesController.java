package cn.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NoteService;
import cn.tedu.cloudnote.util.NoteResult;

@Controller
public class LoadNotesController {
	@Resource(name="noteService")
	private NoteService noteService;
	@RequestMapping("/note/loadnotes.do")
	@ResponseBody
	public NoteResult execute(String bookId){
		NoteResult result=noteService.loadNotes(bookId);
		return result;
	}
	
}
