package mumsched.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mumsched.model.StudentRegistration;

public interface StudentRegistrationService   {
	public void save(StudentRegistration studentRegistration);
	public StudentRegistration getStudentRegistrationById(Long id);  
	public List<StudentRegistration> getAllStudentRegistration();
	public Page<StudentRegistration> findPaginated(Pageable pageable);
	public void deleteById(Long id);
}
