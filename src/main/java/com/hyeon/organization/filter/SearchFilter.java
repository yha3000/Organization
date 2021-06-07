package com.hyeon.organization.filter;

import lombok.Data;

@Data
public class SearchFilter {
	
	private String deptCode;
	private String deptOnly;
	private String searchType;
	private String searchKeyword;
	
}