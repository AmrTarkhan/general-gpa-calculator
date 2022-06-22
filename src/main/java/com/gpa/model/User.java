package com.gpa.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
+---------------+-------------+------+-----+---------+----------------+
| Field         | Type        | Null | Key | Default | Extra          |
+---------------+-------------+------+-----+---------+----------------+
| id            | bigint      | NO   | PRI | NULL    | auto_increment |
| first_name    | varchar(20) | NO   |     | NULL    |                |
| last_name     | varchar(20) | NO   |     | NULL    |                |
| password      | varchar(45) | NO   |     | NULL    |                |
| email         | varchar(60) | NO   |     | NULL    |                |
| gpa           | float       | YES  |     | NULL    |                |
| total_credit  | int         | YES  |     | NULL    |                |
| university_id | bigint      | NO   | MUL | NULL    |                |
| faculty_id    | int         | NO   | MUL | NULL    |                |
+---------------+-------------+------+-----+---------+----------------+
*/

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name")
	@NotNull(message = "Firstname is required")
	private String firstName;
	
	@Column(name = "last_name")
	@NotNull(message = "Lastname is required")
	private String lastName;
	
	@Column(name = "password")
	@JsonIgnore
	private String password;
	
	@Column(name = "email")
	@NotNull(message = "Email is required")
	@Email(message = "Email is unvalid")
	private String email;
	
	@Column(name = "gpa")
	private float gpa;
	
	@Column(name = "total_credit")
	private int totalCredits;
	
	@ManyToOne(targetEntity = University.class)
	@JoinColumn(name = "university_id")
	private University university;
	
	@ManyToOne(targetEntity = Faculty.class)
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;
	
	@ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), 
    							   inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName, String password, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public User(long id, String firstName, String lastName, String password, String email, float gpa, int totalCredits,
			University university, Faculty faculty, Set<Role> roles) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.gpa = gpa;
		this.totalCredits = totalCredits;
		this.university = university;
		this.faculty = faculty;
		this.roles = roles;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	public int getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", gpa="
				+ gpa + ", totalCredits=" + totalCredits + ", university=" + university + ", faculty=" + faculty + "]";
	}
	
	
	public Map<String, String> toJSON(){
		Map<String, String> data = new HashMap<String, String>();
		data.put("firstName",this.firstName);
		data.put("lastName", this.lastName);
		data.put("universityId", String.valueOf(this.university.getId()));
		data.put("facultyId",String.valueOf(this.faculty.getId()) );
		data.put("email", this.email);
		
		return data;
	}
	
	
}
