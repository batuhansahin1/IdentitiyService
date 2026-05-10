package com.walletProject.identityService.business.concretes;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.walletProject.identityService.business.abstracts.AuthService;
import com.walletProject.identityService.business.requests.LoginRequest;
import com.walletProject.identityService.business.requests.RegisterRequest;
import com.walletProject.identityService.business.responses.AuthResponse;
import com.walletProject.identityService.models.entities.Users;
import com.walletProject.identityService.models.enums.Role;
import com.walletProject.identityService.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {

	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authManager;
	private final BCryptPasswordEncoder bcrypt;
	
	@Override
	public AuthResponse login(@Valid LoginRequest loginRequest) {
	    try {
	
	    	authManager.authenticate(
	    			new UsernamePasswordAuthenticationToken(loginRequest.getUsername()
	    					, loginRequest.getPassword()));
		Optional<Users> userExistsUsername=userRepository.findByUsername(loginRequest.getUsername());
		if(!userExistsUsername.isPresent()) {
			throw new RuntimeException("Bu kullanıcı adına ait bir kişi bulunamadı");
		}
		

    	String token=jwtService.generateToken(loginRequest.getUsername());
		
		return new AuthResponse(token,"Başarıyla giriş yapılmıştır");
	    }catch(RuntimeException e){
	    	return AuthResponse.builder().token(null).message("Kullanıcı adı veya şifre yanlış")
	    			.build();
	    }
		
	}

	@Override
	public AuthResponse register(@Valid RegisterRequest registerRequest) {
		Users user=new Users();
		user.setEmail(registerRequest.getEmail());
		user.setPassword(bcrypt.encode( registerRequest.getPassword()));
		user.setUsername(registerRequest.getUsername());
		user.setCreatedAt( LocalDateTime.now());
		user.setRole(Role.ADMIN);
		userRepository.save(user);
		System.out.println("Register request geldi");
		AuthResponse authResponse=new AuthResponse();
		authResponse.setMessage("USER CREATED SUCCESSFULLY");
		String token=jwtService.generateToken(registerRequest.getUsername());
		authResponse.setToken(token);
		return authResponse;
	}

}
