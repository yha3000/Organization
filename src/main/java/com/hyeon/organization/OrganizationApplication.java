package com.hyeon.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hyeon.organization.model.Organization;
import com.hyeon.organization.model.OrganizationType;
import com.hyeon.organization.repository.OrganizationRepository;

@SpringBootApplication
public class OrganizationApplication implements CommandLineRunner {

	@Autowired
	private OrganizationRepository organizationRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(OrganizationApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception { 
		organizationRepository.save(new Organization(1, "0000", "ABC회사", OrganizationType.Company, null));	
		organizationRepository.save(new Organization(2, "A100", "경영지원본부", OrganizationType.Division, new Organization(1)));
		organizationRepository.save(new Organization(3, "A110", "인사팀", OrganizationType.Department, new Organization(2)));
		organizationRepository.save(new Organization(4, "A120", "총무팀", OrganizationType.Department, new Organization(2)));
		organizationRepository.save(new Organization(5, "A130", "법무팀", OrganizationType.Department, new Organization(2)));
		organizationRepository.save(new Organization(6, "B100", "SW 개발본부", OrganizationType.Division, new Organization(1)));
		organizationRepository.save(new Organization(7, "B110", "플랫폼개발부", OrganizationType.Department, new Organization(6)));
		organizationRepository.save(new Organization(8, "B111", "비즈플랫폼팀", OrganizationType.Department, new Organization(7)));
		organizationRepository.save(new Organization(9, "B112", "비즈서비스팀", OrganizationType.Department, new Organization(7)));
		organizationRepository.save(new Organization(10, "B113", "그룹웨어개발팀", OrganizationType.Department, new Organization(7)));
		organizationRepository.save(new Organization(11, "B120", "비즈서비스개발부", OrganizationType.Department, new Organization(6)));
		organizationRepository.save(new Organization(12, "B121", "플랫폼서비스팀", OrganizationType.Department, new Organization(11)));
		organizationRepository.save(new Organization(13, "B122", "모바일개발팀", OrganizationType.Department, new Organization(11)));
		
		organizationRepository.save(new Organization(null, "사장1", OrganizationType.Member, new Organization(1)));
		organizationRepository.save(new Organization(null, "경영1", OrganizationType.Member, new Organization(2)));
		organizationRepository.save(new Organization(null, "인사1", OrganizationType.Member, new Organization(3)));
		organizationRepository.save(new Organization(null, "인사2", OrganizationType.Member, new Organization(3)));
		organizationRepository.save(new Organization(null, "인사3", OrganizationType.Member, new Organization(3)));
		organizationRepository.save(new Organization(null, "총무1", OrganizationType.Member, new Organization(4)));
		organizationRepository.save(new Organization(null, "총무2", OrganizationType.Member, new Organization(4)));
		organizationRepository.save(new Organization(null, "법무1", OrganizationType.Member, new Organization(5)));
		organizationRepository.save(new Organization(null, "법무2", OrganizationType.Member, new Organization(5)));
		organizationRepository.save(new Organization(null, "SW1", OrganizationType.Member, new Organization(6)));
		organizationRepository.save(new Organization(null, "플랫폼1", OrganizationType.Member, new Organization(7)));
		organizationRepository.save(new Organization(null, "플랫폼1", OrganizationType.Member, new Organization(8)));
		organizationRepository.save(new Organization(null, "개발1", OrganizationType.Member, new Organization(8)));
		organizationRepository.save(new Organization(null, "개발2", OrganizationType.Member, new Organization(8)));
		organizationRepository.save(new Organization(null, "개발3", OrganizationType.Member, new Organization(9)));
		organizationRepository.save(new Organization(null, "개발4", OrganizationType.Member, new Organization(9)));
		organizationRepository.save(new Organization(null, "개발5", OrganizationType.Member, new Organization(10)));
		organizationRepository.save(new Organization(null, "개발6", OrganizationType.Member, new Organization(10)));
		organizationRepository.save(new Organization(null, "서비스1", OrganizationType.Member, new Organization(11)));
		organizationRepository.save(new Organization(null, "개발7", OrganizationType.Member, new Organization(12)));
		organizationRepository.save(new Organization(null, "개발8", OrganizationType.Member, new Organization(12)));
		organizationRepository.save(new Organization(null, "개발9", OrganizationType.Member, new Organization(13)));
		organizationRepository.save(new Organization(null, "개발10", OrganizationType.Member, new Organization(13)));
	}
	
}