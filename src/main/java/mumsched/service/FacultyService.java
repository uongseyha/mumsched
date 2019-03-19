package mumsched.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mumsched.model.Faculty;

public interface FacultyService   {
	public void save(Faculty faculty);
	public Faculty getFacultyById(Long id);
	public Faculty getFacultyByEmail(String email);	   
	public List<Faculty> getAllFaculty();
	public Page<Faculty> findPaginated(Pageable pageable);
	public void deleteById(Long id);
}
