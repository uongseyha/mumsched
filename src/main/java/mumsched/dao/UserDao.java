package mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mumsched.model.User;

@Repository
public interface UserDao  extends  JpaRepository<User, Long>  {
	
	@Query("select s from User s where s.id= :id")
	public User findUserById(@Param("id") Long userId);
	
	@Query("select s from User s")
	public List<User> getAllUser();
	
}
