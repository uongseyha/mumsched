package mumsched.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="block")
public class Block {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String blockName;
	private int orderNumber;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate endDate;
	private int FPPNumber;
	private int MPPNumber;
	
	
	public int getFPPNumber() {
		return FPPNumber;
	}
	public void setFPPNumber(int fPPNumber) {
		FPPNumber = fPPNumber;
	}
	public int getMPPNumber() {
		return MPPNumber;
	}
	public void setMPPNumber(int mPPNumber) {
		MPPNumber = mPPNumber;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
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
	@Override
	public String toString() {
		return "Block [id=" + id + ", blockName=" + blockName + ", orderNumber=" + orderNumber + ", startDate="
				+ startDate + ", endDate=" + endDate + ", FPPNumber=" + FPPNumber + ", MPPNumber=" + MPPNumber + "]";
	}
	
	
}


