package mumsched.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import mumsched.dao.BlockDao;
import mumsched.model.Block;
import mumsched.service.BlockService;


@Service
public class BlockServiceImp implements BlockService {
	@Autowired
	BlockDao blockDao;
	
	@Override
	public void save(Block block) {
		blockDao.save(block);
		return;
	}

	@Override
	public Block getBlockByBlockID(Long blockid) {
		// TODO Auto-generated method stub
		return blockDao.findBlockByBlockID(blockid);
	}

	@Override
	public Block getBlockBlockName(String blockname) {
		// TODO Auto-generated method stub
		return blockDao.findBlockByBlockName(blockname);
	}

	@Override
	public List<Block> getAllBlocks() {
		// TODO Auto-generated method stub
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "orderNumber"));
		return blockDao.findAll(sort);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		blockDao.deleteById(id);
	}

}
