package com.gpa.payload;

import java.util.ArrayList;
import java.util.List;


public class AddUniversity {
	
	private String uniName;
	
	private List<Pair> record = new ArrayList<Pair>();

	public AddUniversity(String uniName, List<Pair> record) {
		this.uniName = uniName;
		this.record = record;
	}

	public String getUniName() {
		return uniName;
	}

	public void setUniName(String uniName) {
		this.uniName = uniName;
	}

	public List<Pair> getRecord() {
		return record;
	}

	public void setRecord(List<Pair> record) {
		this.record = record;
	}
	


}
