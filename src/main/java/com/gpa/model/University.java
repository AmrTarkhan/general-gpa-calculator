package com.gpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 +----------+-------------+------+-----+---------+----------------+
| Field    | Type        | Null | Key | Default | Extra          |
+----------+-------------+------+-----+---------+----------------+
| id       | bigint      | NO   | PRI | NULL    | auto_increment |
| uni_name | varchar(45) | NO   |     | NULL    |                |
| status   | tinyint     | YES  |     | NULL    |                |
+----------+-------------+------+-----+---------+----------------+
 */

@Entity
@Table(name = "university")
public class University {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "uni_name")
	private String uniName;
	
	@Column(name = "status")
	private boolean status;
	
	public University() {
		
	}

	public University(String uniName) {
		this.uniName = uniName;
	}
	public University(long id, String uniName, boolean status) {
		this.id = id;
		this.uniName = uniName;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUniName() {
		return uniName;
	}

	public void setUniName(String uniName) {
		this.uniName = uniName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "University [id=" + id + ", uniName=" + uniName + ", status=" + status + "]";
	}
	
	
	
	
}
