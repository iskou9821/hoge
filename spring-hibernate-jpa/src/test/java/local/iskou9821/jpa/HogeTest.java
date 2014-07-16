package local.iskou9821.jpa;

import junit.framework.TestCase;
import local.iskou9821.jpa.model.Hoge;
import local.iskou9821.jpa.service.HogeService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HogeTest extends TestCase {
	protected ApplicationContext ctx;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		ctx = new ClassPathXmlApplicationContext("applicationContext_ut.xml");
	}

	public void testInsert() {
		HogeService svc = ctx.getBean("hogeService", HogeService.class);
		Hoge hoge = new Hoge();
		hoge.setComment("hogehoge");
		hoge.setMsg("piyopiyo");
		svc.save(hoge);
	}
	
	public void testFindAll() {
		HogeService svc = ctx.getBean(HogeService.class);
		for (Hoge hoge : svc.findAll()) {
			System.out.println(hoge);
		}
	}
	
	public void testFindByComment() {
		HogeService svc = ctx.getBean(HogeService.class);
		for (Hoge hoge : svc.findByComment("hogehoge")) {
			System.out.println(hoge);
		}
	}
}
