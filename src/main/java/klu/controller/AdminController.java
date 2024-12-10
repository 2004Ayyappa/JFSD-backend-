package klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import klu.model.Admin;
import klu.model.AdminManager;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
AdminManager AM;

	
	@GetMapping("/login/{uname}/{pwd}")
	public String login(@PathVariable("uname") String un, @PathVariable("pwd") String pw)
	{
		return AM.loginAdmin(un, pw);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Admin A)
	{
		return AM.loginAdmin(A.getUsername(), A.getPassword());
		
	}
	

	@PostMapping("/save")
	public String save(@RequestBody Admin A)
	{
		return AM.saveAdmin(A);
	}
	
	
}
