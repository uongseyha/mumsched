package mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mumsched.model.Entry;

@Repository
public interface EntryDao extends JpaRepository<Entry, Long>{
	@Query("select s from Entry s where s.id= :id")
	public Entry findEntryByEntryID(@Param("id") Long id);
	
	@Query("select s from Entry s where s.entryName= :entryName")
	public Entry findEntryByEntryName(@Param("entryName") String entryName);
	
	@Query("select s from Entry s")
	public List<Entry> getAllEntry();
}
