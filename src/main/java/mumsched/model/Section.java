package mumsched.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//import mumsched.model.Faculty;

@Entity
@Table(name="section")
public class Section {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int maxSeat;
	
//	@OneToOne
//	@JoinColumn(name="faculty_course_id")
//	private FacultyCourse facultyCourse;
	
	@OneToOne
	@JoinColumn(name="block_id")
	private Block block;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMaxSeat() {
		return maxSeat;
	}

	public void setMaxSeat(int maxSeat) {
		this.maxSeat = maxSeat;
	}

//	public FacultyCourse getFacultyCourse() {
//		return facultyCourse;
//	}
//
//	public void setFacultyCourse(FacultyCourse facultyCourse) {
//		this.facultyCourse = facultyCourse;
//	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

}


