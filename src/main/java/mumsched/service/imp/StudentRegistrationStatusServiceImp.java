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
import mumsched.dao.StudentRegistrationStatusDao;
import mumsched.model.StudentRegistrationStatus;
import mumsched.service.StudentRegistrationStatusService;

@Service
public class StudentRegistrationStatusServiceImp implements StudentRegistrationStatusService  {
	
	//@Autowired (required=false)
	@Autowired
	StudentRegistrationStatusDao studentRegistrationStatusDAO;
	
	public void save(StudentRegistrationStatus studentRegistrationStatus){
		studentRegistrationStatusDAO.save(studentRegistrationStatus);
		return ;
	}

	@Override
	public StudentRegistrationStatus getStudentRegistrationStatusById(Long studentRegistrationStatusId) {
		return studentRegistrationStatusDAO.findStudentRegistrationStatusById(studentRegistrationStatusId);
	}
	
	@Override
	public List<StudentRegistrationStatus> getAll(){
		return studentRegistrationStatusDAO.findAll();
	}
	 
    public Page<StudentRegistrationStatus> findPaginated(Pageable pageable) {
    	List<StudentRegistrationStatus> studentRegistrationStatus = studentRegistrationStatusDAO.findAll();
    	int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<StudentRegistrationStatus> list;
 
        if (studentRegistrationStatus.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, studentRegistrationStatus.size());
            list = studentRegistrationStatus.subList(startItem, toIndex);
        }
 
        Page<StudentRegistrationStatus> bookPage
          = new PageImpl<StudentRegistrationStatus>(list, PageRequest.of(currentPage, pageSize), studentRegistrationStatus.size());
 
        return bookPage;
    }
	
}
