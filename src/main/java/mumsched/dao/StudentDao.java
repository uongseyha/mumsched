package mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mumsched.model.Student;

@Repository
public interface StudentDao  extends  JpaRepository<Student, Long>  {
	
	@Query("select s from Student s where s.id= :id")
	public Student findStudentById(@Param("id") Long studentId);

	@Query("select s from Student s where s.email= :email")
	public Student findStudentByEmail(@Param("email") String studentEmail);

	public List<Student> findAllById(int id);
	
//	@Query("select s from Student s")
//	public List<Student> getAllStudent();
	
}
