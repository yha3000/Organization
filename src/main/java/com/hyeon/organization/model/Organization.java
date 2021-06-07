package com.hyeon.organization.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@FilterDef(name = "code", parameters={ @ParamDef(name="CODE",type="string") })
public class Organization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private OrganizationType type;

	@Column(nullable = true, length = 4)
	private String code;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent")
	@JsonBackReference
	private Organization parent;
	
	@Filter(name = "code", condition = "code is not null")
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	@JsonManagedReference
	private List<Organization> children = new ArrayList<Organization>(); 
	
	public Organization (int id) {
		this.id = id;
	} 
	
	@Builder
	public Organization (int id, String code, String name, OrganizationType type, Organization parent) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.code = code;
		this.parent = parent;
	}
	
	@Builder
	public Organization (String code, String name, OrganizationType type, Organization parent) {
		this.name = name;
		this.type = type;
		this.code = code;
		this.parent = parent;
	}
	
}