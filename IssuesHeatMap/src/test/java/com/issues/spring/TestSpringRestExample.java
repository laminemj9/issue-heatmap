package com.issues.spring;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.issues.spring.controller.IssueRestURIConstants;
import com.issues.spring.model.Employee;

public class TestSpringRestExample {

	public static final String SERVER_URI = "http://localhost:9090/IssuesHeatMap";
	
	public static void main(String args[]){
		
		
		System.out.println("*****");
		testGetAllEmployee();
		System.out.println("*****");
		testGetAllIssuesByRegionAndDate();
	}

	private static void testGetAllIssues() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<Issue> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		List<LinkedHashMap> issues = restTemplate.getForObject(SERVER_URI+EmpRestURIConstants.GET_ALL_ISSUES, List.class);
		System.out.println(issues.size());
		for(LinkedHashMap map : issues){
			System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",Description="+map.get("description")+",Status="+map.get("status")+",CreatedDate="+map.get("createdDate")+",ClosedDate="+map.get("closedDate"));;
		}
	}
	
	
	private static void testGetAllIssuesByRegionAndDate() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<Issue> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		
		UriComponentsBuilder builder = UriComponentsBuilder
    .fromUriString(SERVER_URI+EmpRestURIConstants.GET_ALL_ISSUES_BY_REGION_AND_DATE)
    // Add query parameter
    .queryParam("startdate", "10/1/2017")
    .queryParam("enddate", "10/1/2017")
    .queryParam("region", "USA");
	
		List<LinkedHashMap> issues = restTemplate.getForObject(builder, List.class);
		System.out.println(issues.size());
		for(LinkedHashMap map : issues){
			System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",Description="+map.get("description")+",Status="+map.get("status")+",CreatedDate="+map.get("createdDate")+",ClosedDate="+map.get("closedDate"));;
		}
	}

	
}
