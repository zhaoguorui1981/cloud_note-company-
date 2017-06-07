package cloud_note.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.dao.CnUserDAO;
import cn.tedu.cloudnote.entity.CnUser;

public class TestCase {
	private CnUserDAO dao;
	@Before
	public void init() throws SQLException{
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/*.xml");
		DataSource ds=ac.getBean("ds",javax.sql.DataSource.class);
		Connection conn=ds.getConnection();
		System.out.println(conn);
		dao=ac.getBean("cnUserDAO",CnUserDAO.class);

	}
	@Test
	public void test1() throws SQLException{
		CnUser cu=dao.findByCnUserName("zhouj");
		System.out.println(cu);
	}
}
