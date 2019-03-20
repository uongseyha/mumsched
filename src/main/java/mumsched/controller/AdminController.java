package mumsched.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mumsched.dao.AdminDao;
import mumsched.model.*;
import java.lang.*;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class AdminController {
	
	@Autowired
	private AdminDao adminRepo;
	
	//=== Get All Admins ===
	@GetMapping("/admin")
	public List<Admin> getAllAdmins() {
		return adminRepo.findAll();
	}
	
	//=== Get Admin by ID  ===
	@GetMapping("/admin/{id}")
	public Admin getAdminById(@PathVariable long id){
		Optional<Admin> admin = adminRepo.findById(id);

		if (!admin.isPresent())  
			ResponseEntity.notFound().build();

		return admin.get();
	}
	
	//=== Delete Admin by ID ===
	@DeleteMapping("/admin/{id}")
	public void deleteAdmin(@PathVariable long id) {
		adminRepo.deleteById(id);
	}
	
	//=== Create new Admin =======
	@PostMapping("/admin")
	public Admin createAdmin(@RequestBody Admin admin) {
		return adminRepo.save(admin);

	}
	
	//=== Update Admin by ID=======
	@PutMapping("/admin/{id}")
	public ResponseEntity<Object> updateAdmin(@RequestBody Admin admin, @PathVariable long id) {

		Optional<Admin> adminOptional = adminRepo.findById(id);

		if (!adminOptional.isPresent())
			return ResponseEntity.notFound().build();

		admin.setId(id);
		Admin updateAdmin=adminRepo.save(admin);
		
		return ResponseEntity.ok().body(updateAdmin);
	}
}
