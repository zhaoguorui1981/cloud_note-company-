package cn.tedu.cloudnote.aop;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionBean {
	@AfterThrowing(throwing="e",pointcut="within(cn.tedu.cloudnote.controller..*)")
	public void execute(Exception e){
		try {
			FileWriter fw=new FileWriter("F:\\note_error.log",true);
			PrintWriter pw=new PrintWriter(fw);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=sdf.format(new Date());
			pw.println("************************************");
			pw.println("*发生时间:"+time);
			pw.println("*异常类型:"+e);
			pw.println("***************异常详情***************");
			e.printStackTrace(pw);
			pw.close();
			fw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("将异常信息写入文件"+e);
	}
}
