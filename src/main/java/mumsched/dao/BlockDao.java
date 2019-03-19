package mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mumsched.model.Block;

@Repository
public interface BlockDao extends JpaRepository<Block, Long> {

	@Query("select s from Block s where s.id= :id")
	public Block findBlockByBlockID(@Param("id") Long id);
	
	@Query("select s from Block s where s.blockName= :blockName")
	public Block findBlockByBlockName(@Param("blockName") String blockName);
	
	@Query("select s from Block s")
	public List<Block> getAllBlocks();
}
