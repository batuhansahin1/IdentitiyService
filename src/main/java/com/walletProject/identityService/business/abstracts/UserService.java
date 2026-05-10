package com.walletProject.identityService.business.abstracts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walletProject.identityService.business.responses.GetAllUserResponse;

@Service
public interface UserService {

	List<GetAllUserResponse> getAll();


	
}
