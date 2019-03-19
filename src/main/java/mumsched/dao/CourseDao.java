package mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mumsched.model.Course;

@Repository
public interface CourseDao  extends  JpaRepository<Course, Long>  {
	
	@Query("select s from Course s where s.id= :id")
	public Course findCourseById(@Param("id") Long studentId);

}
