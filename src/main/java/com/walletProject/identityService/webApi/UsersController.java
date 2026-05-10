package com.walletProject.identityService.webApi;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walletProject.identityService.business.abstracts.UserService;
import com.walletProject.identityService.business.responses.GetAllUserResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

	private final UserService userService;
	
	@GetMapping("/getAll")
	public List<GetAllUserResponse> getAll() {
		
		return userService.getAll();
	}
	
}
