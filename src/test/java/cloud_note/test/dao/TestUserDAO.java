package cloud_note.test.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.dao.CnUserDAO;
import cn.tedu.cloudnote.entity.CnUser;

public class TestUserDAO {
	private CnUserDAO dao;
	@Before
	public void init() throws SQLException{
		String [] co={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		String [] conf={"conf/spring-mybatis-company.xml","conf/spring-mvc-company.xml"};
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		DataSource ds=ac.getBean("ds", DataSource.class);
		Connection conn=ds.getConnection();
		System.out.println(conn);
		conn.close();
		SqlSessionFactory factory=
				ac.getBean("ssfb",SqlSessionFactory.class);
		System.out.println(factory);
		dao=ac.getBean("CnUserDAO",CnUserDAO.class);
 
	}
	@Test
	public void test1() throws SQLException{
		CnUser cu=dao.findByCnUserName("zgr");
		System.out.println(cu);
	}
	@Test
	public void test2(){
		CnUser cu=new CnUser();
		cu.setCn_user_id("1");
		cu.setCn_user_name("zhaoguorui");
		cu.setCn_user_password("123456");
		cu.setCn_user_nick("zgr");
		dao.save(cu);
	}
}
