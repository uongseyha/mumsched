package mumsched.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mumsched.model.StudentRegistrationStatus;

public interface StudentRegistrationStatusService   {
	public StudentRegistrationStatus getStudentRegistrationStatusById(Long id);  
	public List<StudentRegistrationStatus> getAll();

}
