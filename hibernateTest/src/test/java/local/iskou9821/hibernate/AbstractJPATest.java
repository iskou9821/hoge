package local.iskou9821.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

public abstract class AbstractJPATest extends TestCase{
	protected EntityManagerFactory factory;
	protected EntityManager emmgr;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//引数に渡すのは、META-INF/persistence.xmlで記述した「<persistence-unit name="jpa-test"」に該当する部分の文字列
		factory = Persistence.createEntityManagerFactory("jpa-test");
		emmgr = factory.createEntityManager();
	}

	@Override
	protected void tearDown() throws Exception {
		emmgr.close();
		super.tearDown();
	}
	
}
