package local.iskou9821.jpa.repository;

import java.util.List;

import local.iskou9821.jpa.model.Hoge;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HogeRepository extends CrudRepository<Hoge, Long>{
	@Query("select h from Hoge h where h.comment=:comment")
	public List<Hoge> findByComment(@Param("comment")String comment);
}
