package com.mycompany.basespringmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@GetMapping("/hello")
	public String helloWithParam(@RequestParam(name="name", required=false, defaultValue="Mondo") String name, Model model) {
		model.addAttribute("name", name);
		return "hello";
	}
	
	
	@GetMapping("/hello/{name}")
	public String helloWithPathVariable(@PathVariable(name="name", required=false) String name, Model model) {
		model.addAttribute("name", name);
		return "hello";
	}

}
