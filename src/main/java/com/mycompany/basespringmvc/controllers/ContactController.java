package com.mycompany.basespringmvc.controllers;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.basespringmvc.models.ContactForm;


@Controller
@RequestMapping("/contact")
public class ContactController {
	
	@Resource(name="defaultContactForm")
	private ContactForm contactForm;

	@GetMapping
	public ModelAndView showContact(Model model) {
		//Equivalent to:
		//model.addAttribute("contactform", contactForm);
		//return "contact";	
		return new ModelAndView("contact", "contactform", contactForm);
	}
	
	@PostMapping
	public String sendEmail(@Valid @ModelAttribute("contactform")ContactForm contactform, BindingResult result, Model model) {
		
		if (result.hasErrors()) {			
			model.addAttribute("error", "Uncorrect values!");
            return "contact";
        }
		
		model.addAttribute("name", contactform.getName());
		return "confirmContact";
	}
}
