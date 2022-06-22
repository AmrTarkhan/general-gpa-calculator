package com.gpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpa.model.Material;
import com.gpa.model.User;
import com.gpa.payload.AddMaterial;
import com.gpa.payload.Record2;
import com.gpa.service.MaterialService;
import com.gpa.service.UserService;

@RestController
public class MaterialController {

	@Autowired
	private MaterialService materialService;
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/allmaterials")
	@CrossOrigin
	public List<Material> getmats(){
		
		return materialService.findAll();
	}
	
	@GetMapping("/user/hismaterials")
	@CrossOrigin
	public List<Material> MaterialsOfUser(){
		User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return materialService.MaterialsOfUser(user.getId());
	}
	
	@PostMapping("/user/addmaterial")
	@CrossOrigin
	public void addMaterial(@RequestBody AddMaterial paylaod) {
		
		materialService.addMaterial(paylaod);
		materialService.privateGpa(paylaod.getUserId());
	}
	
	@PostMapping("/user/deletematerial")
	@CrossOrigin
	public void deleteMaterial(long materialId) {
		
		Material material = materialService.findById(materialId);
		User user = userService.findById(material.getUser().getId());
		materialService.delete(material);
		materialService.privateGpa(user.getId());
	}
	
	@PostMapping("/user/editmaterial")
	@CrossOrigin
	public void deleteMaterial(@RequestBody Material material) {
	
		materialService.editMaterial(material);
		Material theMat = materialService.findById(material.getId());
		materialService.privateGpa(theMat.getUser().getId());
	}
	
	@PostMapping("/ggpa")
	@CrossOrigin
	public float genralCalculator(@RequestBody Record2 record) {
		return materialService.generalGpa(record);
	}

}
