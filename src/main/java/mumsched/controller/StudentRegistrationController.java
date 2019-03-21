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

import mumsched.model.Block;
import mumsched.model.Course;
import mumsched.model.Faculty;
import mumsched.model.Section;
import mumsched.model.Student;
import mumsched.model.StudentRegistration;
import mumsched.model.StudentRegistrationStatus;
import mumsched.service.BlockService;
import mumsched.service.FacultyCourseService;
import mumsched.service.SectionService;
import mumsched.service.StudentRegistrationService;
import mumsched.service.StudentRegistrationStatusService;
import mumsched.service.StudentService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class StudentRegistrationController {
	
	@Autowired
	StudentRegistrationService studentRegistrationService;
	
	@Autowired
	SectionService sectionService;
	
	@Autowired
	FacultyCourseService facultyCourseService;

	@Autowired
	BlockService blockService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentRegistrationStatusService statusService;
	
	//==== 1. StudentRegistration List Form ====
	@SuppressWarnings("deprecation")
	@RequestMapping(value={"/studentRegistration"})
	public String showStudentRegistrationList(Model model, 
			@RequestParam("page") Optional<Integer> page, 
		      @RequestParam("size") Optional<Integer> size) {
		
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
 
        Page<StudentRegistration> studentRegistrationList = studentRegistrationService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("studentRegistrationList", studentRegistrationList);
 
        int totalPages = studentRegistrationList.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        
        return "studentRegistration/studentRegistration";
    }

	//==== 2. Create new StudentRegistration Form ====
	@RequestMapping(value={"/studentRegistration/add"},method=RequestMethod.GET)
    public String AddStudentRegistration(@ModelAttribute("newStudentRegistration") StudentRegistration studentRegistration, Model model) {
		
		//==== get Student =====
		model.addAttribute("studentList",studentService.getAllStudent());
		
		//==== get section =====
		List<StudentRegistration> listStuReg=studentRegistrationService.getAllStudentRegistration();
		List<Block> listS=listStuReg.stream()
				.map(x -> x.getSection().getBlock())
				.collect(Collectors.toList());

		List<Section> listSection=sectionService.getAllSections();
		List<Section> sectionList=listSection.stream()
				.filter(x -> !listS.contains(x.getBlock()))
				.collect(Collectors.toList());
		model.addAttribute("sectionList", sectionList);
		//model.addAttribute("sectionList", sectionService.getAllSections());
		
		//==== get Status =====
		model.addAttribute("statusList", statusService.getAll());
		
		model.addAttribute("newStudentRegistration", studentRegistration);
 		return "studentRegistration/addStudentRegistration";
    }
	
	//==== 3. Save add new StudentRegistration ====
	@RequestMapping(value={"/studentRegistration/save"}, method = RequestMethod.POST)
    public String saveStudentRegistration(@ModelAttribute("newStudentRegistration") StudentRegistration studentRegistration, Model model) {
		
		studentRegistrationService.save(studentRegistration);

 		return "redirect:/studentRegistration";
    }
	
	//=== 4. Edit Form ====
	@RequestMapping(value = "/studentRegistration/edit/{id}")
	public String editEntry(@PathVariable Long id, Model model) {
		
		model.addAttribute("studentList", studentService.getAllStudent());
		model.addAttribute("sectionList", sectionService.getAllSections());
		model.addAttribute("statusList", statusService.getAll());
		
		StudentRegistration studentRegistration = studentRegistrationService.getStudentRegistrationById(id);
		model.addAttribute("studentRegistration", studentRegistration);
		return "studentRegistration/editStudentRegistration";
	}
	
	//=== 5. Save Edit ====
	@RequestMapping(value = "/studentRegistration/edit/save", method = RequestMethod.POST)
	public String SaveEditStudentRegistration(@ModelAttribute("studentRegistration") StudentRegistration studentRegistration) {
		StudentRegistration entity=studentRegistrationService.getStudentRegistrationById(studentRegistration.getId());
		//entity.setFirstName(studentRegistration.getFirstName());
		
		//==== update data from drowdown-list ===
		Student student=studentService.getStudentById(studentRegistration.getStudent().getId());
		entity.setStudent(student);
		
		//==== update data from drowdown-list ===
		Section section=sectionService.getSectionBySectionId(studentRegistration.getSection().getId());
		entity.setSection(section);
		
		//==== update data from drowdown-list ===
		StudentRegistrationStatus status=statusService.getStudentRegistrationStatusById(studentRegistration.getStatus().getId());
		entity.setStatus(status);
		
		entity.setUpdateDate(LocalDate.now());
		studentRegistrationService.save(entity);
		
		return "redirect:/studentRegistration";
	}
	
	//=== 6. Remove ====
	@RequestMapping(value = "/studentRegistration/delete/{id}")
	public String deleteEntry(@PathVariable Long id) {
		studentRegistrationService.deleteById(id);

		return "redirect:/studentRegistration";
	}
	
	//==== 7. Confirm ===
	
	
}
