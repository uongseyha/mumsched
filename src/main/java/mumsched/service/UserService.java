package mumsched.service;

import java.util.List;

import mumsched.model.User;

public interface UserService   {
	public void save(User user);
	public User getUserById(Long id);
	// Student getStudentByEmail(String email);	   
	public List<User> getAllUser();
}
