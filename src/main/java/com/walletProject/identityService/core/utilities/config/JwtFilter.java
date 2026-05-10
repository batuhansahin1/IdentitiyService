package com.walletProject.identityService.core.utilities.config;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.walletProject.identityService.business.concretes.JwtService;
import com.walletProject.identityService.business.concretes.UserDetailsManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
	private final JwtService jwtService;
	private final ApplicationContext context;
	
	//jwt'nin süresini artır
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authHeader=request.getHeader("Authorization");
		System.out.println(authHeader);
	    String token=null;
	    String username=null;
		
		if(authHeader!= null&& authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7);
			username=jwtService.extractUsername(token);
		}
		if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null ) {
			//username null olmicak ve authenticate edilmiş bir şey olmayacak
			//token geçerliyse biz authentication object oluşturacağız
			UserDetails userDetails=context.getBean(UserDetailsManager.class).loadUserByUsername(username);
			if(jwtService.validateToken(token,userDetails)) {
				UsernamePasswordAuthenticationToken authToken=
						new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			
		}
	
		filterChain.doFilter(request, response);
	}
}
