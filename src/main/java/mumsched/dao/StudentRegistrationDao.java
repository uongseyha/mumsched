package mumsched.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mumsched.model.StudentRegistration;

@Repository
public interface StudentRegistrationDao  extends  JpaRepository<StudentRegistration, Long>  {
	
	@Query("select s from StudentRegistration s where s.id= :id")
	public StudentRegistration findStudentRegistrationById(@Param("id") Long studentRegistrationId);

}
