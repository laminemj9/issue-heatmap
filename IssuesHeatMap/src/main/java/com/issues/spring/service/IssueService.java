package com.issues.spring.service;
 
import java.util.List;
 
import com.issues.spring.model.Issue;
 
 
 
public interface IssueService {
     
  
 
    List<Issue> getAllIssues(); 
     
   List<Issue> getAllIssuesByRegionAndDate(string startDate,string endDate, string region); 
     
}