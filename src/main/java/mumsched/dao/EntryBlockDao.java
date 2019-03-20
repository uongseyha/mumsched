package mumsched.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mumsched.model.EntryBlock;
@Repository
public interface EntryBlockDao extends JpaRepository<EntryBlock, Long> {
	@Query("select s from EntryBlock s where s.id= :id")
	public EntryBlock findEntryBlockById(@Param("id") Long id);
}
