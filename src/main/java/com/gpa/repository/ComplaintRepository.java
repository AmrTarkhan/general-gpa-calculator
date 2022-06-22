package com.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpa.model.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

}
