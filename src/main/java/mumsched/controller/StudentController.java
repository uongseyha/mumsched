package mumsched.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mumsched.model.Student;
import mumsched.model.User;
import mumsched.service.StudentService;
import java.util.*;

@Controller
public class StudentController {
	
	@Autowired
	StudentService studentService;

	@RequestMapping(value={"/student"},method=RequestMethod.GET)
	public String studentList(@ModelAttribute("newStudent") Student student, Model model) {
 
        //model.addAttribute("newStudent", student);
        
        //=== return studentList ===
        List<Student> studentList=new ArrayList<Student>();
        for(Student s: studentService.getAllStudent()) {
        	studentList.add(s);
        }
        model.addAttribute("studentList", studentList);
        
        return "student/student";
        //return "/layouts/default";
    }
	
	@RequestMapping(value={"/student/save"}, method = RequestMethod.POST)
    public String registerStudent(@ModelAttribute("newStudent") Student student, Model model) {
		 
	//STUDENT SAVED IN PERSISTENCE
		studentService.save(student);

 		return "student/addStudent";
    }

	@RequestMapping(value={"/student/add"},method=RequestMethod.GET)
    public String studentCreateForm(@ModelAttribute("newStudent") Student student, Model model) {
		
 	//GET STUDENT FROM PERSISTENCE	
		model.addAttribute("newStudent", student);

 		return "student/addStudent";
    }	
	
	@RequestMapping(value={"/student/detail"},method=RequestMethod.GET)
    public String studentDetail(@ModelAttribute("newStudent") Student student, Model model) {
		
 	//GET STUDENT FROM PERSISTENCE	
 		model.addAttribute(studentService.getStudentByEmail(student.getEmail()));

 		return "student/studentDetail";
    }

	@GetMapping(value = {"/"}) // = @RequestMapping(value = "/user")
	public String userPage(Model model){
		
		return "/layouts/default";
	}
	
}
