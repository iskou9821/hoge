package local.iskou9821.spring.sample;

import local.iskou9821.spring.sample.bean.HogeBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test01 {
	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		HogeBean hoge = (HogeBean)ctx.getBean("hoge");
		System.out.println(hoge.getMsg());
	}
}
