package cloud_note.test.dao;

import java.util.List;

import org.junit.Test;

import cloud_note.test.init.BaseTest;
import cn.tedu.cloudnote.dao.AssociationDAO;
import cn.tedu.cloudnote.entity.NoteBook;

public class TestAssociationDao extends BaseTest {
	@Test
	public void test(){
	AssociationDAO dao=ac.getBean("associationDAO",AssociationDAO.class);
//	NoteBook book=dao.findById("e46239d6-4f54-426c-a448-f7a0d45f9425");
	List<NoteBook> list=dao.findAllBook();
	int n=0;
		for(NoteBook book:list){
			if(book.getUser()!=null){
		System.out.print("笔记本名:"+book.getCn_notebook_name()+"\t"+"\t");
		System.out.print("创建时间:"+book.getCn_notebook_createtime()+"\t");
		System.out.print("状态标号:"+book.getCn_notebook_type_id()+"\t");
		System.out.print("所属用户名"+book.getUser().getCn_user_name()+"\n");
		n++;
		}
		}
		System.out.println("共有记录条数:"+n);
	}
}
