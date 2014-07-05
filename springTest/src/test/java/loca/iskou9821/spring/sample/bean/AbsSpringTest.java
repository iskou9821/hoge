package loca.iskou9821.spring.sample.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public abstract class AbsSpringTest extends TestCase {
	private ApplicationContext context;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = new ClassPathXmlApplicationContext(getContextFileName());
	}

	public ApplicationContext getContext() {
		return context;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> clazz) {
		String n = clazz.getSimpleName();
		n = n.substring(0, 1).toLowerCase() + n.substring(1, n.length());
		return (T)getContext().getBean(n);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> clazz, String beanName) {
		return (T)getContext().getBean(beanName);
	}
	
	public String getContextFileName() {
		return "applicationContext.xml";
	}
}
