package mumsched.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mumsched.model.Section;
import mumsched.model.Student;
import mumsched.service.BlockService;
import mumsched.service.FacultyService;
import mumsched.service.SectionService;

@Controller
public class SectionController {
	@Autowired
	SectionService sectionService;
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	BlockService blockService;
	
	@RequestMapping(value="/sections", method=RequestMethod.GET)
	public String showSections(Model model, @RequestParam("page") Optional<Integer> page, 
		      @RequestParam("size") Optional<Integer> size) {
		
		 int currentPage = page.orElse(1);
	     int pageSize = size.orElse(10);
	     
	     Page<Section> sectionList = sectionService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
	     model.addAttribute("sectionList", sectionList);
	     
	     
	     int totalPages = sectionList.getTotalPages();
	        if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                .boxed()
	                .collect(Collectors.toList());
	            model.addAttribute("pageNumbers", pageNumbers);
	        }

		return "section/section";
	}
	@RequestMapping(value="/section/addform", method=RequestMethod.GET)
	public String addSectionForm(@ModelAttribute("newSection") Section section, Model model) {
		
		model.addAttribute("facultyList", facultyService.getAllFaculty());
		model.addAttribute("blockList", blockService.getAllBlocks());
		model.addAttribute("newSection", section);
		return "section/addSectionForm";
	}
	
	@RequestMapping(value="/section/save", method=RequestMethod.POST)
	public String saveSection(@ModelAttribute("newSection") Section section, Model model) {
		
		sectionService.save(section);
		return "redirect:/sections";
	}
}
