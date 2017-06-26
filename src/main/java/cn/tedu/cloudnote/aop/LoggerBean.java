package cn.tedu.cloudnote.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerBean {
	@Before("within(cn.tedu.cloudnote.controller..*)")
	public void loggerController(){
		System.out.println("进入Controller组件处理");
	}
}
