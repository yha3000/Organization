package com.hyeon.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hyeon.organization.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

	public List<Organization> findByParentIsNull();
	public List<Organization> findByNameContains(String name);
	
	@Query(value="SELECT CONCAT(REPEAT('  ', level  - 1), d.name) AS name, d.id, d.parent, func.level FROM ("
				+ " SELECT B._id, @lv2 \\:= @lv2 + 1 AS level"
				+ "	FROM ( SELECT @r AS _id, ( SELECT @r \\:= parent  FROM organization  WHERE id = _id ) AS parent, @l \\:= @l +1 AS lv"
				+ " FROM ( SELECT @r \\:= ?1, @l \\:= 0 ) vars, organization d"
				+ " WHERE @r <> 0"
				+ " ORDER BY lv DESC"
				+ " ) B, (SELECT @lv2 \\:= 0 ) vars2"
				+ " ) func"
				+ " Join organization d "
				+ " ON func._id = d.id", nativeQuery = true)
	public List<Organization> findByParentNode(int id);
	
}