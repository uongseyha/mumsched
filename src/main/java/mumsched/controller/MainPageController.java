package mumsched.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
	@GetMapping(value = {"/"})
	public String userPage(Model model){
		
		return "/layouts/default";
	}
}
