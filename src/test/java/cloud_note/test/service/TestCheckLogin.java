package cloud_note.test.service;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.service.CnUserService;
import cn.tedu.cloudnote.util.NoteResult;

public class TestCheckLogin {
	private ApplicationContext ac;
	@Before
	public void init() throws SQLException{
		String [] co={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		String [] conf={"conf/spring-mybatis-company.xml","conf/spring-mvc-company.xml"};
		ac=new ClassPathXmlApplicationContext(conf);
		
		
	}
	@Test
	public void test(){
		CnUserService cus=ac.getBean("CnUserService",CnUserService.class);
		NoteResult nr=cus.checkLogin("zhouj", "1234");
		System.out.println(nr);
		NoteResult nr1=cus.checkLogin("zgr", "1234");
		System.out.println(nr1);
	}
}
