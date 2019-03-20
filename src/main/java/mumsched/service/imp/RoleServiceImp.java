package mumsched.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mumsched.dao.RoleDao;
import mumsched.model.Role;
import mumsched.service.RoleService;
@Service
public class RoleServiceImp implements RoleService {
	@Autowired
	RoleDao roleDao;
	
	@Override
	public Role getRoleByRoleId(Long id) {
		
		return roleDao.geRoleByRoleId(id);
	}

	@Override
	public List<Role> getAllRole() {
		return roleDao.getAllRole();
	}

	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

}
