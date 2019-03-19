package mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mumsched.model.Faculty;

@Repository
public interface FacultyDao  extends  JpaRepository<Faculty, Long>  {
	
	@Query("select s from Faculty s where s.id= :id")
	public Faculty findFacultyById(@Param("id") Long studentId);

	@Query("select s from Faculty s where s.email= :email")
	public Faculty findFacultyByEmail(@Param("email") String facultyEmail);

}
