package com.walletProject.identityService.webApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walletProject.identityService.business.abstracts.AuthService;
import com.walletProject.identityService.business.requests.LoginRequest;
import com.walletProject.identityService.business.requests.RegisterRequest;
import com.walletProject.identityService.business.responses.AuthResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //sadece final değişkenler için bir constructor üretiliyor
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService authService;
	
    @PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
    	AuthResponse authResponse= authService.register(registerRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
	}
	
	@PostMapping
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
		AuthResponse authResponse=authService.login(loginRequest);
		return ResponseEntity.ok(authResponse);
	}
	
}
