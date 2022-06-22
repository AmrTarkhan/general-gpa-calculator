package com.gpa.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gpa.model.Faculty;
import com.gpa.model.University;
import com.gpa.repository.FacultyRepository;


@Service
@Transactional
public class FacultyService {
	
	@Autowired
	private FacultyRepository facultyRepo;

	@Autowired
	public FacultyService(FacultyRepository facultyRepo) {
		this.facultyRepo = facultyRepo;
	}
	
	public List<Faculty> findAll(){
		
		return facultyRepo.findAll();
	}

	public Faculty findById(long theId) {
		Optional<Faculty> result = facultyRepo.findById(theId);
		Faculty theRequests = null;
		if(result.isPresent()){
			
			theRequests = result.get();
		}
		else {
			throw new RuntimeException("Didn't find - " +theId );
		}
		return theRequests;
	}
	

	
}
