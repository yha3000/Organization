package com.hyeon.organization.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyeon.organization.dto.OrganizationDTO;
import com.hyeon.organization.filter.SearchFilter;
import com.hyeon.organization.service.OrganizationService;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private EntityManager entityManager;
	
	@GetMapping
	public List<OrganizationDTO> getOrganization(SearchFilter filter) throws Exception {

		Session session = entityManager.unwrap(Session.class);
		List<OrganizationDTO> organization = null;
		
		// 부서만 deptOnly 파라미터에 따라 부서정보만 응답 할 수 있음.
		if(filter.getDeptOnly() != null && filter.getDeptOnly().equals("true")) {
			session.enableFilter("code").setParameter("CODE", "");
			organization = organizationService.findByParentIsNull().stream().map(OrganizationDTO::new).collect(Collectors.toList());
		} else if(filter.getDeptOnly() != null && filter.getDeptOnly().equals("false")) {
			organization = organizationService.findByParentIsNull().stream().map(OrganizationDTO::new).collect(Collectors.toList());
		}
		
		// 부서코드(deptCode) 파라미터를 추가했을 때 해당 부서를 포함하여 하위 부서를 응답한다.
		if(filter.getDeptCode() != null) { 
			session.enableFilter("code").setParameter("CODE", "");
			organization = organizationService.findByNameContains(filter.getDeptCode()).stream().map(OrganizationDTO::new).collect(Collectors.toList());
		}  
		
		// 부서원 검색 파라미터(searchType=member, searchKeyword) 추가시 검색된 부서원 과 부서원이 속한 부서 관계된 상위 부서를 포함한 트리 구조로 응답
		if(filter.getSearchType() != null && filter.getSearchType().equals("Member")) {
			organization = organizationService.findByNameContains(filter.getSearchKeyword()).stream().map(OrganizationDTO::new).collect(Collectors.toList());
		}
		
		// 부서 검색 파라미터(searchType=dept, searchKeyword) 추가시 검색된 부서들과 관계된 상위 부서를 포함한 트리 구조로 응답
		if(filter.getSearchType() != null && filter.getSearchType().equals("Dept")) {
			session.enableFilter("code").setParameter("CODE", "");
			organization = organizationService.findByNameContains(filter.getSearchKeyword()).stream().map(OrganizationDTO::new).collect(Collectors.toList());
		}
		
		return organization;
	}
	
//	@PostMapping
//	public void insertOrganization(@RequestBody Organization organization) throws Exception {
//	}

//	@PutMapping
//	public void updateOrganization(@RequestBody Organization organization) throws Exception {
//	}

//	@DeleteMapping("/{id}")
//	public void deleteOrganization(@PathVariable("id") int id) throws Exception {
//		organizationService.deleteById(id);
//	}
	
}