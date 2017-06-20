package cn.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NoteService;
import cn.tedu.cloudnote.util.NoteResult;

@Controller
public class SearchNoteController {
	@Resource(name="noteService")
	private NoteService noteService;
	@RequestMapping("/note/search.do")
	@ResponseBody
	public NoteResult execute(String keyword){
		NoteResult nr=noteService.searchShareNote(keyword);
		return nr;
	}
}
