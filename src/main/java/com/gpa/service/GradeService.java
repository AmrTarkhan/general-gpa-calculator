package com.gpa.service;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.gpa.model.Grade;
import com.gpa.repository.GradeRepository;



@Service
@Transactional
public class GradeService {
	
	@Autowired
	private GradeRepository gradeRepo;

	@Autowired
	public GradeService(GradeRepository gradeRepo) {
		this.gradeRepo = gradeRepo;
	}
	
	public List<Grade> findAll(){
		
		return gradeRepo.findAll();
	}
	
	public void save(Grade grade) {
		
		gradeRepo.save(grade);
	}
	
	//Find points of specific uni and grade
	public float getGradePoints(long universityId, String grade) {
		
		float value = 0;
		String upperGrade = StringUtils.capitalize(grade);
		
		List<Grade> all = gradeRepo.findAll();
		
		for (int i=0; i < all.size(); i++) {
			

			if (universityId == all.get(i).getUniversity().getId() &&
				upperGrade.equals(all.get(i).getGrade())) {
				
				value = all.get(i).getPoints();
				System.out.println(upperGrade);
				
				}
			}
		//System.out.println(value);
			return value;
		}

	
	
	
	
	
	
	
	

	
}
