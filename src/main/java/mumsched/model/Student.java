package mumsched.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private Long registrationNumber;
	private String Gender;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate entryDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dob;
	private String track;
	
	//@NotBlank(message="Advisor cannot be empty")
	@OneToOne
	@JoinColumn(name="advisor_id")
	private Faculty faculty;

	@OneToOne
	@JoinColumn(name="entry_id")
	private Entry entry;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone=phone;
	}

	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address=address;
	}
	public Long getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(Long registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public LocalDate getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	public Entry getEntry() {
		return entry;
	}
	public void setEntry(Entry entry) {
		this.entry = entry;
	}
	
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

}
