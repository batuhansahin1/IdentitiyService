package com.walletProject.identityService.business.responses;

import java.time.LocalDateTime;

import com.walletProject.identityService.models.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllUserResponse {

	//private String username;
	private String email;
	private LocalDateTime updatedAt;
	private Role role;
	 
}
