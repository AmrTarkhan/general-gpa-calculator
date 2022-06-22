package com.gpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gpa.model.University;
import com.gpa.payload.AddUniversity;
import com.gpa.service.UniversityService;

@RestController
public class UniversityController {

	@Autowired
	private UniversityService universityService;
	
	
	@GetMapping("/alluni")
	@CrossOrigin
	public List<University> getUnis(){
		
		return universityService.findAll();
	}
	
	@PostMapping("/user/adduni")
	@CrossOrigin
	public void addUniversity(@RequestBody AddUniversity record) {
		
		 universityService.addUni(record);
	}
	
}
