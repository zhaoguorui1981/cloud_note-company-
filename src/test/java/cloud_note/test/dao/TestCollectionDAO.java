package cloud_note.test.dao;

import java.util.List;

import org.junit.Test;

import cloud_note.test.init.BaseTest;
import cn.tedu.cloudnote.dao.CollectionDAO;
import cn.tedu.cloudnote.entity.CnUser;
import cn.tedu.cloudnote.entity.NoteBook;

public class TestCollectionDAO extends BaseTest {
	@Test
	public void test2(){
		CollectionDAO dao=ac.getBean("collectionDAO",CollectionDAO.class);
		List<CnUser> list=dao.findAllUser();
		for(CnUser u:list){
			String bookname="";
			for(NoteBook b:u.getBooks()){
				bookname+=b.getCn_notebook_name();
				bookname+=",";
			}
			if(bookname.length()>0){
				bookname=bookname.substring(0, bookname.length()-1);
			}
			System.out.print("用户名:"+u.getCn_user_name()+"\t");
			System.out.println("笔记本数量:"+u.getBooks().size()+"笔记本名字:"+bookname);
		}
	}
	
	@Test
	public void test(){
		CollectionDAO dao=ac.getBean("collectionDAO",CollectionDAO.class);
		CnUser user=dao.findById("");                                          
	}
}
