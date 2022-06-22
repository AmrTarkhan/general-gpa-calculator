package com.gpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
+--------------+-------------+------+-----+---------+----------------+
| Field        | Type        | Null | Key | Default | Extra          |
+--------------+-------------+------+-----+---------+----------------+
| id           | long         | NO   | PRI | NULL    | auto_increment |
| faculty_name | varchar(45) | NO   |     | NULL    |                |
+--------------+-------------+------+-----+---------+----------------+
 */
@Entity
@Table(name = "faculty")
public class Faculty {

	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "faculty_name")
	private String facultyName;
	
	public Faculty() {
		
	}

	public Faculty(long id, String facultyName) {
		this.id = id;
		this.facultyName = facultyName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	@Override
	public String toString() {
		return "Faculty [id=" + id + ", facultyName=" + facultyName + "]";
	}
	
	
	
}
