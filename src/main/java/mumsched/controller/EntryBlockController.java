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
import mumsched.model.EntryBlock;
import mumsched.model.Faculty;
import mumsched.model.FacultyCourse;
import mumsched.service.BlockService;
import mumsched.service.EntryBlockService;
import mumsched.service.EntryService;

@Controller
public class EntryBlockController {
	@Autowired
	EntryBlockService entryBlockService;
	
	@Autowired
	EntryService entryService;
	
	@Autowired
	BlockService blockService;
	
	
	
	
	
	//==== 1. FacultyCourse List Form ====
		@RequestMapping(value={"/entry-blocks"})
		public String showEntryBlockList(Model model, 
				@RequestParam("page") Optional<Integer> page, 
			      @RequestParam("size") Optional<Integer> size) {
			
	        int currentPage = page.orElse(1);
	        int pageSize = size.orElse(10);
	 
	        Page<EntryBlock> entryBlockList=entryBlockService.findPaginated(PageRequest.of(currentPage-1, pageSize));
	        model.addAttribute("entryBlockList", entryBlockList);
	        int totalPages = entryBlockList.getTotalPages();
	        if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                .boxed()
	                .collect(Collectors.toList());
	            model.addAttribute("pageNumbers", pageNumbers);
	        }

	        return "entryblock/entryBlock";
	    }

		//==== 2. Create new EntryBlock Form ====
		@RequestMapping(value={"/entryblock/addform"},method=RequestMethod.GET)
	    public String AddEntryBlock(@ModelAttribute("newEntryBlock") EntryBlock entryBlock, Model model) {
			
			//==== Get entry for dropdown-list ===
	        List<Entry> entryList=entryService.getAllEntry(); 
	        model.addAttribute("entryList", entryList);
	        
	        //==== Get block for dropdown-list ===
	        List<Block> blockList= blockService.getAllBlocks();
	        model.addAttribute("blockList",blockList);
	        
			model.addAttribute("newEntryBlock", entryBlock);
	 		return "entryblock/addEntryBlock";
	    }
	
		//==== 3. Save add new FacultyCourse ====
		@RequestMapping(value={"/entryBlock/save"}, method = RequestMethod.POST)
	    public String saveFacultyCourse(@ModelAttribute("newEntryBlock") EntryBlock entryBlock, Model model) {
			
			entryBlockService.save(entryBlock);

	 		return "redirect:/entry-blocks";
	    }
		
		//=== 4. Edit Form ====
		@RequestMapping(value = "/editentryblock/{id}")
		public String editEntry(@PathVariable Long id, Model model) {
			
//			//==== Get entry for dropdown-list ===
	        List<Entry> entryList=entryService.getAllEntry();
	        model.addAttribute("entryList",entryList);
	        
	      //==== Get block for dropdown-list ===
	        List<Block> blockList=blockService.getAllBlocks();
	        model.addAttribute("blockList",blockList);
	          
			EntryBlock entryBlock=entryBlockService.getEntryBlockById(id);
			model.addAttribute("entryBlock", entryBlock);
			return "entryblock/editEntryBlock";
		}
		
		//=== 5. Save Edit ====
		@RequestMapping(value = "/entryblock/edit/save", method = RequestMethod.POST)
		public String SaveEditEntryBlock(@ModelAttribute("entryBlock") EntryBlock entryBlock) {
			EntryBlock eBlock=entryBlockService.getEntryBlockById(entryBlock.getId());
			eBlock.setBlock(entryBlock.getBlock());
			eBlock.setEntry(entryBlock.getEntry());
			entryBlockService.save(eBlock);
			return "redirect:/entry-blocks";
		}
		
		//=== 6. Remove ====
		@RequestMapping(value = "/delete-entryblock/{id}")
		public String deleteEntryBlock(@PathVariable Long id) {
			entryBlockService.deleteById(id);

			return "redirect:/entry-blocks";
		}
		
		
		
}
