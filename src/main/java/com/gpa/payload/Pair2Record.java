package com.gpa.payload;


public class Pair2Record {

	private String grade;
	private int credits;
	private int status;
	private String prevGrade;
	
	public Pair2Record(String grade, int credits, int status, String prevGrade) {
		this.grade = grade;
		this.credits = credits;
		this.status = status;
		this.setPrevGrade(prevGrade);
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPrevGrade() {
		return prevGrade;
	}
	public void setPrevGrade(String prevGrade) {
		this.prevGrade = prevGrade;
	}
	
	
	
	
	
}
