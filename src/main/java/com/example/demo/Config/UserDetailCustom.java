package com.example.demo.Config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.Entity.Userbot;

public class UserDetailCustom implements UserDetails{
	
	
	
	private Optional<Userbot> userbot;
	
	public UserDetailCustom(Optional<Userbot> userbot2) {
		this.userbot = userbot2;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		
		return userbot.get().getPassword();
	}

	@Override
	public String getUsername() {
		
		return userbot.get().getName();
	}
	
	
	
}
