package cloud_note.test.dao;

import java.util.List;

import org.junit.Test;

import cloud_note.test.init.BaseTest;
import cn.tedu.cloudnote.dao.NoteBookDAO;
import cn.tedu.cloudnote.entity.NoteBook;

public class TestNoteBookDAO extends BaseTest {
	
	
	@Test
	public void test1(){
		NoteBookDAO dao=ac.getBean("NoteBookDAO",NoteBookDAO.class);
		System.out.println(1);
		List<NoteBook> nb=dao.findBookByUserName("demo");
		for(NoteBook b:nb){
			System.out.println(b);	
		}
		
	}
}
