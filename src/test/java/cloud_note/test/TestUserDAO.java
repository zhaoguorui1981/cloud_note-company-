package cloud_note.test;

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
		dao=ac.getBean("cnUserDAO",CnUserDAO.class);
 
	}
	@Test
	public void test1() throws SQLException{
		CnUser cu=dao.findByCnUserName("zgr");
		System.out.println(cu);
	}
}
