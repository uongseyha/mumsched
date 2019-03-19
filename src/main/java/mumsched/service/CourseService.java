package mumsched.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mumsched.model.Course;

public interface CourseService   {
	public void save(Course course);
	public Course getCourseById(Long id);	   
	public List<Course> getAllCourse();
	public Page<Course> findPaginated(Pageable pageable);
	public void deleteById(Long id);
}
