package mumsched.service.imp;

import java.util.ArrayList;
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
import mumsched.dao.FacultyCourseDao;
import mumsched.model.FacultyCourse;
import mumsched.service.FacultyCourseService;

@Service
public class FacultyCourseServiceImp implements FacultyCourseService  {
	
	//@Autowired (required=false)
	@Autowired
	FacultyCourseDao facultyCourseDAO;
	
	public void save(FacultyCourse facultyCourse){
		facultyCourseDAO.save(facultyCourse);
		return ;
	}


	@Override
	public FacultyCourse getFacultyCourseById(Long facultyCourseId) {
		return facultyCourseDAO.findFacultyCourseById(facultyCourseId);
	}
	
	@Override
	public List<FacultyCourse> getAllFacultyCourse(){
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "faculty"));
		return facultyCourseDAO.findAll(sort);
	}
	
	@Override
	public void deleteById(Long id) {
		facultyCourseDAO.deleteById(id);
	}
	 
    public Page<FacultyCourse> findPaginated(Pageable pageable) {
    	List<FacultyCourse> facultyCourse = facultyCourseDAO.findAll();
    	int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<FacultyCourse> list;
 
        if (facultyCourse.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, facultyCourse.size());
            list = facultyCourse.subList(startItem, toIndex);
        }
 
        Page<FacultyCourse> bookPage
          = new PageImpl<FacultyCourse>(list, PageRequest.of(currentPage, pageSize), facultyCourse.size());
 
        return bookPage;
    }
	
}
