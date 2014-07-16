package local.iskou9821.jpa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import local.iskou9821.jpa.model.Hoge;
import local.iskou9821.jpa.repository.HogeRepository;
import local.iskou9821.jpa.service.HogeService;

@Service("hogeService")
@Repository
@Transactional
public class HogeServiceImpl implements HogeService {

	@Autowired
	private HogeRepository repo;
	
	@Override
	public List<Hoge> findAll() {
		return Lists.newArrayList(repo.findAll());
	}
	
	@Override
	public List<Hoge> findByComment(String comment) {
		return Lists.newArrayList(repo.findByComment(comment));
	}

	@Override
	public Hoge loadById(long id) {
		return repo.findOne(id);
	}

	@Override
	public Hoge save(Hoge hoge) {
		return repo.save(hoge);
	}

}
