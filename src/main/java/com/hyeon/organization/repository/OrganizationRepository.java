package com.hyeon.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyeon.organization.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

	List<Organization> findByParentIsNull();
	List<Organization> findByNameContains(String name);
	
}