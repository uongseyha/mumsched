package mumsched.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mumsched.model.Admin;
import mumsched.model.Block;

@Repository
public interface AdminDao extends JpaRepository<Admin, Long>{
	//@Query("select s from Admin s ")
	//public List<Admin> findAll();
}
