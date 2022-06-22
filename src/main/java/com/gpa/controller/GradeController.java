package com.gpa.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpa.model.Grade;
import com.gpa.model.User;
import com.gpa.service.GradeService;
import com.gpa.service.MaterialService;

@RestController
public class GradeController {

	@Autowired
	private GradeService gradeService;
	@Autowired
	private MaterialService materialService;
	
	
	/*@GetMapping("/allusers")
	public List<Grade> getUsers(){
		
		return gradeService.findAll();
	}*/
	
	@PostMapping("/grade")
	@CrossOrigin
	public float gradegrade(@RequestParam String grade) {
		
		return gradeService.getGradePoints(1, grade);
	}

}
