package com.walletProject.identityService.business.concretes;

import org.springframework.stereotype.Service;

import com.walletProject.identityService.business.abstracts.AuthService;
import com.walletProject.identityService.business.requests.LoginRequest;
import com.walletProject.identityService.business.requests.RegisterRequest;
import com.walletProject.identityService.business.responses.AuthResponse;
import com.walletProject.identityService.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {

	private final UserRepository userRepository;
	
	@Override
	public AuthResponse login(@Valid LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthResponse register(@Valid RegisterRequest registerRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
