package com.walletProject.identityService.business.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@NotBlank(message = "Kullanıcı adı veya e-posta boş bırakılamaz")
	private String usernameOrEmail;

	@NotBlank(message = "Şifre boş bırakılamaz")
	private String password;

}
