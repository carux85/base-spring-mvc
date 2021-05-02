package com.mycompany.basespringmvc.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.basespringmvc.exceptions.CustomException;

@Controller
@RequestMapping("errorgen")
public class ErrorGeneratorController {
	
	@Autowired
	Logger logger;

	@GetMapping
	public String generic() throws Exception{
		
		throw new Exception("Generic error");	
	}
	
	@GetMapping("/conflict")
	public String conflict() throws Exception{
		
		throw new DataIntegrityViolationException("Conflict error");	
	}
	
	@GetMapping("/database")
	public String database() throws Exception{
		
		throw new SQLException("SQL error");	
	}
	
	@GetMapping("/custom")
	public String custom() throws Exception{
		
		throw new CustomException();
	}
	
	/*
	// Convert a predefined exception to an HTTP Status code
	  @ResponseStatus(value=HttpStatus.CONFLICT, reason="Data integrity violation")  // 409
	  @ExceptionHandler(DataIntegrityViolationException.class)
	  public void conflictError() {
	    // Nothing to do
	  }
	  
	  // Specify name of a specific view that will be used to display the error:
	  @ExceptionHandler({SQLException.class, DataAccessException.class})
	  public String databaseError() {
	    // Nothing to do.  Returns the logical view name of an error page, passed
	    // to the view-resolver(s) in usual way.
	    // Note that the exception is NOT available to this view (it is not added
	    // to the model) but see "Extending ExceptionHandlerExceptionResolver"
	    // below.
	    return "databaseError";
	  }

	  // Total control - setup a model and return the view name yourself. Or
	  // consider subclassing ExceptionHandlerExceptionResolver (see below).
	  @ExceptionHandler(Exception.class)
	  public ModelAndView handleError(HttpServletRequest req, Exception ex) {
		
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", ex);
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName("error");
	    return mav;
	  }
	  */
}
