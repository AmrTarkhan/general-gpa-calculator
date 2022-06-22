package com.gpa.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.gpa.model.Complaint;
import com.gpa.model.Faculty;
import com.gpa.model.Material;
import com.gpa.model.University;
import com.gpa.model.User;
import com.gpa.payload.EditPasswordPayload;
import com.gpa.payload.EditProfileRequest;
import com.gpa.payload.JwtResponse;
import com.gpa.payload.LoginRequest;
import com.gpa.payload.SignupRequest;
import com.gpa.repository.FacultyRepository;
import com.gpa.security.JwtUtil;
import com.gpa.security.service.UniGpaUserDetails;
import com.gpa.service.ComplaintService;
import com.gpa.service.FacultyService;
import com.gpa.service.MaterialService;
import com.gpa.service.UniversityService;
import com.gpa.service.UserRoleService;
import com.gpa.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private ComplaintService compService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UniversityService universityService;
	@Autowired
	FacultyService facultyService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtil jwtManager;
	
	@Autowired
	public UserController(UserService theUserService) {
		
		userService = theUserService;
	}
	
	
	@PostMapping(value="/login")
	@CrossOrigin
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtManager.generateToken(authentication);
		UniGpaUserDetails userDetails = (UniGpaUserDetails) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(), roles));
    }
	
	
	@PostMapping(value="/signup")
	@CrossOrigin
	public ResponseEntity<Integer> signup(@Valid @RequestBody SignupRequest signupRequest){
		
		User user = new User(signupRequest.getFirstName(), signupRequest.getLastName(),
				signupRequest.getPassword(), signupRequest.getEmail());
		
		if (userService.findUserByEmail(user.getEmail()) != null) {
			return new ResponseEntity<Integer>(HttpStatus.CONFLICT);
		}		
		
		userService.save(user);;
		
		userRoleService.setRole(2, user.getId());
		
		return new ResponseEntity<Integer>(HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping(value="/user/editprofile")
	@CrossOrigin
	public Map<String, String> editProfile(Principal principal, @RequestBody(required = false) EditProfileRequest request){
		User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		University uni = universityService.findById(request.getUniversityId()); 
		Faculty faculty = facultyService.findById(request.getFacultyId());

		if (request.getFirstName() != null)
			user.setFirstName(request.getFirstName());
		if (request.getLastName() != null)
			user.setLastName(request.getLastName());
		if (request.getEmail() != null)
			user.setEmail(request.getEmail());
		if (request.getUniversityId() != null)
			user.setUniversity(uni);
		if (request.getFacultyId() != null)
			user.setFaculty(faculty);



		
		return userService.updateUser(user).toJSON();
	}
	
	
/*	@PostMapping(value="/user/editpassword")
	@CrossOrigin
	public void editPassword(@RequestBody EditPasswordPayload payload) {
		
		User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		
		if ( user.getPassword() )
		
	}*/
	
	
	
	@GetMapping("/allusers")
	@CrossOrigin
	public List<User> getUsers(){
		
		return userService.findAll();
	}
	
	
	@GetMapping("/admin/fromuni")
	@CrossOrigin
	public List<User> fromOneIniversity(@RequestParam Long uniId){
		
		return userService.forOneUni(uniId);
	}
	
	@PostMapping("/user/complaint")
	@CrossOrigin
	public void addComplaint(@RequestBody Complaint comp) {
		compService.addComplaint(comp);
	}
	
	/*@GetMapping("/user/pgpa")
	public float getGpa() {
		
		User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return user.getGpa();
	}*/
	
	@GetMapping("/user/getdata")
	@CrossOrigin
	public User findUser() {
		
		User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return user;
	}
	
	
}
