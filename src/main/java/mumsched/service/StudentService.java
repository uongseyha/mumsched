package mumsched.service;

import java.util.List;

import mumsched.model.Student;

public interface StudentService   {
	public void save(Student student);
	public Student getStudentById(Long id);
	public Student getStudentByEmail(String email);	   
	public List<Student> getAllStudent();
}
