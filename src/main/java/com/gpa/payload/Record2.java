package com.gpa.payload;

import java.util.ArrayList;
import java.util.List;


public class Record2 {


	
	private long uniId;
	private int lastCredits;
	private float lastGPA;
	
	private List<Pair2Record> record = new ArrayList<Pair2Record>();

	public Record2( long uniId, int lastCredits, float lastGPA, List<Pair2Record> record) {
		this.uniId = uniId;
		this.lastCredits = lastCredits;
		this.lastGPA = lastGPA;
		this.record = record;
	}

	public long getUniId() {
		return uniId;
	}

	public void setUniId(long uniId) {
		this.uniId = uniId;
	}

	public int getLastCredits() {
		return lastCredits;
	}

	public void setLastCredits(int lastCredits) {
		this.lastCredits = lastCredits;
	}

	public float getLastGPA() {
		return lastGPA;
	}

	public void setLastGPA(float lastGPA) {
		this.lastGPA = lastGPA;
	}

	public List<Pair2Record> getRecord() {
		return record;
	}

	public void setRecord(List<Pair2Record> record) {
		this.record = record;
	}
	
	
}
