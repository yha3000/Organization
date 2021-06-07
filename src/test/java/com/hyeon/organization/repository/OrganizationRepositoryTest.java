package com.hyeon.organization.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hyeon.organization.model.Organization;
import com.hyeon.organization.model.OrganizationType;

@RunWith(SpringRunner.class)
@DataJpaTest
class OrganizationRepositoryTest {

	@Autowired
	private OrganizationRepository organizationRepository;

	public void cleanup(){
		organizationRepository.deleteAll();
    }
	
	@Test
	public void whenFindByParentIsNull() {
		int id = 1;
		String name = "TEST";
		String code = "A1234";
		organizationRepository.save(Organization.builder()
							.id(id)
							.name(name)
							.type(OrganizationType.Company)
							.code(code)
							.parent(null)
							.build());
		
		List<Organization> organization = organizationRepository.findByParentIsNull();
		
		assertNotNull(organization);
		assertEquals(organization.get(0).getId(), id);
		assertEquals(organization.get(0).getName(), name);
		assertEquals(organization.get(0).getCode(), code);
	}
	
	@Test
	public void whenFindByNameContains() {
		int id = 1;
		String name = "TEST";
		String code = "A1234";
		organizationRepository.save(Organization.builder()
							.id(id)
							.name(name)
							.type(OrganizationType.Company)
							.code(code)
							.parent(null)
							.build());
		
		List<Organization> organization = organizationRepository.findByNameContains(name);
		
		assertNotNull(organization);
		assertEquals(organization.get(0).getId(), id);
		assertEquals(organization.get(0).getName(), name);
		assertEquals(organization.get(0).getCode(), code);
	}
}