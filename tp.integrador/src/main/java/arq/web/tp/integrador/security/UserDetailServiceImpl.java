package arq.web.tp.integrador.security;/*
package com.challengeDrive.challenge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.challengeDrive.challenge.dto.UserDTO;
import com.challengeDrive.challenge.entity.UserEntity;
import com.challengeDrive.challenge.repository.UserRepository;



@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity userEntity =userRepository
						.findOneByEmail(email)
						.orElseThrow(()-> new UsernameNotFoundException("El usuario con email " + email+ " no existe."));
		UserDTO user = UserDTO.builder()
				.id(userEntity.getId())
				.name(userEntity.getName())
				.email(userEntity.getEmail())
				.password(userEntity.getPassword())
				.build();
		return new UserDetailsImpl(user);
	}
	
	
}
*/
