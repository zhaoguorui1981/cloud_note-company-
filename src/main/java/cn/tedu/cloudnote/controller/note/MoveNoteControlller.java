package cn.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NoteService;
import cn.tedu.cloudnote.util.NoteResult;

@Controller
public class MoveNoteControlller {
	@Resource(name="noteService")
	private NoteService noteService;
	@RequestMapping("/note/move.do")
	@ResponseBody
	public NoteResult execute(String noteId,String bookId){
		NoteResult nr=noteService.moveNote(noteId, bookId);
		return nr;
	}
}
