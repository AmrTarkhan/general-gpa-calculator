package com.gpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 +---------------+-------------+------+-----+---------+----------------+
| Field         | Type        | Null | Key | Default | Extra          |
+---------------+-------------+------+-----+---------+----------------+
| id            | bigint      | NO   | PRI | NULL    | auto_increment |
| material_name | varchar(45) | NO   |     | NULL    |                |
| grade         | varchar(5)  | NO   |     | NULL    |                |
| credit        | int         | NO   |     | NULL    |                |
| status        | int         | NO   |     | NULL    |                |
| user_id       | bigint      | NO   | MUL | NULL    |                |
+---------------+-------------+------+-----+---------+----------------+
 */

@Entity
@Table(name = "material")
public class Material {

	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "material_name")
	private String materialName;
	
	@Column(name = "grade")
	private String grade;
	
	@Column(name = "prev_grade")
	private String prevGrade;
	
	@Column(name = "credit")
	private Integer credit;
	
	@Column(name = "status")
	private Integer status;
	
	/*@Column(name = "user_id")
	private long userId;*/
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;
	
	public Material() {
		
	}

	public Material(String materialName, String grade, Integer credit, Integer status, User user) {
		
		this.materialName = materialName;
		this.grade = grade;
		
		this.credit = credit;
		this.status = status;
		this.user = user;
	}
	
	public Material(long id, String materialName, String grade, String prevGrade, Integer credit, Integer status, User user) {
		this.id = id;
		this.materialName = materialName;
		this.grade = grade;
		this.prevGrade = prevGrade;
		this.credit = credit;
		this.status = status;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPrevGrade() {
		return prevGrade;
	}

	public void setPrevGrade(String prevGrade) {
		this.prevGrade = prevGrade;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Material [id=" + id + ", materialName=" + materialName + ", grade=" + grade + ", prevGrade=" + prevGrade
				+ ", credit=" + credit + ", status=" + status + ", user=" + user + "]";
	}


	
	
	
}
