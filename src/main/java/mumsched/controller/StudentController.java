package mumsched.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mumsched.model.Entry;
import mumsched.model.Faculty;
import mumsched.model.Student;
import mumsched.service.StudentService;
import mumsched.service.EntryService;
import mumsched.service.FacultyService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	FacultyService facultyService;

	@Autowired
	EntryService entryService;

	//==== 1. Student List Form ====
	@SuppressWarnings("deprecation")
	@RequestMapping(value={"/student"})
	public String showStudentList(Model model, 
			@RequestParam("page") Optional<Integer> page, 
		      @RequestParam("size") Optional<Integer> size) {
		
        //List<Student> studentList=studentService.getAllStudent();
        //model.addAttribute("studentList", studentList);
        
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
 
        Page<Student> studentList = studentService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("studentList", studentList);
 
        int totalPages = studentList.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
      
        return "student/student";
    }

	//==== 2. Create new Student Form ====
	@RequestMapping(value={"/student/add"},method=RequestMethod.GET)
    public String AddStudent(@ModelAttribute("newStudent") Student student, Model model) {
		
		//==== Get faculty for dropdown-list ===
        List<Faculty> facultyList= facultyService.getAllFaculty();
        model.addAttribute("facultyList",facultyList);
        
    	//==== Get entry for dropdown-list ===
        List<Entry> entryList= entryService.getAllEntry();
        model.addAttribute("facultyList",facultyList);
        
		model.addAttribute("newStudent", student);
 		return "student/addStudent";
    }
	
	//==== 3. Save add new Student ====
	@RequestMapping(value={"/student/save"}, method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("newStudent") Student student, Model model) {
		
		studentService.save(student);

 		return "redirect:/student";
    }
	
	//=== 4. Edit Form ====
	@RequestMapping(value = "/student/edit/{id}")
	public String editEntry(@PathVariable Long id, Model model) {
		
//		//==== Get faculty for dropdown-list ===
        List<Faculty> facultyList= facultyService.getAllFaculty();
        model.addAttribute("facultyList",facultyList);
        
      //==== Get entry for dropdown-list ===
        List<Entry> entryList= entryService.getAllEntry();
        model.addAttribute("entryList",entryList);
//        
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);
		return "student/editStudent";
	}
	
	//=== 5. Save Edit ====
	@RequestMapping(value = "/student/edit/save", method = RequestMethod.POST)
	public String SaveEditStudent(@ModelAttribute("student") Student student) {
		Student entity=studentService.getStudentById(student.getId());
		entity.setFirstName(student.getFirstName());
		entity.setLastName(student.getLastName());
		entity.setGender(student.getGender());
		entity.setPhone(student.getPhone());
		entity.setAddress(student.getAddress());
		entity.setDob(student.getDob());
		entity.setEmail(student.getEmail());
		entity.setRegistrationNumber(student.getRegistrationNumber());
		entity.setEntryDate(student.getEntryDate());
		entity.setTrack(student.getTrack());
		
		//==== update faculty from drowdown-list ===
		Faculty faculty=facultyService.getFacultyById(student.getFaculty().getId());
		entity.setFaculty(faculty);
		
		//==== update entry from drowdown-list ===
		Entry entry=entryService.getEntryByEntryID(student.getEntry().getId());
		entity.setFaculty(faculty);
		
		
		studentService.save(entity);
		
		return "redirect:/student";
	}
	
	//=== 6. Remove ====
	@RequestMapping(value = "/student/delete/{id}")
	public String deleteEntry(@PathVariable Long id) {
		studentService.deleteById(id);

		return "redirect:/student";
	}
	
	
}
