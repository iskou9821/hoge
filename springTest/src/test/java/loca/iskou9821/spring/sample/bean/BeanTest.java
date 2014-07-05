package loca.iskou9821.spring.sample.bean;

import local.iskou9821.spring.sample.bean.FugaBean;
import local.iskou9821.spring.sample.bean.HogeBean;
import local.iskou9821.spring.sample.bean.PiyoBean;


public class BeanTest extends AbsSpringTest {
	public void testBean1() {
		HogeBean hoge = getBean(HogeBean.class, "hoge");
		System.out.println(hoge.getMsg());
	}
	
	public void testBean2() {
		PiyoBean piyo = getBean(PiyoBean.class, "piyo");
		System.out.println(piyo.getMsg());
	}
	
	public void testBean3() {
		FugaBean fuga = getBean(FugaBean.class);
		System.out.println("msg:" +fuga.getMsg());
		for (HogeBean hoge : fuga.getHoges()) {
			System.out.println("hoges: " + hoge.getMsg());
		}
	}
}
