package com.walletProject.identityService.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@NotBlank(message = "Kullanıcı adı  boş bırakılamaz")
	@Size(min = 11, max = 11, message = "TC kimlik no 11 karakter olmalıdır")
	private String tcKimlik;

	@NotBlank(message = "Şifre boş bırakılamaz")
	private String password;

}
