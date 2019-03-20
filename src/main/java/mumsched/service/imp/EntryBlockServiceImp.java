package mumsched.service.imp;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mumsched.dao.EntryBlockDao;
import mumsched.model.EntryBlock;
import mumsched.model.FacultyCourse;
import mumsched.model.Section;
import mumsched.service.EntryBlockService;
@Service
public class EntryBlockServiceImp implements EntryBlockService {
	
	@Autowired
	EntryBlockDao entryBlockDao;
	
	@Override
	public void save(EntryBlock entryBlock) {
		entryBlockDao.save(entryBlock);
		return;
	}

	@Override
	public EntryBlock getEntryBlockById(Long id) {
		// TODO Auto-generated method stub
		return entryBlockDao.findEntryBlockById(id);
	}

	@Override
	public List<EntryBlock> getAllEntryBlock() {
		// TODO Auto-generated method stub
		return entryBlockDao.findAll();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		entryBlockDao.deleteById(id);
	}

	@Override
	public Page<EntryBlock> findPaginated(Pageable pageable) {
		List<EntryBlock> entryBlock = entryBlockDao.findAll();
    	int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<EntryBlock> list;
 
        if (entryBlock.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, entryBlock.size());
            list = entryBlock.subList(startItem, toIndex);
        }
 
        Page<EntryBlock> bookPage
          = new PageImpl<EntryBlock>(list, PageRequest.of(currentPage, pageSize), entryBlock.size());
 
        return bookPage;
	}

}
