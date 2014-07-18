package local.iskou9821.hibernate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import local.iskou9821.hibernate.model.Hoge;

public class HogeTest extends AbstractJPATest {
	public void testInsert() {
		emmgr.getTransaction().begin();
		try {
			Hoge hoge = new Hoge();
			hoge.setMsg("hello, hoge");
			hoge.setComment("hogehoge");
			
			emmgr.persist(hoge);
		} finally {
			emmgr.getTransaction().commit();
		}
	}
	
	public void testSelectAll() {
		emmgr.getTransaction().begin();
		try {
			CriteriaQuery<Hoge> q = emmgr.getCriteriaBuilder().createQuery(Hoge.class);
			q.select(q.from(Hoge.class));
			
			for (Hoge hoge : emmgr.createQuery(q).getResultList()) {
				System.out.println(hoge);
			}
		} finally {
			emmgr.getTransaction().commit();
		}
	}
	
	public void testSelectEqual() {
		emmgr.getTransaction().begin();
		try {
			CriteriaBuilder cb = emmgr.getCriteriaBuilder();
			CriteriaQuery<Hoge> cq = cb.createQuery(Hoge.class);
			Root<Hoge> r = cq.from(Hoge.class);
			cq.select(r).where(cb.equal(r.get("comment"), "hogehoge"));
			
			for (Hoge hoge : emmgr.createQuery(cq).getResultList()) {
				System.out.println(hoge);
			}
		} finally {
			emmgr.getTransaction().commit();
		}
	}
	
	public void testUpdate() {
		emmgr.getTransaction().begin();
		try {
			CriteriaBuilder cb = emmgr.getCriteriaBuilder();
			CriteriaQuery<Hoge> cq = cb.createQuery(Hoge.class);
			Root<Hoge> r = cq.from(Hoge.class);
			cq.select(r).where(cb.equal(r.get("id"), 1));
			
			Hoge hoge = emmgr.createQuery(cq).getSingleResult();
			hoge.setMsg("hello, world - again -");
			
			emmgr.persist(hoge);
		} finally {
			emmgr.getTransaction().commit();
		}
	}
	
	public void testDelete() {
		emmgr.getTransaction().begin();
		try {
			CriteriaBuilder cb = emmgr.getCriteriaBuilder();
			CriteriaQuery<Hoge> cq = cb.createQuery(Hoge.class);
			Root<Hoge> r = cq.from(Hoge.class);
			cq.select(r).where(cb.equal(r.get("id"), 1));
			
			Hoge hoge = emmgr.createQuery(cq).getSingleResult();
			
			emmgr.remove(hoge);
		} finally {
			emmgr.getTransaction().commit();
		}		
	}
}
