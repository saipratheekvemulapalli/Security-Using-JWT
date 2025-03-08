package com.example.demo.Config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.Entity.Userbot;
import com.example.demo.Repository.UserbotRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserbotRepository userbotRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Userbot> userbot = userbotRepository.findByName(username);
		
		if(userbot==null) {
			throw new UsernameNotFoundException("User with the name: " + username + "doesn't exist.");
		}
		return new UserDetailCustom(userbot);
		
	}

}
