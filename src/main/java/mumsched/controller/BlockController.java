package mumsched.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mumsched.model.Block;
import mumsched.service.BlockService;

@Controller
public class BlockController {
	
	@Autowired
	BlockService blockService;
	
	@RequestMapping(value="/block", method=RequestMethod.GET)
	public String blockRegForm(@ModelAttribute("newBlock") Block block, Model model) {
		List<Block> blockList=blockService.getAllBlocks();
		
		model.addAttribute("blockList", blockList);
		model.addAttribute("newBlock", block);
		return "block/addBlockForm";
	}
	@RequestMapping(value= {"/addnewblock"}, method=RequestMethod.POST)
	public String registerNewBlock(@ModelAttribute("newBlock") Block block, Model model) {

		blockService.save(block);
		return "redirect:/show-blocks";
	}
	
	@RequestMapping(value= {"/show-blocks"}, method=RequestMethod.GET)
	public String showBlocks(Model model) {
		List<Block> blockList=blockService.getAllBlocks();
		model.addAttribute("blockList", blockList);
		return "block/blocks";
	}
	@RequestMapping(value="/delete-block/{id}", method=RequestMethod.GET)
	public String deleteBlock(@PathVariable Long id) {
		blockService.deleteById(id);
		return "redirect:/show-blocks";
	}
	
	@RequestMapping(value="/editblock/{id}")
	public String editBlock(@PathVariable Long id, Model model) {
		
		model.addAttribute("blockEdit", blockService.getBlockByBlockID(id));
		return "block/editBlock";
	}
	
	@RequestMapping(value="/editblock-saved", method=RequestMethod.POST)
	public String editAndSave(@ModelAttribute("blockEdit") Block b) {

		Block block=blockService.getBlockByBlockID(b.getId());
		block.setBlockName(b.getBlockName());
		block.setEndDate(b.getEndDate());
		block.setFPPNumber(b.getFPPNumber());
		block.setMPPNumber(b.getMPPNumber());
		block.setOrderNumber(b.getOrderNumber());
		block.setStartDate(b.getStartDate());
		
		blockService.save(block);

		return "redirect:/show-blocks";
	}
}
