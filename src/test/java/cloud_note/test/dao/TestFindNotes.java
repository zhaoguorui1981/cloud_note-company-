package cloud_note.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cloud_note.test.init.BaseTest;
import cn.tedu.cloudnote.dao.NoteDAO;
import cn.tedu.cloudnote.entity.Note;

public class TestFindNotes extends BaseTest {
	@Test
	public void test1(){
		NoteDAO noteDao=ac.getBean("noteDAO",NoteDAO.class);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("title", "%测试%");
		params.put("status", "2");
		params.put("begin", "TO_DATE(2014-06-25 YYYY-MM-DD)");
		List<Note> list=noteDao.findNotes(params);
		for(Note n:list){
			System.out.println(n);
		}
		System.out.println(list.size());
	}
}
