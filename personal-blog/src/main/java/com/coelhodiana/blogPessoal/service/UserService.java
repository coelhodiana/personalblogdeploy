package com.coelhodiana.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.coelhodiana.blogPessoal.model.User;
import com.coelhodiana.blogPessoal.model.UserLogin;
import com.coelhodiana.blogPessoal.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User UserRegister(User user) {
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		 
		 String passwordEncoder = encoder.encode(user.getPassword());
		 
		 user.setPassword(passwordEncoder);
		 
		 return repository.save(user);
	}
	
	public Optional<UserLogin> Login(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<User> usuario = repository.findByUser(user.get().getUser());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getPassword(), usuario.get().getPassword())) {
				
				String auth = user.get().getUser() + ":" + user.get().getPassword();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
 				String authHeader = "Basic " + new String(encodedAuth);
 				
 				user.get().setToken(authHeader);
 				user.get().setName(usuario.get().getName());
 				
 				return user;
			}
		}
		return null;
	}
}
