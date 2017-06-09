package cloud_note.test.init;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.dao.NoteBookDAO;
import cn.tedu.cloudnote.entity.NoteBook;

public class BaseTest {
	protected ApplicationContext ac;
	@Before
	public void init() throws SQLException{
		String [] co={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		String [] conf={"conf/spring-mybatis-company.xml","conf/spring-mvc-company.xml"};
		ac=new ClassPathXmlApplicationContext(conf);
	}
//	@Test
//	public void test(){
//		NoteBookDAO dao=ac.getBean("NoteBookDAO",NoteBookDAO.class);
//		NoteBook us=dao.findBookByUserName("demo");
//		System.out.println(us);
//	}
}
