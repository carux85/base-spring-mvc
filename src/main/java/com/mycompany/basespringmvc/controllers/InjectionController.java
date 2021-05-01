package com.mycompany.basespringmvc.controllers;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.basespringmvc.models.SimpleBean;
import com.mycompany.basespringmvc.models.SimpleComponent;

@Controller
@RequestMapping("/injections")
public class InjectionController {
	
	@Resource(name="defaultSimpleBean")
	//@Qualifier("defaultSimpleBean")
	private SimpleBean resourseByType;
	
	@Resource(name="firstSimpleBean")
	private SimpleBean resourseByName1;
	
	@Resource(name="secondSimpleBean")
	private SimpleBean resourseByName2;

	@Inject
	@Qualifier("defaultSimpleBean")
	private SimpleBean injectByType;
	
	@Inject
	@Named("firstSimpleBean")
	private SimpleBean injectByName1;
	
	@Inject
	@Named("secondSimpleBean")
	private SimpleBean injectByName2;
	
	@Autowired
	@Qualifier("defaultSimpleBean")
	private SimpleBean autowiredByType;
	
	@Autowired
	@Named("firstSimpleBean")
	private SimpleBean autowiredByName1;
	
	@Autowired
	@Named("secondSimpleBean")
	private SimpleBean autowiredByName2;
	
	@Autowired
	private SimpleComponent simpleComponent;
	
	@GetMapping
	public String injections(Model model) {
	
		model.addAttribute("resourseByType", resourseByType);
		model.addAttribute("resourseByName1", resourseByName1);
		model.addAttribute("resourseByName2", resourseByName2);
		
		model.addAttribute("injectByType", injectByType);
		model.addAttribute("injectByName1", injectByName1);
		model.addAttribute("injectByName2", injectByName2);
		
		model.addAttribute("autowiredByType", autowiredByType);
		model.addAttribute("autowiredByName1", autowiredByName1);
		model.addAttribute("autowiredByName2", autowiredByName2);
		
		model.addAttribute("simpleComponent", simpleComponent);
		
		return "injections";
	}
}
