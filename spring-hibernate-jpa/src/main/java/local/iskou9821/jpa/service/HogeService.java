package local.iskou9821.jpa.service;

import java.util.List;

import local.iskou9821.jpa.model.Hoge;

public interface HogeService {
	public List<Hoge> findAll();
	public List<Hoge> findByComment(String comment);
	public Hoge loadById(long id);
	public Hoge save(Hoge hoge);
}
