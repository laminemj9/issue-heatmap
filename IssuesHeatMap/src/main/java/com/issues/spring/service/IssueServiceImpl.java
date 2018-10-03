package com.issues.spring.service;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.issues.spring.model.Isssue;
 
@Service("issueService")
@Transactional
public class IssueServiceImpl implements IssueService{
     
   
     
    private static List<Issue> issues;
     
    
 
   
    public static List<User> getAllIssues(){
        
		// code to get data from  database needs more time to write that
        return issues;
    }
	 public static List<User> getAllIssuesByRegionAndDate(string startDate,string endDate, string  region){
        
		// code to get data from  database needs more titme to write there
        return issues;
    }
	
 
   
 
}