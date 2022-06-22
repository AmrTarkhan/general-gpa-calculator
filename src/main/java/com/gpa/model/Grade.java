package com.gpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 +---------------+------------+------+-----+---------+----------------+
| Field         | Type       | Null | Key | Default | Extra          |
+---------------+------------+------+-----+---------+----------------+
| id            | long        | NO   | PRI | NULL    | auto_increment |
| grade         | varchar(5) | NO   |     | NULL    |                |
| points        | float      | NO   |     | NULL    |                |
| university_id | bigint     | NO   | MUL | NULL    |                |
+---------------+------------+------+-----+---------+----------------+
 */

@Entity
@Table(name = "grade")
public class Grade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "grade")
	private String grade;
	
	@Column(name = "points")
	private float points;
	
	@OneToOne(targetEntity = University.class)
	@JoinColumn(name = "university_id")
	private University university;
	
	public Grade() {
		
	}
	public Grade(String grade, float points, University university) {
		
		this.grade = grade;
		this.points = points;
		this.university = university;
	}
	public Grade(long id, String grade, float points, University university) {
		this.id = id;
		this.grade = grade;
		this.points = points;
		this.university = university;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public float getPoints() {
		return points;
	}

	public void setPoints(float points) {
		this.points = points;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", grade=" + grade + ", points=" + points + ", university=" + university + "]";
	}
	
	
	
}
