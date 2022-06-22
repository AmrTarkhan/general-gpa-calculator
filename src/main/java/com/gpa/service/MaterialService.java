package com.gpa.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gpa.model.Material;
import com.gpa.model.User;
import com.gpa.payload.AddMaterial;
import com.gpa.payload.Record2;
import com.gpa.repository.MaterialRepository;


@Service
@Transactional
public class MaterialService {
	
	@Autowired
	private MaterialRepository materialRepo;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private UserService userService;

	@Autowired
	public MaterialService(MaterialRepository materialRepo, GradeService gradeService, UserService userService) {
		this.materialRepo = materialRepo;
		this.gradeService = gradeService;
		this.userService = userService;
	}
	
	public List<Material> findAll(){
		
		return materialRepo.findAll();
	}

	public Material findById(long theId) {
		Optional<Material> result = materialRepo.findById(theId);
		Material theMaterial = null;
		if(result.isPresent()){
			
			theMaterial = result.get();
		}
		else {
			throw new RuntimeException("Didn't find - " +theId );
		}
		return theMaterial;
	}
	
	public void delete(Material material) {
		
		materialRepo.delete(material);
	}
	
	
	public float generalGpa(Record2 all) {
		
		
		
		float lastpoints = (all.getLastCredits() * all.getLastGPA());
		float totalPoints =0;
		int totalCredits=0;
		int lastCredits =all.getLastCredits();
		float totalGPA=0;
		
		for(int i=0; i < all.getRecord().size() ; i++ ) {
			
			//New Material
			if(all.getRecord().get(i).getStatus() == 0) {
				
			totalPoints = totalPoints + ( all.getRecord().get(i).getCredits()) *
							gradeService.getGradePoints(all.getUniId(), all.getRecord().get(i).getGrade());
			
			totalCredits = totalCredits + all.getRecord().get(i).getCredits();
			}
			
			//Peviously faild Material
			else if(all.getRecord().get(i).getStatus() == 1) {
				
				totalPoints = totalPoints + ( all.getRecord().get(i).getCredits() *
						gradeService.getGradePoints(all.getUniId(), all.getRecord().get(i).getGrade()) );
			}
			
			//Improvement Material
			else if(all.getRecord().get(i).getStatus() == 2) {
				
				totalPoints = totalPoints - (all.getRecord().get(i).getCredits() *
						gradeService.getGradePoints(all.getUniId(), all.getRecord().get(i).getPrevGrade()))
										  + ( all.getRecord().get(i).getCredits() *
						gradeService.getGradePoints(all.getUniId(), all.getRecord().get(i).getGrade()) );
				
			}
			
		}
		totalCredits = totalCredits + lastCredits;
		totalPoints = totalPoints + lastpoints;
		
		totalGPA = totalPoints / totalCredits;
		System.out.println(totalCredits);
		return totalGPA;
	}
	
	
	
	
	public void privateGpa(long userId) {
	
	float totalGPA=0;
	int totalCredits=0;
	float totalpoints = 0;
	
	List<Material> all = materialRepo.findAll();
	List<Material> record = new ArrayList<Material>();
	for(int i=0; i < all.size(); i++) {
		if(userId == all.get(i).getUser().getId()) {
			record.add(all.get(i));
		}
	}
	
	for(int i=0; i < record.size() ; i++ ) {
		
		

		totalpoints = totalpoints + ( record.get(i).getCredit() *
						gradeService.getGradePoints(record.get(i).getUser().getUniversity().getId(), record.get(i).getGrade()) );
		
		totalCredits = totalCredits + record.get(i).getCredit();
		

		totalGPA = totalpoints / totalCredits;
		
		
		
	} 
			User user = userService.findById(userId);
			user.setGpa(totalGPA);
	}
	
	
	
	/*public float privateGpa(long userId) {
		
		float totalGPA=0;
		int totalCredits=0;
		float totalpoints = 0;
		
		List<Material> all = materialRepo.findAll();
		List<Material> record = new ArrayList<Material>();
		for(int i=0; i < all.size(); i++) {
			if(userId == all.get(i).getUser().getId()) {
				record.add(all.get(i));
			}
		}
		
		for(int i=0; i < record.size() ; i++ ) {
			
			if(record.get(i).getStatus() == 0) {

			totalpoints = totalpoints + ( record.get(i).getCredit() *
							gradeService.getGradePoints(record.get(i).getUser().getUniversity().getId(), record.get(i).getGrade()) );
			
			totalCredits = totalCredits + record.get(i).getCredit();
			}
			
			
			else if(record.get(i).getStatus() == 1) {
				//System.out.println(record.get(i));

				totalpoints = totalpoints + ( record.get(i).getCredit() *
						gradeService.getGradePoints(record.get(i).getUser().getUniversity().getId(), record.get(i).getGrade()) );
				totalCredits = totalCredits + record.get(i).getCredit();
			}
			
			
			else if(record.get(i).getStatus() == 2) {
				System.out.println(record.get(i));

				totalpoints = totalpoints - (record.get(i).getCredit() *
						gradeService.getGradePoints(record.get(i).getUser().getUniversity().getId(), record.get(i).getPrevGrade()))
										  + ( record.get(i).getCredit() *
						gradeService.getGradePoints(record.get(i).getUser().getUniversity().getId(), record.get(i).getGrade()) );
				totalCredits = totalCredits + record.get(i).getCredit();
			}
			
			
			
			
		}
	//	totalCredits = totalCredits + lastCredits;
	//	totalpoints = totalpoints + lastpoints;
	
		totalGPA = totalpoints / totalCredits;
			
			
			return totalGPA;
		}*/
		
		
	public List<Material> MaterialsOfUser(long userId){
		
		List<Material> all = materialRepo.findAll();
		List<Material> temp = new ArrayList<Material>();
		
		for (int i=0; i< all.size(); i++) {
			if(userId == all.get(i).getUser().getId() ) {
				temp.add(all.get(i));
			}
		}
			return temp;
	}
	
	public void addMaterial(AddMaterial payload) {
		
		User user = userService.findById(payload.getUserId());
		Material material = new Material(payload.getMaterialName(), payload.getGrade(), payload.getCredit(),
										0, user);
		materialRepo.save(material);
	}
	
	
public void editMaterial(Material theMat) {
		
		//User user = userService.findById(theMat.getUserId());
		Optional<Material> material = materialRepo.findById(theMat.getId());
		
		if (theMat.getCredit() != null)
			material.get().setCredit(theMat.getCredit());
		if (theMat.getGrade()!= null)
			material.get().setGrade(theMat.getGrade());
		if (theMat.getMaterialName() != null)
			material.get().setMaterialName(theMat.getMaterialName());
		if (theMat.getPrevGrade() != null)
			material.get().setPrevGrade(theMat.getPrevGrade());
		if (theMat.getStatus() != null)
			material.get().setStatus(theMat.getStatus());
		
		
		
		materialRepo.save(material.get());
	}
	
	
	
	
}
