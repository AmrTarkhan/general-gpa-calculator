package com.gpa.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpa.model.Role;
import com.gpa.model.User;
import com.gpa.model.UserRole;
import com.gpa.repository.RoleRepository;
import com.gpa.repository.UserRepository;
import com.gpa.repository.UserRoleRepository;

@Service
@Transactional
public class UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	public UserRoleService(UserRoleRepository userRoleRepository, UserRepository userRepository,
			RoleRepository roleRepository) {
		this.userRoleRepository = userRoleRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}


	//Initiate Role For New Users
	public void setRole(long roleId, long userId) {
		
		UserRole ur = new UserRole();
		Optional<User> user = userRepository.findById(userId);
		Optional<Role> role = roleRepository.findById(roleId); 
		ur.setRole(role.get());
		ur.setUser(user.get());
		
		
		userRoleRepository.save(ur);
	}


	

}
