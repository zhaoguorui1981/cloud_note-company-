package cloud_note.test.dao;

import org.junit.Test;

import cloud_note.test.init.BaseTest;
import cn.tedu.cloudnote.dao.NoteDAO;

public class TestBatchDelete extends BaseTest{
	@Test
	public void test(){
		NoteDAO dao=ac.getBean("noteDAO",NoteDAO.class);
		String[] ids={"ss19055-30e8-4cdc-bfac-97c6bad9518f","ffc2cf21-78ed-4647-adb4-3e545613ef26","fsaf-as-df-asdf-as-df-dsa"};
		int rows=dao.batchDelete(ids);
		System.out.println("删除的记录行数"+rows);
	}
}
