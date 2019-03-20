package mumsched.service.imp;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mumsched.dao.UserDao;
import mumsched.model.Section;
import mumsched.model.User;
import mumsched.service.UserService;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserDao userDao;
	
	@Override
	public void save(User user) {
		userDao.save(user);
		return;
	}

	@Override
	public User getUserByUserId(Long userId) {
		return userDao.findUserByID(userId);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}

	@Override
	public List<User> getUsers() {
		return userDao.findAll();
	}

	@Override
	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

	@Override
	public Page<User> findPaginated(Pageable pageable) {
		List<User> user = userDao.getAllUser();
    	int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<User> list;
 
        if(user.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, user.size());
            list = user.subList(startItem, toIndex);
        }
 
        Page<User> bookPage
          = new PageImpl<User>(list, PageRequest.of(currentPage, pageSize), user.size());
 
        return bookPage;
	}


}
