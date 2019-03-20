package mumsched.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//import mumsched.model.Faculty;

@Entity
@Table(name="section")
public class Section {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Integer maxSeat;
	
	@OneToOne
	@JoinColumn(name="faculty_course_id")
	private FacultyCourse facultyCourse;
	
	@OneToOne
	@JoinColumn(name="block_id")
	private Block block;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMaxSeat() {
		return maxSeat;
	}

	public void setMaxSeat(Integer maxSeat) {
		this.maxSeat = maxSeat;
	}

	public FacultyCourse getFacultyCourse() {
		return facultyCourse;
	}

	public void setFacultyCourse(FacultyCourse facultyCourse) {
		this.facultyCourse = facultyCourse;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	

}


