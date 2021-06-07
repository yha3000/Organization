package com.hyeon.organization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyeon.organization.model.Organization;
import com.hyeon.organization.repository.OrganizationRepository;

@Service
public class OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Transactional(readOnly = true)
	public List<Organization> findByParentIsNull() {
		return organizationRepository.findByParentIsNull();
	}
	
	@Transactional(readOnly = true)
	public List<Organization> findByNameContains(String name) {
		return organizationRepository.findByNameContains(name);
	}
	
	@Transactional(readOnly = true)
	public List<Organization> findByParentNode(Organization organization) {
		return organizationRepository.findByParentNode(organization.getId());
	}
	
	@Transactional(readOnly = true)
	public void deleteById(int id) {
		organizationRepository.deleteById(id);
	}
	
}