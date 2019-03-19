package mumsched.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="entry")
public class Entry {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String entryName;
	private Integer entryYear;
	private Integer entryMonth;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate endDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate updateDate;
	
	@Column(name="fpp_number")
	private Integer FPPNumber;

	@Column(name="mpp_number")
	private Integer MPPNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getEntryYear() {
		return entryYear;
	}

	public void setEntryYear(Integer entryYear) {
		this.entryYear = entryYear;
	}

	public Integer getEntryMonth() {
		return entryMonth;
	}

	public void setEntryMonth(Integer entryMonth) {
		this.entryMonth = entryMonth;
	}

	public Integer getFPPNumber() {
		return FPPNumber;
	}

	public void setFPPNumber(Integer fPPNumber) {
		FPPNumber = fPPNumber;
	}

	public Integer getMPPNumber() {
		return MPPNumber;
	}

	public void setMPPNumber(Integer mPPNumber) {
		MPPNumber = mPPNumber;
	}

	
	
}

