package mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mumsched.model.Block;
import mumsched.model.Section;
@Repository
public interface SectionDao extends JpaRepository<Section, Long> {
	
	@Query("select s from Section s where s.id= :id")
	public Section geSectionBySectionID(@Param("id")Long id);
	
	@Query("select s from Section s where s.block= :block")
	public Section getSectionBySectionBlockID(@Param("block") Block block);
	
	@Query("select s from Section s")
	public List<Section> getAllSections();
}
