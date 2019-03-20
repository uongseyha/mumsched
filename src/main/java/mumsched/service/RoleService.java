package mumsched.service;

import java.util.List;

import mumsched.model.Role;

public interface RoleService {
	public Role getRoleByRoleId(Long id);
	public List<Role> getAllRole();
	public void save(Role role);
}
