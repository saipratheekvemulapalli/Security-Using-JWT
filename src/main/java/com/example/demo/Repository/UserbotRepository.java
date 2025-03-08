package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Userbot;

@Repository
public interface UserbotRepository extends JpaRepository<Userbot, Integer> {
	
	public Optional<Userbot> findByName(String name);
	
	
}
