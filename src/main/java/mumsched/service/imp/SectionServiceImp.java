package mumsched.service.imp;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import mumsched.dao.SectionDao;
import mumsched.model.Block;
import mumsched.model.Section;
import mumsched.model.Student;
import mumsched.service.SectionService;
@Service
public class SectionServiceImp implements SectionService {
	@Autowired
	SectionDao sectionDao;
	@Override
	public void save(Section section) {
		sectionDao.save(section);
		return;
	}

	@Override
	public Section getSectionBySectionId(Long id) {
		// TODO Auto-generated method stub
		return sectionDao.geSectionBySectionID(id);
	}

	@Override
	public Section geSectionBSectionBlockId(Block block) {
		// TODO Auto-generated method stub
		return sectionDao.getSectionBySectionBlockID(block);
	}

	@Override
	public List<Section> getAllSections() {
		// TODO Auto-generated method stub
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "block"));
		return sectionDao.findAll(sort);
		//return sectionDao.getAllSections();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		sectionDao.deleteById(id);
	}

	@Override
	public Page<Section> findPaginated(Pageable pageable) {
    	List<Section> section = sectionDao.getAllSections();
    	int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Section> list;
 
        if (section.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, section.size());
            list = section.subList(startItem, toIndex);
        }
 
        Page<Section> bookPage
          = new PageImpl<Section>(list, PageRequest.of(currentPage, pageSize), section.size());
 
        return bookPage;
	}

}
