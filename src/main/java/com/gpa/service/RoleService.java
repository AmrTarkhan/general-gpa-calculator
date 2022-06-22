package com.gpa.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gpa.model.Faculty;
import com.gpa.model.Role;
import com.gpa.model.User;
import com.gpa.model.UserRole;
import com.gpa.repository.FacultyRepository;
import com.gpa.repository.RoleRepository;
import com.gpa.repository.UserRoleRepository;


@Service
@Transactional
public class RoleService {
	
	private RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	

	//Find Role By Id (1 = ADMIN, 2 = USER)
	public Role findById(long theId) {
		Optional<Role> result = roleRepository.findById(theId);
		Role theRole = null;
		if(result.isPresent()){
			
			theRole = result.get();
		}
		else {
			throw new RuntimeException("Didn't find - " +theId );
		}
		
		return theRole;
	}

}
