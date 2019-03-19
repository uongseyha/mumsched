package mumsched.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mumsched.model.FacultyCourse;

public interface FacultyCourseService   {
	public void save(FacultyCourse student);
	public FacultyCourse getFacultyCourseById(Long id);	   
	public List<FacultyCourse> getAllFacultyCourse();
	public Page<FacultyCourse> findPaginated(Pageable pageable);
	public void deleteById(Long id);
}
