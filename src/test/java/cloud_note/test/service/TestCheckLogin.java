package cloud_note.test.service;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cloud_note.test.init.BaseTest;
import cn.tedu.cloudnote.service.CnUserService;
import cn.tedu.cloudnote.util.NoteResult;

public class TestCheckLogin extends BaseTest{
	CnUserService cus=ac.getBean("CnUserService",CnUserService.class);;
	@Test
	public void test(){	
		NoteResult nr=cus.checkLogin("zhouj", "1234");
		System.out.println(nr);
		NoteResult nr1=cus.checkLogin("zgr", "1234");
		System.out.println(nr1);
	}
	@Test 
	public void test2(){	
		NoteResult nr=cus.addUser("zhaoguorui", "zgr", "123456");
		NoteResult nr1=cus.addUser("liuxiaofeng", "lxf", "123123456");
		System.out.println(nr);
		System.out.println(nr1);
	}
}
