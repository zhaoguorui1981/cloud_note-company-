package cloud_note.test.init;

import java.sql.SQLException;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {
	protected ApplicationContext ac;
	@Before
	public void init() throws SQLException{
		String [] co={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		String [] conf={"conf/spring-mybatis-company.xml","conf/spring-mvc-company.xml"};
		ac=new ClassPathXmlApplicationContext(conf);
	}
}
