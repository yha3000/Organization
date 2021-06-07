package com.hyeon.organization.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.hyeon.organization.model.Organization;
import com.hyeon.organization.model.OrganizationType;

import lombok.Getter;

@Getter
public class OrganizationDTO {
	
	private int id;
	private String name;
	private OrganizationType type;
	private String code;
	private List<OrganizationDTO> children;
	
	public OrganizationDTO(final Organization organization) {
		this.id = organization.getId();
		this.name = organization.getName();
		this.type = organization.getType();
		this.code = organization.getCode();
		this.children = organization.getChildren().stream().map(OrganizationDTO::new).collect(Collectors.toList());
	}
	
}