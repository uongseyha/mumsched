package mumsched.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mumsched.dao.UserDao;
import mumsched.model.User;
import mumsched.service.UserService;

@Service
public class UserServiceImp implements UserService  {
	
	//@Autowired (required=false)
	@Autowired
	UserDao userDao;
	
	public void save(User user){
		userDao.save(user);
		return ;
	}

	@Override
	public User getUserById(Long userId) {
		return userDao.findUserById(userId);
		//return userDao.findOne(userId);
	}
	
	
	@Override
	public List<User> getAllUser(){
		return userDao.findAll();
	}
	
}
