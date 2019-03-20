package mumsched.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mumsched.model.Student;

public interface StudentService   {
	public void save(Student student);
	public Student getStudentById(Long id);
	public List<Student> getStudentListById(int id);
	public Student getStudentByEmail(String email);	   
	public List<Student> getAllStudent();
	public Page<Student> findPaginated(Pageable pageable);
	public void deleteById(Long id);
}
