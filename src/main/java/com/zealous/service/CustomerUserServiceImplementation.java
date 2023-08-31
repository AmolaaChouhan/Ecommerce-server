package com.zealous.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import com.zealous.model.User;
import com.zealous.repository.UserRepository;
@Service
public class CustomerUserServiceImplementation implements UserDetailsService{
	private UserRepository userRepository;
	
	public CustomerUserServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user =userRepository.findByEmail(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("user not found with email - "+username);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

}
