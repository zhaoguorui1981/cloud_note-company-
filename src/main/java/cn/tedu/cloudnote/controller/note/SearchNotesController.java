package cn.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NoteService;
import cn.tedu.cloudnote.util.NoteResult;

@Controller
public class SearchNotesController {
	@Resource(name="noteService")
	private NoteService noteService;
	@RequestMapping("/note/searchnotes.do")
	@ResponseBody
	public NoteResult execute(String title,String status,String begin,String end){
		NoteResult nr=noteService.searchNotes(title, status, begin, end);
		return nr;
	}
}
