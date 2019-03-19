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
import mumsched.service.CourseService;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CourseController {
	
	@Autowired
	CourseService courseService;

	//==== 1. Course List Form ====
	@SuppressWarnings("deprecation")
	@RequestMapping(value={"/course"})
	public String showCourseList(Model model, 
			@RequestParam("page") Optional<Integer> page, 
		      @RequestParam("size") Optional<Integer> size) {
		
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
 
        Page<Course> courseList = courseService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("courseList", courseList);
 
        int totalPages = courseList.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        
        return "course/course";
    }

	//==== 2. Create new Course Form ====
	@RequestMapping(value={"/course/add"},method=RequestMethod.GET)
    public String AddCourse(@ModelAttribute("newCourse") Course course, Model model) {
		
		model.addAttribute("newCourse", course);
 		return "course/addCourse";
    }
	
	//==== 3. Save add new Course ====
	@RequestMapping(value={"/course/save"}, method = RequestMethod.POST)
    public String saveCourse(@ModelAttribute("newCourse") Course course, Model model) {
		
		courseService.save(course);

 		return "redirect:/course";
    }
	
	//=== 4. Edit Form ====
	@RequestMapping(value = "/course/edit/{id}")
	public String editEntry(@PathVariable Long id, Model model) {
		Course course = courseService.getCourseById(id);
		model.addAttribute("course", course);
		return "course/editCourse";
	}
	
	//=== 5. Save Edit ====
	@RequestMapping(value = "/course/edit/save", method = RequestMethod.POST)
	public String SaveEditCourse(@ModelAttribute("course") Course course) {
		Course entity=courseService.getCourseById(course.getId());
		entity.setCourseTitle(course.getCourseTitle());
		entity.setCourseNumber(course.getCourseNumber());
		entity.setCourseLevel(course.getCourseLevel());
		entity.setCourseCode(course.getCourseCode());
		courseService.save(entity);
		
		return "redirect:/course";
	}
	
	//=== 6. Remove ====
	@RequestMapping(value = "/course/delete/{id}")
	public String deleteEntry(@PathVariable Long id) {
		courseService.deleteById(id);

		return "redirect:/course";
	}
	
}
