package com.walletProject.identityService.business.abstracts;

import com.walletProject.identityService.business.requests.LoginRequest;
import com.walletProject.identityService.business.requests.RegisterRequest;
import com.walletProject.identityService.business.responses.AuthResponse;

import jakarta.validation.Valid;

public interface AuthService {

	AuthResponse login(@Valid LoginRequest loginRequest);

	AuthResponse register(@Valid RegisterRequest registerRequest);

}
