package mumsched.service.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import mumsched.dao.StudentRegistrationDao;
import mumsched.model.StudentRegistration;
import mumsched.service.StudentRegistrationService;

@Service
public class StudentRegistrationServiceImp implements StudentRegistrationService  {
	
	//@Autowired (required=false)
	@Autowired
	StudentRegistrationDao studentRegistrationDAO;
	
	public void save(StudentRegistration studentRegistration){
		studentRegistrationDAO.save(studentRegistration);
		return ;
	}

	@Override
	public StudentRegistration getStudentRegistrationById(Long studentRegistrationId) {
		return studentRegistrationDAO.findStudentRegistrationById(studentRegistrationId);
	}
	
	@Override
	public List<StudentRegistration> getAllStudentRegistration(){
		return studentRegistrationDAO.findAll();
	}
	
	@Override
	public void deleteById(Long id) {
		studentRegistrationDAO.deleteById(id);
	}
	 
    public Page<StudentRegistration> findPaginated(Pageable pageable) {
    	List<StudentRegistration> studentRegistration = studentRegistrationDAO.findAll();
    	
//    	List<StudentRegistration> listReg = studentRegistrationDAO.findAll();
//    	List<StudentRegistration> studentRegistration=listReg.stream().filter(x -> x.getStudent().getId()==1).collect(Collectors.toList());

    	int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<StudentRegistration> list;
 
        if (studentRegistration.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, studentRegistration.size());
            list = studentRegistration.subList(startItem, toIndex);
        }
 
        Page<StudentRegistration> bookPage
          = new PageImpl<StudentRegistration>(list, PageRequest.of(currentPage, pageSize), studentRegistration.size());
 
        return bookPage;
    }
	
}
