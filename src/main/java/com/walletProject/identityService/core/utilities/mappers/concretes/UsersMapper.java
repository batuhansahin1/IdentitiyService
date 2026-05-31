package com.walletProject.identityService.core.utilities.mappers.concretes;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.walletProject.identityService.business.responses.GetAllUserResponse;
import com.walletProject.identityService.models.entities.Users;


@Mapper(componentModel = "spring")
public interface UsersMapper {

	
	//@Mapping(source = "tcKimlik",target = "tcKimlik")
	@Mapping(source = "email",target = "email")
	@Mapping(source = "updatedAt",target = "updatedAt")
	@Mapping(source = "role",target = "role")
	GetAllUserResponse userToGetAllUserResponse(Users user);
	
	
	
	
	
}
