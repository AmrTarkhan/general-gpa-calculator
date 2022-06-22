package com.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpa.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

}
