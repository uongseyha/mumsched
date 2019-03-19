package mumsched.service.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import mumsched.dao.FacultyDao;
import mumsched.model.Faculty;
import mumsched.service.FacultyService;

@Service
public class FacultyServiceImp implements FacultyService  {
	
	//@Autowired (required=false)
	@Autowired
	FacultyDao facultyDAO;
	
	public void save(Faculty faculty){
		facultyDAO.save(faculty);
		return ;
	}

	@Override
	public Faculty getFacultyByEmail(String email) {
		return facultyDAO.findFacultyByEmail(email);
	}

	@Override
	public Faculty getFacultyById(Long facultyId) {
		return facultyDAO.findFacultyById(facultyId);
	}
	
	@Override
	public List<Faculty> getAllFaculty(){
		return facultyDAO.findAll();
	}
	
	@Override
	public void deleteById(Long id) {
		facultyDAO.deleteById(id);
	}
	 
    public Page<Faculty> findPaginated(Pageable pageable) {
    	List<Faculty> faculty = facultyDAO.findAll();
    	int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Faculty> list;
 
        if (faculty.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, faculty.size());
            list = faculty.subList(startItem, toIndex);
        }
 
        Page<Faculty> bookPage
          = new PageImpl<Faculty>(list, PageRequest.of(currentPage, pageSize), faculty.size());
 
        return bookPage;
    }
	
}
