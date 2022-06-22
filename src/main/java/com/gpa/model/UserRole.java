package com.gpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity	
@Table(name = "user_role")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(targetEntity = Role.class)
	@JoinColumn(name = "role_id")
	private Role role;
	
	@OneToOne(targetEntity = User.class, cascade = CascadeType.DETACH)
	@JoinColumn(name = "user_id")
	private User user;
	

	
	public UserRole() {}

	public UserRole(long id, Role role, User user) {
		this.id = id;
		this.role = role;
		this.user = user;
	}




	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", role=" + role + ", user=" + user + "]";
	}
	
	
	
	
}
