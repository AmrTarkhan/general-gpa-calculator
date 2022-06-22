package com.gpa.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gpa.model.Grade;
import com.gpa.model.University;
import com.gpa.model.User;
import com.gpa.payload.AddUniversity;
import com.gpa.payload.Pair;
import com.gpa.repository.GradeRepository;
import com.gpa.repository.UniversityRepository;


@Service
@Transactional
public class UniversityService {
	
	@Autowired
	private UniversityRepository universityRepo;
	@Autowired
	private GradeService gradeService;

	@Autowired
	public UniversityService(UniversityRepository universityRepo, GradeService gradeService) {
		this.universityRepo = universityRepo;
		this.gradeService = gradeService;
	}
	
	public List<University> findAll(){
		
		return universityRepo.findAll();
	}

	
	public University findById(long theId) {
		Optional<University> result = universityRepo.findById(theId);
		University theRequests = null;
		if(result.isPresent()){
			
			theRequests = result.get();
		}
		else {
			throw new RuntimeException("Didn't find - " +theId );
		}
		return theRequests;
	}
	

	public void addUni(AddUniversity record) {
		
		University uni = new University(record.getUniName());
		
		uni.setUniName(record.getUniName());
		uni.setStatus(true);
		universityRepo.save(uni);
		
		for(int i =0; i < record.getRecord().size(); i++) {

			Grade grade2 = new Grade(record.getRecord().get(i).getGrade(),
					record.getRecord().get(i).getPoints(), uni);
			System.out.println(grade2);
			gradeService.save(grade2);
		}
	}
	

	
}
