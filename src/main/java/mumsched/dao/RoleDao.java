package mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mumsched.model.Role;


@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
	@Query("select s from Role s where s.id= :id")
	public Role geRoleByRoleId(@Param("id")Long id);
	
	
	@Query("select s from Role s")
	public List<Role> getAllRole();
	
}
