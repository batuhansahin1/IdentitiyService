package com.walletProject.identityService.business.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	@NotBlank(message = "Kullanıcı adı boş bırakılamaz")
    @Size(min = 3, max = 50, message = "Kullanıcı adı 3 ile 50 karakter arasında olmalıdır")
	private String username;
	
	@NotBlank(message = "Şifre boş bırakılamaz")
	@Size(min = 6, message = "Şifre en az 6 karakter olmalıdır")
	private String password;
	
    @NotBlank(message = "E-posta boş bırakılamaz")
    @Email(message = "Geçerli bir e-posta adresi giriniz")
	private String email;
	
	
	
}
