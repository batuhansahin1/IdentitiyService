package com.walletProject.identityService.models.entities;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    private final Optional<Users> user;	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.get().getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.get().getUsername();
	}



}
