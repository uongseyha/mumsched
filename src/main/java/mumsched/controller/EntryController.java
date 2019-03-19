package mumsched.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mumsched.model.Entry;
import mumsched.model.Student;
import mumsched.service.EntryService;
import java.util.*;

@Controller
public class EntryController {
	@Autowired
	EntryService entryService;

	@RequestMapping(value = "/entry/addform", method = RequestMethod.GET)
	public String entryRegForm(@ModelAttribute("newEntry") Entry entry, Model model) {
		model.addAttribute("newEntry", entry);
		
		return "entry/addEntryForm";
	}

	@RequestMapping(value = { "/entry/save" }, method = RequestMethod.POST)
	public String registerNewEntry(@ModelAttribute("newEntry") Entry entry, Model model) {

		entryService.save(entry);
		return "redirect:/showentries";
	}

	@RequestMapping(value = { "/showentries" }, method = RequestMethod.GET)
	public String showEntriesList(@ModelAttribute("newStudent") Student student, Model model) {
		
		List<Entry> entryList =entryService.getAllEntry();
		
		model.addAttribute("entryList", entryList);
		
		return "entry/entries";
	}

	@RequestMapping(value = "/editnewentry/{id}")
	public String editEntry(@PathVariable Long id, Model model) {
		Entry entry = entryService.getEntryByEntryID(id);
		model.addAttribute("entry", entry);
		return "entry/editEntry";
	}

	@RequestMapping(value = "/editsave-entry", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("entry") Entry p) {
		Entry entry=entryService.getEntryByEntryID(p.getId());
		entry.setEndDate(p.getEndDate());
		entry.setEntryName(p.getEntryName());
		entry.setFPPNumber(p.getFPPNumber());
		entry.setMPPNumber(p.getMPPNumber());
		entry.setStartDate(p.getStartDate());
		entryService.save(entry);
		
		return "redirect:/showentries";
	}

	@RequestMapping(value ="/delete-entry/{id}", method = RequestMethod.GET)
	public String deleteEntry(@PathVariable Long id) {
		entryService.deleteById(id);

		return "redirect:/showentries";
	}
}
