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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mumsched.model.Block;
import mumsched.model.Course;
import mumsched.model.Entry;
import mumsched.model.Faculty;
import mumsched.model.FacultyCourse;
import mumsched.model.Section;
import mumsched.model.Student;
import mumsched.service.BlockService;
import mumsched.service.CourseService;
import mumsched.service.FacultyCourseService;
import mumsched.service.FacultyService;
import mumsched.service.SectionService;

@Controller
public class SectionController {
	@Autowired
	SectionService sectionService;
	@Autowired
	FacultyCourseService facultyCourseService;

	@Autowired
	BlockService blockService;


	@RequestMapping(value = "/sections", method = RequestMethod.GET)
	public String showSections(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);

		Page<Section> sectionList = sectionService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
		model.addAttribute("sectionList", sectionList);

		int totalPages = sectionList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "section/section";
	}

	@RequestMapping(value = "/section/addform", method = RequestMethod.GET)
	public String addSectionForm(@ModelAttribute("newSection") Section section, Model model) {

		model.addAttribute("facultyCourseList", facultyCourseService.getAllFacultyCourse());
		model.addAttribute("blockList", blockService.getAllBlocks());
		model.addAttribute("newSection", section);
		return "section/addSectionForm";
	}

	@RequestMapping(value = "/section/save", method = RequestMethod.POST)
	public String saveSection(@ModelAttribute("newSection") Section section, Model model) {

		sectionService.save(section);
		return "redirect:/sections";
	}

	// === 4. Edit Form ====
	@RequestMapping(value = "/editnewsection/{id}")
	public String editEntry(@PathVariable Long id, Model model) {
		// Get faculityCourse drop down
		model.addAttribute("facultyCourseList", facultyCourseService.getAllFacultyCourse());
		// Get blockList drop down
		model.addAttribute("blockList", blockService.getAllBlocks());

		model.addAttribute("editedSection", sectionService.getSectionBySectionId(id));
		return "section/editSectionForm";
	}
	
	//=== 5. Save Edit ====
	@RequestMapping(value = "/section/edit/save", method = RequestMethod.POST)
	public String SaveEditStudent(@ModelAttribute("editedSection") Section s) {

		Section section=sectionService.getSectionBySectionId(s.getId());
		Block block=blockService.getBlockByBlockID(s.getBlock().getId());
		FacultyCourse facultyCourse=facultyCourseService.getFacultyCourseById(s.getFacultyCourse().getId());
		
		section.setBlock(block);
		section.setFacultyCourse(facultyCourse);
		section.setMaxSeat(s.getMaxSeat());
		
		sectionService.save(section);
		return "redirect:/sections";
	}
	

	// === 6. Remove ====
	@RequestMapping(value = "/delete-section/{id}")
	public String deleteSection(@PathVariable Long id) {
		sectionService.deleteById(id);

		return "redirect:/sections";
	}

}
