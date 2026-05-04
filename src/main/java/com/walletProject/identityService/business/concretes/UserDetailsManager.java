package com.walletProject.identityService.business.concretes;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.walletProject.identityService.models.entities.UserPrincipal;
import com.walletProject.identityService.models.entities.Users;
import com.walletProject.identityService.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsManager implements UserDetailsService {

	private final UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> user= userRepository.findByUsername(username);
		if(user.isEmpty()) {
			System.out.println("Kullanıcı bulunamadı");
			throw new UsernameNotFoundException("Kullanıcı adı bulunamadı");
		}
		return new UserPrincipal(user);
	}

}
