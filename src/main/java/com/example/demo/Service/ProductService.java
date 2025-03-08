package com.example.demo.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.DTO.Products;
import com.example.demo.Entity.UserLogin;
import com.example.demo.Entity.Userbot;
import com.example.demo.Repository.UserbotRepository;

@Service
public class ProductService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserbotRepository userbotRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTService jwtService;
	
	List<Products> productlist=null;
	
	
	
	public void loadProductList() {
		productlist = IntStream.rangeClosed(1, 100)
				.mapToObj(o->Products.builder()
						.id(o)
						.productName("productname " + o)
						.price(new Random().nextInt(2000))
						.quantity(new Random().nextInt(9))
						.build()
				).collect(Collectors.toList());
					
				
	}
	
	public Products getProductById(int id) {
		return productlist.stream().filter(i->i.getId()==id).findAny().orElseThrow(()-> new RuntimeException("There is no product with the Id: " + id));
	}
	
	
	public List<Products> getAllProducts(){
		return productlist;
	}

	public String addUser(Userbot userbot) {
		
		Optional<Userbot> userbo = userbotRepository.findByName(userbot.getName());
		
		if(userbo!=null) {
			throw new UsernameNotFoundException("user already registered in the db.");
			
		}
		userbot.setPassword(passwordEncoder.encode(userbot.getPassword()));
		userbotRepository.save(userbot);
		return "user Added Successfully";

	}
	
	@RequestMapping("/login")
	public String Login(UserLogin userlogin) {
		Authentication authen = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userlogin.getName(), userlogin.getPassword()));
		if(authen.isAuthenticated()) {
			return jwtService.generateToken(userlogin.getName());
		}
		return null;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
