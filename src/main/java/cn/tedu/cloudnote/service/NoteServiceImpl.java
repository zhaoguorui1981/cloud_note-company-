package cn.tedu.cloudnote.service;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloudnote.dao.NoteDAO;
import cn.tedu.cloudnote.dao.ShareDAO;
import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.entity.Share;
import cn.tedu.cloudnote.util.NoteResult;
import cn.tedu.cloudnote.util.NoteUtil;
@Service("noteService")
@Transactional
public class NoteServiceImpl implements Serializable, NoteService {
	@Resource(name="noteDAO")
	private NoteDAO dao;
	@Resource(name="shareDAO")
	private ShareDAO sdao;
	public NoteResult loadNotes(String bookId) {
		NoteResult nr=new NoteResult();
		List<Map>list=dao.findNoteByBookId(bookId);
		nr.setStatus(0);
		nr.setMsg("笔记加载成功");
		nr.setData(list);
		return nr;
	}
	public NoteResult loadNote(String noteId) {
		Note note=dao.findNoteByNotId(noteId);
		NoteResult nr=new NoteResult();
		nr.setStatus(0);
		nr.setMsg("笔记加载成功:");
		nr.setData(note);
		return nr;
	}
	public NoteResult updateNote(String noteId, String body, String title) {
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_body(body);
		note.setCn_note_title(title);
		Long time=System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		int index=dao.dynamicUpdate(note);
		NoteResult nr=new NoteResult();
		if(index>0){
			nr.setStatus(0);
			nr.setMsg("更新成功");
		}else{
			nr.setStatus(1);
			nr.setMsg("更新失败");
		}
		return nr;
	}
	public NoteResult addNote(String userId, String title, String bookId) {
		Note note=new Note();
		Long time=System.currentTimeMillis();
		note.setCn_note_create_time(time);
		note.setCn_note_id(NoteUtil.createId());
		note.setCn_note_last_modify_time(time);
		note.setCn_note_title(title);
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		int index=dao.saveNote(note);
		NoteResult nr=new NoteResult();
		if(index>0){
			nr.setStatus(0);
			nr.setMsg("笔记添加成功");
			nr.setData(note);
		}else{
			nr.setStatus(1);
			nr.setMsg("笔记条件失败");
		}
		return nr;
	}
	public NoteResult deleteNote(String noteId) {
		Note note=new Note();
		note.setCn_note_id(noteId);
		NoteResult nr=new NoteResult();
		note.setCn_note_status_id("2");
		int index=dao.dynamicUpdate(note);
		if(index>0){
			nr.setStatus(0);
			nr.setMsg("删除成功");
		}else{
			nr.setStatus(1);
			nr.setMsg("删除失败");
		}
		return nr;
	}
//	public NoteResult moveNote(String noteId, String bookId) {
//		Note note=new Note();
//		note.setCn_note_id(noteId);
//		note.setCn_notebook_id(bookId);
//		int rows=dao.updateBookId(note);
//		NoteResult nr=new NoteResult();
//		if(rows>0){
//			nr.setStatus(0);
//			nr.setMsg("移动成功");
//		}else{
//			nr.setStatus(1);
//			nr.setMsg("移动失败");
//		}
//		return nr;
//	}
	public NoteResult moveNote(String noteId, String bookId) {
		 Note note=new Note();
		 note.setCn_note_id(noteId);
		 note.setCn_notebook_id(bookId);
//		Map map=new HashMap();
//		map.put("noteId", noteId);
//		map.put("bookId", bookId);
		int rows=dao.dynamicUpdate(note);
		NoteResult nr=new NoteResult();
		if(rows>0){
			nr.setStatus(0);
			nr.setMsg("移动成功");
		}else{
			nr.setStatus(1);
			nr.setMsg("移动失败");
		}
		return nr;
	}
	public NoteResult shareNote(String noteId) {
		//按照ID取出NOTE对象
		Note note=dao.findNoteByNotId(noteId);
		
		NoteResult nr=new NoteResult();
		//如果文章已经被收藏,则返回ajax提示
		if(note==null){
			nr.setStatus(1);
			nr.setMsg("文章分享失败");
			return nr;
		}
		if("2".equals(note.getCn_note_type_id())){
				nr.setStatus(2);
				nr.setMsg("文章已经被分享");
		}else{
				Share snote=new Share();
				snote.setCn_note_id(noteId);
				snote.setCn_share_body(note.getCn_note_body());
				snote.setCn_share_id(NoteUtil.createId());
				snote.setCn_share_title(note.getCn_note_title());
				int rows=sdao.saveShareNote(snote);
				if(rows>0){
					//将分享成功的文章在note表中typeid变更为2
					note.setCn_note_type_id("2");
					note.setCn_note_id(noteId);
					dao.dynamicUpdate(note);
					nr.setStatus(0);
					nr.setMsg("文章分享成功");
				}else{
					nr.setStatus(1);
					nr.setMsg("文章分享失败");
				}
		}
		return nr;
	}
	public NoteResult searchShareNote(String keyword,int page) {
		if(page<1){
			page=1;
		}
		int begin=(page-1)*5;
		String title="%";
		if(keyword!=null && !"".equals(keyword)){
			title="%"+keyword+"%";
		}	
		NoteResult nr=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("begin", begin);
		map.put("keyword",title);
		List<Share> list=sdao.findNoteByKeyword(map);
		if(list.size()==0){
			nr.setStatus(1);
			nr.setMsg("没有匹配到任何结果");
		}else{
			nr.setStatus(0);
			nr.setMsg("搜索成功");
			nr.setData(list);
		}
		return nr;
	}
	public NoteResult loadShare(String shareId) {
		Share share=sdao.findShareNoteById(shareId);
		NoteResult nr=new NoteResult();
		if(share==null){
			nr.setStatus(1);
			nr.setMsg("分享笔记加载失败");
		}else{
			nr.setStatus(0);
			nr.setMsg("分享笔记加载成功");
			nr.setData(share);
		}
		return nr;
	}
	public NoteResult searchNotes(String title, String status, String beginStr, String endStr) {
		Map<String,Object> params=new HashMap<String,Object>();
		if(title!=null && !"".equals(title)){
			params.put("title", "%"+title+"%");
		}
		if(!"0".equals(status)){
			params.put("status", status);
		}
		if(beginStr!=null && !"".equals(beginStr)){
			Date beginDate=Date.valueOf(beginStr);
			params.put("begin", beginDate.getTime());
		}
		if(endStr!=null && !"".equals(endStr)){
			Date endDate=Date.valueOf(endStr);
			params.put("end", endDate.getTime());
		}
		System.out.println(params);
		List<Note>list=dao.findNotes(params);
		NoteResult nr=new NoteResult();
		nr.setStatus(0);
		nr.setMsg("搜索完毕");
		nr.setData(list);
		return nr;
	}
	

}
