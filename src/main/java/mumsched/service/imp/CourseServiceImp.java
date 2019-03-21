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
import mumsched.dao.CourseDao;
import mumsched.model.Course;
import mumsched.service.CourseService;

@Service
public class CourseServiceImp implements CourseService  {
	
	//@Autowired (required=false)
	@Autowired
	CourseDao courseDAO;
	
	public void save(Course course){
		courseDAO.save(course);
		return ;
	}

	@Override
	public Course getCourseById(Long courseId) {
		return courseDAO.findCourseById(courseId);
	}
	
	@Override
	public List<Course> getAllCourse(){
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "courseTitle"));
		return courseDAO.findAll(sort);
	}
	
	@Override
	public void deleteById(Long id) {
		courseDAO.deleteById(id);
	}
	 
    public Page<Course> findPaginated(Pageable pageable) {
    	Sort sort = new Sort(new Sort.Order(Direction.ASC, "courseTitle"));
    	List<Course> course = courseDAO.findAll(sort);
    	int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Course> list;
 
        if (course.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, course.size());
            list = course.subList(startItem, toIndex);
        }
 
        Page<Course> bookPage
          = new PageImpl<Course>(list, PageRequest.of(currentPage, pageSize), course.size());
 
        return bookPage;
    }
	
}
