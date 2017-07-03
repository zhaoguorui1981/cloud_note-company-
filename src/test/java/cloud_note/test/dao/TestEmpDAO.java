package cloud_note.test.dao;

import org.junit.Test;

import cloud_note.test.init.BaseTest;
import cn.tedu.cloudnote.dao.EmpDAO;
import cn.tedu.cloudnote.entity.Emp;

public class TestEmpDAO extends BaseTest {
	@Test
	public void test(){
		EmpDAO dao=ac.getBean("empDAO",EmpDAO.class);
		Emp emp=new Emp();
		emp.setName("zgr");
		dao.save(emp);
		System.out.println("主键值:"+emp.getNo());
	}
}
