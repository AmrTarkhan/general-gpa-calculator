package com.gpa.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gpa.model.Complaint;
import com.gpa.repository.ComplaintRepository;


@Service
@Transactional
public class ComplaintService {
	
	@Autowired
	private ComplaintRepository complaintRepo;
	@Autowired
	private UserService userService;

	@Autowired
	public ComplaintService(ComplaintRepository complaintRepo) {
		this.complaintRepo = complaintRepo;
	}
	
	public List<Complaint> findAll(){
		
		return complaintRepo.findAll();
	}
	
	public void addComplaint(Complaint comp) {
		
		Complaint newone = new Complaint(comp.getDescription(), comp.getUserId());
		complaintRepo.save(newone);
	}
	
	public List<Complaint> madeByOneUser(long userId){
		
		List<Complaint> all = complaintRepo.findAll();
		List<Complaint> temp = new ArrayList<Complaint>();
		
		for(int i=0; i < all.size(); i++) {
			if(userId == all.get(i).getUserId()) {
				temp.add(all.get(i));
			}
		}
		return temp;
	}
	

	
}
