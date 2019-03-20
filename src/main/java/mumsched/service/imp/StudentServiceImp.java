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
	}
	
	@Override
	public List<Student> getAllStudent(){
		return studentDAO.findAll();
		//return studentDAO.findAllById(ids)
	}
	
	@Override
	public List<Student> getStudentListById(int id){
		return studentDAO.findAllById(id);
	}
	
	@Override
	public void deleteById(Long id) {
		studentDAO.deleteById(id);
	}
	 
    public Page<Student> findPaginated(Pageable pageable) {
    	List<Student> student = studentDAO.findAll();
    	int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Student> list;
 
        if (student.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, student.size());
            list = student.subList(startItem, toIndex);
        }
 
        Page<Student> bookPage
          = new PageImpl<Student>(list, PageRequest.of(currentPage, pageSize), student.size());
 
        return bookPage;
    }
	
}
