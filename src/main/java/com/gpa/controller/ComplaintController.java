package com.gpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpa.model.Complaint;
import com.gpa.service.ComplaintService;
import com.gpa.service.UserService;

@RestController
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/admin/allcomplaints")
	@CrossOrigin
	public List<Complaint> getmats(){
		
		return complaintService.findAll();
	}
	
	@GetMapping("/admin/hiscomplaints")
	@CrossOrigin
	public List<Complaint> ComplaintsOfUser(@RequestParam long userId){
		
		return complaintService.madeByOneUser(userId);
	}

	

}
