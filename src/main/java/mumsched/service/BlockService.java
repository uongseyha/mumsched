package mumsched.service;

import java.util.List;

import mumsched.model.Block;


public interface BlockService {
	
	public void save(Block block);
	public Block getBlockByBlockID(Long blockid);
	public Block getBlockBlockName(String blockname);
	public List<Block> getAllBlocks();
	public void deleteById(Long id);
}
