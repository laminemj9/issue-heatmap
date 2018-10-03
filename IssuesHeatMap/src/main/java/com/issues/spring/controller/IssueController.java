package com.issues.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.issues.spring.model.Issue;
import com.issues.spring.model.ErrorResponse;
import com.issues.exception.IssueException;
import com.issues.spring.service.IssueService;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class IssueController {
	
	private static final Logger logger = LoggerFactory.getLogger(IssueController.class);
	
	 @Autowired
    IssueService issueService;  //
	
	//Map to store issues, ideally we should use database
	Map<Integer, Issue> issueData = new HashMap<Integer, Issue>();
	
	
	@RequestMapping(value = IssueRestURIConstants.GET_ALL_ISSUES, method = RequestMethod.GET)
	public @ResponseBody List<Issue> getAllIssues()  throws IssueException {
		
		try
		{
			logger.info("Start getAllIssues.");
			List<Issue> issueData = issueService.getAllIssues();
			Set<Integer> issueIdKeys = issueData.keySet();
			for(Integer i : issueIdKeys){
				issues.add(issueData.get(i));
			}
			if(issueData!=null && issueData.size()>0)
			{
					 throw new IssueException("No isssues found!");

			}
			return issues;
		}
		catch(Exception ex)
		{
			logger.info("Exception :"+ex.message);
			throw ex;
		}
	}
	
	
	@RequestMapping(value = IssueRestURIConstants.GET_ALL_ISSUES_BY_REGION_AND_DATE, method = RequestMethod.GET)
	public @ResponseBody List<Issue> getAllIssuesByRegionAndDate((@RequestParam("startdate") string startDate,(@RequestParam("enddate") string endDate,(@RequestParam("region") string region)  throws IssueException
		try
		{
			logger.info("Start getAllIssues By Date and Region.");
			List<Issue> issueData = issueService.getAllIssuesByRegionAndDate(startDate,endDate, region);
			Set<Integer> issueIdKeys = issueData.keySet();
			for(Integer i : issueIdKeys){
				issues.add(issueData.get(i));
			}
			if(issueData!=null && issueData.size()>0)
			{
					 throw new IssueException("No isssues found!");

			}
			return issues;
		}
		catch(Exception ex)
		{
			logger.info("Exception :"+ex.message);
			throw ex;
		}
	}
	
	
	 @ExceptionHandler(IssueException.class)

    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {

        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

    }

	
	 @ExceptionHandler(Exception.class)

    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {

        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage("Please contact your administrator");
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

    }


	
}
