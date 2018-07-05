package com.example.spring.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.spring.demo.model.Customer;
import com.example.spring.demo.service.CustomerService;

@Controller
public class DemoController {

	@Autowired
    CustomerService customerService;
	
	@Value("${spring.application.name}")
    String appName;
 
    @GetMapping("/customer")
    public String homePage(Model model) {
        model.addAttribute("appName", ".:ATO:.");
        return "home";
    }
    
    @GetMapping("/add")
    public String add(Model model) {
    	model.addAttribute("customer", new Customer());
        return "form";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
    	Customer customer= customerService.getCustomer(id);
    	model.addAttribute("customer", customer);
        return "form";
    }
}
