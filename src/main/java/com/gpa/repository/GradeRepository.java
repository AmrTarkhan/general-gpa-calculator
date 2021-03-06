package com.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpa.model.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

}
