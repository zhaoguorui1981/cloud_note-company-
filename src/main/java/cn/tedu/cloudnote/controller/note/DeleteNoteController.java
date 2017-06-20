package cn.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NoteService;
import cn.tedu.cloudnote.util.NoteResult;

@Controller
public class DeleteNoteController {
	@Resource(name="noteService")
	private NoteService noteService;
	@RequestMapping("/note/delete.do")
	@ResponseBody
	public NoteResult execute(String noteId){
		NoteResult nr=noteService.deleteNote(noteId);
		return nr;
	}
}
