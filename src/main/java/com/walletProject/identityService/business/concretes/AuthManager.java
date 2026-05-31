package com.walletProject.identityService.business.concretes;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.walletProject.identityService.business.abstracts.AuthService;
import com.walletProject.identityService.business.requests.LoginRequest;
import com.walletProject.identityService.business.requests.RegisterRequest;
import com.walletProject.identityService.business.responses.AuthResponse;
import com.walletProject.identityService.core.utilities.config.RabbitMQConfig;
import com.walletProject.identityService.messaging.events.UserRegisteredEvent;
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
	private final RabbitTemplate rabbitTemplate; // RabbitMQ tetikleyicisi
	
	@Override
	public AuthResponse login(@Valid LoginRequest loginRequest) {
	    try {
	
	    	authManager.authenticate(
	    			new UsernamePasswordAuthenticationToken(loginRequest.getTcKimlik()
	    					, loginRequest.getPassword()));
		Optional<Users> userExistsUsername=userRepository.findByTcKimlik(loginRequest.getTcKimlik());
		if(!userExistsUsername.isPresent()) {
			throw new RuntimeException("Bu kullanıcı adına ait bir kişi bulunamadı");
		}
		

    	String token=jwtService.generateToken(loginRequest.getTcKimlik());
		
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
		user.setTcKimlik(registerRequest.getTcKimlik());
		user.setCreatedAt( LocalDateTime.now());
		user.setRole(Role.ADMIN);
		userRepository.save(user);
		System.out.println("Register request geldi");
		// 2. Core Banking için Event oluştur
        UserRegisteredEvent event = new UserRegisteredEvent(
            registerRequest.getTcKimlik(),
            registerRequest.getFirstName(),
            registerRequest.getLastName(),
            registerRequest.getEmail(),
            registerRequest.getType()
        );

        // 3. Mesajı RabbitMQ'ya gönder (Exchange ve Routing Key ayarlarına göre)
        rabbitTemplate.convertAndSend(
        	    RabbitMQConfig.EXCHANGE,    // Config'deki değişkeni kullan
        	    RabbitMQConfig.ROUTING_KEY, // Config'deki değişkeni kullan
        	    event
        	);
		AuthResponse authResponse=new AuthResponse();
		authResponse.setMessage("USER CREATED SUCCESSFULLY");
		String token=jwtService.generateToken(registerRequest.getTcKimlik());
		authResponse.setToken(token);
		return authResponse;
	}

}
