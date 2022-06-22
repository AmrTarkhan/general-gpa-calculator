package com.gpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 +-------------+---------------+------+-----+---------+----------------+
| Field       | Type          | Null | Key | Default | Extra          |
+-------------+---------------+------+-----+---------+----------------+
| id          | bigint        | NO   | PRI | NULL    | auto_increment |
| description | varchar(1000) | NO   |     | NULL    |                |
| user_id     | bigint        | NO   | MUL | NULL    |                |
+-------------+---------------+------+-----+---------+----------------+
 */
@Entity
@Table(name = "complaint")
public class Complaint {
	
	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "description")
	private String description;
	
	@Column(name = "user_id")
	private long userId;
	
	public Complaint() {
		
	}

	public Complaint(long id, String description, long userId) {
		this.id = id;
		this.description = description;
		this.userId = userId;
	}
	
	public Complaint(String description, long userId) {
		
		this.description = description;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Complaint [id=" + id + ", description=" + description + ", userId=" + userId + "]";
	}
	
	
	
}
