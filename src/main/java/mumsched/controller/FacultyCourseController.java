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

import mumsched.model.Course;
import mumsched.model.Faculty;
import mumsched.model.FacultyCourse;
import mumsched.service.CourseService;
import mumsched.service.FacultyCourseService;
import mumsched.service.FacultyService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class FacultyCourseController {
	
	@Autowired
	FacultyCourseService facultyCourseService;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	CourseService courseService;

	//==== 1. FacultyCourse List Form ====
	@SuppressWarnings("deprecation")
	@RequestMapping(value={"/facultyCourse"})
	public String showFacultyCourseList(Model model, 
			@RequestParam("page") Optional<Integer> page, 
		      @RequestParam("size") Optional<Integer> size) {
		
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
 
        Page<FacultyCourse> facultyCourseList = facultyCourseService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("facultyCourseList", facultyCourseList);
 
        int totalPages = facultyCourseList.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
      
        return "facultyCourse/facultyCourse";
    }

	//==== 2. Create new FacultyCourse Form ====
	@RequestMapping(value={"/facultyCourse/add"},method=RequestMethod.GET)
    public String AddFacultyCourse(@ModelAttribute("newFacultyCourse") FacultyCourse facultyCourse, Model model) {
		
		//==== Get faculty for dropdown-list ===
        List<Faculty> facultyList= facultyService.getAllFaculty();
        model.addAttribute("facultyList",facultyList);
        
        //==== Get course for dropdown-list ===
        List<Course> courseList= courseService.getAllCourse();
        model.addAttribute("courseList",courseList);
        
		model.addAttribute("newFacultyCourse", facultyCourse);
 		return "facultyCourse/addFacultyCourse";
    }
	
	//==== 3. Save add new FacultyCourse ====
	@RequestMapping(value={"/facultyCourse/save"}, method = RequestMethod.POST)
    public String saveFacultyCourse(@ModelAttribute("newFacultyCourse") FacultyCourse facultyCourse, Model model) {
		
		facultyCourseService.save(facultyCourse);

 		return "redirect:/facultyCourse";
    }
	
	//=== 4. Edit Form ====
	@RequestMapping(value = "/facultyCourse/edit/{id}")
	public String editEntry(@PathVariable Long id, Model model) {
		
//		//==== Get faculty for dropdown-list ===
        List<Faculty> facultyList= facultyService.getAllFaculty();
        model.addAttribute("facultyList",facultyList);
        
      //==== Get course for dropdown-list ===
        List<Course> courseList= courseService.getAllCourse();
        model.addAttribute("courseList",courseList);
          
		FacultyCourse facultyCourse = facultyCourseService.getFacultyCourseById(id);
		model.addAttribute("facultyCourse", facultyCourse);
		return "facultyCourse/editFacultyCourse";
	}
	
	//=== 5. Save Edit ====
	@RequestMapping(value = "/facultyCourse/edit/save", method = RequestMethod.POST)
	public String SaveEditFacultyCourse(@ModelAttribute("facultyCourse") FacultyCourse facultyCourse) {
		FacultyCourse entity=facultyCourseService.getFacultyCourseById(facultyCourse.getId());
		
		//==== update data from drowdown-list ===
		Faculty faculty=facultyService.getFacultyById(facultyCourse.getFaculty().getId());
		entity.setFaculty(faculty);
		
		//==== update data from drowdown-list ===
		Course course=courseService.getCourseById(facultyCourse.getCourse().getId());
		entity.setCourse(course);
				
		facultyCourseService.save(entity);
		
		return "redirect:/facultyCourse";
	}
	
	//=== 6. Remove ====
	@RequestMapping(value = "/facultyCourse/delete/{id}")
	public String deleteEntry(@PathVariable Long id) {
		facultyCourseService.deleteById(id);

		return "redirect:/facultyCourse";
	}

	
}
