package com.gpa.payload;

import javax.persistence.Column;

public class AddMaterial {

	private String materialName;
	private String grade;
	private String prevGrade;
	private Integer credit;
	private long userId;
	public AddMaterial(String materialName, String grade, String prevGrade, Integer credit, long userId) {
		this.materialName = materialName;
		this.grade = grade;
		this.prevGrade = prevGrade;
		this.credit = credit;
		this.userId = userId;
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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
