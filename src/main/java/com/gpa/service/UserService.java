package com.gpa.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.gpa.model.User;
import com.gpa.repository.UniversityRepository;
import com.gpa.repository.UserRepository;


@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UniversityRepository universityRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserService(UserRepository userRepo, UniversityRepository universityRepo) {
		this.userRepo = userRepo;
		this.universityRepo = universityRepo;
	}
	
	public List<User> findAll(){
		
		return userRepo.findAll();
	}
	
	public void save(User theUser) {
		//theUser.setPassword(pbkdf2PasswordEncoder.encode(theUser.getPassword()));
		theUser.setPassword(bCryptPasswordEncoder.encode(theUser.getPassword()));
		userRepo.save(theUser);
	}
	
	public User updateUser(User user) {
		
		return userRepo.save(user);
	}
	
	public User findById(long theId) {
		Optional<User> result = userRepo.findById(theId);
		User theRequests = null;
		if(result.isPresent()){
			
			theRequests = result.get();
		}
		else {
			throw new RuntimeException("Didn't find - " +theId );
		}
		return theRequests;
	}

	public User findUserByEmail(String email) {
		
		return userRepo.findByEmail(email);
	}
	

	public List<User> forOneUni(Long uniId){
		
		List<User> all = userRepo.findAll();
		List<User> newone = new ArrayList<User>();
		
		for(int i=0; i < all.size(); i++) {
			
			if( uniId == all.get(i).getUniversity().getId() ) {
				
				newone.add(all.get(i));
			}
		}
		return newone;
	}
	
}
