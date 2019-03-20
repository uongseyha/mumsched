package mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mumsched.model.StudentRegistrationStatus;

@Repository
public interface StudentRegistrationStatusDao  extends  JpaRepository<StudentRegistrationStatus, Long>  {
	
	@Query("select s from StudentRegistrationStatus s where s.id= :id")
	public StudentRegistrationStatus findStudentRegistrationStatusById(@Param("id") Long studentRegistrationStatusId);

}
