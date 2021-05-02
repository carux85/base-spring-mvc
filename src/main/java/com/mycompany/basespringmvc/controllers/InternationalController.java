package com.mycompany.basespringmvc.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class InternationalController  {
	
	@Autowired
    private MessageSource messageSource;
	
	@Autowired
	private Logger logger;

    @GetMapping("/international")
    public String getInternationalPage(Locale locale) {
    	
    	logger.info(messageSource.getMessage("lang.change",null,locale));
    	
        return "international";
    }
}
