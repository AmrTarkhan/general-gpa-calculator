package com.gpa.payload;

public class Pair {

	private String grade;
	private float points;
	
	
	public Pair(String grade, float points) {
		this.grade = grade;
		this.points = points;
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
}
