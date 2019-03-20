package mumsched.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mumsched.model.User;


public interface UserService {
	
	public void save(User user);
	public User getUserByUserId(Long userId);
	public User getUserByUserName(String userName);
	public List<User> getUsers();
	public void deleteById(Long id);
	public Page<User> findPaginated(Pageable page);

}
