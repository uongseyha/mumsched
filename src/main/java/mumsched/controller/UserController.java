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

import mumsched.model.Role;
import mumsched.model.Section;
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
	
	
	//1. Show all users
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

	// 2. Add new user as student
	@RequestMapping(value = "/userstudent/addform", method = RequestMethod.GET)
	public String addUserStudentForm(@ModelAttribute("newUser") User user, Model model) {
//		model.addAttribute("roleList", roleService.getAllRole());
		model.addAttribute("studentList", studentService.getAllStudent());
		model.addAttribute("newUser", user);

		return "user/addUserStudentForm";
	}

	@RequestMapping(value = "/userstudent/save", method = RequestMethod.POST)
	public String saveUserStudent(@ModelAttribute("newUser") User user, Model model) {
		List<User> users = userService.getUsers();
		for (User u : users) {
			if (user.getUserName().equals(u.getUserName())) {
				model.addAttribute("userExist", true);

//				model.addAttribute("roleList", roleService.getAllRole());
				model.addAttribute("studentList", studentService.getAllStudent());
				model.addAttribute("newUser", user);

				return "user/addUserStudentForm";
			}
		}

		Role role = new Role();
		role.setId(3);
		role.setDescription("Student");
		user.setRole(role);
		userService.save(user);
		return "redirect:/users";
	}
	
	
	
	
	// 3. Add new user as faculty
	@RequestMapping(value = "/userfaculty/addform", method = RequestMethod.GET)
	public String addUserFacultyForm(@ModelAttribute("newUser") User user, Model model) {
//		model.addAttribute("roleList", roleService.getAllRole());
		model.addAttribute("facultyList", facultyService.getAllFaculty());
		model.addAttribute("newUser", user);

		return "user/addUserFaculty";
	}

	@RequestMapping(value = "/userfaculty/save", method = RequestMethod.POST)
	public String saveUserFaculty(@ModelAttribute("newUser") User user, Model model) {
		List<User> users = userService.getUsers();
		for (User u : users) {
			if (user.getUserName().equals(u.getUserName())) {
				model.addAttribute("userExist", true);

//				model.addAttribute("roleList", roleService.getAllRole());
				model.addAttribute("facultyList", facultyService.getAllFaculty());
				model.addAttribute("newUser", user);

				return "user/addUserFaculty";
			}
		}

		Role role = new Role();
		role.setId(2);
		role.setDescription("Faculty");
		user.setRole(role);
		userService.save(user);
		return "redirect:/users";
	}

	
	//4. Delete user
	@RequestMapping(value = "/delete-user/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteById(id);

		return "redirect:/users";
	}
	
	
	//5. Edit user form
	@RequestMapping(value = "/editnewuser/{id}")
	public String editUser(@PathVariable Long id, User u,  Model model) {
		// Get role drop down
		model.addAttribute("roleList", roleService.getAllRole());
		model.addAttribute("editedUser", userService.getUserByUserId(id));
		return "user/editUserForm";
	}
	
	//6. Save Edited user
	@RequestMapping(value = "/user/edit/save", method = RequestMethod.POST)
	public String SaveEditedUser(@ModelAttribute("editedUser") User u, Model model) {
		
		List<User> users = userService.getUsers();
		
		for (User us : users) {
			if (u.getUserName().equals(us.getUserName())) {
				model.addAttribute("userExist", true);

				model.addAttribute("roleList", roleService.getAllRole());
				model.addAttribute("editedUser", u);

				return "user/editUserForm";
			}
		}
		User user=userService.getUserByUserId(u.getId());
		user.setRole(u.getRole());
		user.setAdmin(u.getAdmin());
		user.setFaculty(u.getFaculty());
		user.setStudent(u.getStudent());
		user.setUserName(u.getUserName());
		user.setUserPassword(u.getUserPassword());
		userService.save(user);
		return "redirect:/users";
	}
	
	
}
