package mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mumsched.model.FacultyCourse;

@Repository
public interface FacultyCourseDao  extends  JpaRepository<FacultyCourse, Long>  {
	
	@Query("select s from FacultyCourse s where s.id= :id")
	public FacultyCourse findFacultyCourseById(@Param("id") Long facultyCourseId);
}


