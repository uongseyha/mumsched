package mumsched.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mumsched.dao.StudentDao;
import mumsched.model.Student;
import mumsched.service.StudentService;

@Service
public class StudentServiceImp implements StudentService  {
	
	//@Autowired (required=false)
	@Autowired
	StudentDao studentDAO;
	
	public void save(Student student){
		studentDAO.save(student);
		return ;
	}

	@Override
	public Student getStudentByEmail(String email) {
		return studentDAO.findStudentByEmail(email);
	}

	@Override
	public Student getStudentById(Long studentId) {
		return studentDAO.findStudentById(studentId);
		//return studentDAO.fi
	}
	
	
	@Override
	public List<Student> getAllStudent(){
		return studentDAO.getAllStudent();
		
		//return (List<Student>) studentDAO.findAll();
	}
	
}
