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

import mumsched.model.Role;
import mumsched.model.User;
import mumsched.service.FacultyService;
import mumsched.service.RoleService;
import mumsched.service.StudentService;
import mumsched.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	FacultyService facultyService;
	@Autowired
	StudentService studentService;
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showUsers(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);

		Page<User> userList = userService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
		model.addAttribute("userList", userList);

		int totalPages = userList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "user/users";
	}
	
	//Add new user
	@RequestMapping(value = "/userstudent/addform", method = RequestMethod.GET)
	public String addSectionForm(@ModelAttribute("newUser") User user, Model model) {
		model.addAttribute("roleList", roleService.getAllRole());
		model.addAttribute("studentList", studentService.getAllStudent());
		model.addAttribute("newUser", user);
		
		return "user/addUserStudentForm";
	}

	@RequestMapping(value = "/userstudent/save", method = RequestMethod.POST)
	public String saveSection(@ModelAttribute("newUser") User user, Model model) {
		if(userService.getUserByUserName(user.getUserName()).getUserName().equals(user.getUserName())
				&& user.getUserPassword().equals(userService.getUserByUserId(user.getId()).getUserPassword())) {
			model.addAttribute("userExist", "Already this user is existing");
			return "redirect:/addUserStudentForm";
		}
		
		Role role=roleService.getRoleByRoleId(Long.valueOf(3));
		user.setRole(role);
		userService.save(user);
		return "redirect:/users";
	}
	
}
