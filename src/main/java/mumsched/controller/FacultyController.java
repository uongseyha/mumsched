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

import mumsched.model.Faculty;
import mumsched.service.FacultyService;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class FacultyController {
	
	@Autowired
	FacultyService facultyService;

	//==== 1. Faculty List Form ====
	@SuppressWarnings("deprecation")
	@RequestMapping(value={"/faculty"})
	public String showFacultyList(Model model, 
			@RequestParam("page") Optional<Integer> page, 
		      @RequestParam("size") Optional<Integer> size) {
		
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
 
        Page<Faculty> facultyList = facultyService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("facultyList", facultyList);
 
        int totalPages = facultyList.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        
        return "faculty/faculty";
    }

	//==== 2. Create new Faculty Form ====
	@RequestMapping(value={"/faculty/add"},method=RequestMethod.GET)
    public String AddFaculty(@ModelAttribute("newFaculty") Faculty faculty, Model model) {
		
		model.addAttribute("newFaculty", faculty);
 		return "faculty/addFaculty";
    }
	
	//==== 3. Save add new Faculty ====
	@RequestMapping(value={"/faculty/save"}, method = RequestMethod.POST)
    public String saveFaculty(@ModelAttribute("newFaculty") Faculty faculty, Model model) {
		
		facultyService.save(faculty);

 		return "redirect:/faculty";
    }
	
	//=== 4. Edit Form ====
	@RequestMapping(value = "/faculty/edit/{id}")
	public String editEntry(@PathVariable Long id, Model model) {
		Faculty faculty = facultyService.getFacultyById(id);
		model.addAttribute("faculty", faculty);
		return "faculty/editFaculty";
	}
	
	//=== 5. Save Edit ====
	@RequestMapping(value = "/faculty/edit/save", method = RequestMethod.POST)
	public String SaveEditFaculty(@ModelAttribute("faculty") Faculty faculty) {
		Faculty entity=facultyService.getFacultyById(faculty.getId());
		entity.setFirstName(faculty.getFirstName());
		entity.setLastName(faculty.getLastName());
		entity.setPhone(faculty.getPhone());
		entity.setAddress(faculty.getAddress());
		entity.setDob(faculty.getDob());
		entity.setEmail(faculty.getEmail());
		entity.setGender(faculty.getGender());
		facultyService.save(entity);
		
		return "redirect:/faculty";
	}
	
	//=== 6. Remove ====
	@RequestMapping(value = "/faculty/delete/{id}")
	public String deleteEntry(@PathVariable Long id) {
		facultyService.deleteById(id);

		return "redirect:/faculty";
	}
	
}
