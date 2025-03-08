package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.UserLogin;
import com.example.demo.Entity.Userbot;
import com.example.demo.Service.ProductService;

@RestController
@RequestMapping("/auth")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	
	@PostMapping("/adduser")
	@ResponseStatus(code = HttpStatus.OK)
	public String addUser(@RequestBody Userbot userbot ) {
		
		return productService.addUser(userbot);
		
	}
	
	@PostMapping("/login")
	public String LoginUser(@RequestBody UserLogin userLogin) {
		return productService.Login(userLogin);
	}



}
