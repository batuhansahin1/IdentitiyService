package com.walletProject.identityService.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.walletProject.identityService.business.abstracts.UserService;
import com.walletProject.identityService.business.responses.GetAllUserResponse;
import com.walletProject.identityService.core.utilities.mappers.concretes.UsersMapper;
import com.walletProject.identityService.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

	private final UserRepository userRepository;
	private final UsersMapper userMapper;
	
	@Override
	public List<GetAllUserResponse> getAll() {
		// TODO Auto-generated method stub
		GetAllUserResponse userResponse=new GetAllUserResponse();
		List<GetAllUserResponse> userResponseList=this.userRepository.findAll().stream()
				.map(user->userMapper.userToGetAllUserResponse(user)).collect(Collectors.toList());
		
		
		return userResponseList;
	}

}
