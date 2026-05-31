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

	@NotBlank(message = "TC kimlik no boş bırakılamaz")
    @Size(min = 11, max = 11, message = "TC kimlik no 11 karakter olmalıdır")
	private String tcKimlik;
	
	@NotBlank(message = "Şifre boş bırakılamaz")
	@Size(min = 6, message = "Şifre en az 6 karakter olmalıdır")
	private String password;
	
    @NotBlank(message = "E-posta boş bırakılamaz")
    @Email(message = "Geçerli bir e-posta adresi giriniz")
	private String email;
    @NotBlank(message = "İsim boş bırakılamaz")
    private String firstName;
    @NotBlank(message = "Soyisim boş bırakılamaz")
	private String lastName;
    @NotBlank(message = "Müşteri türü boş bırakılamaz")
	private String type;

	
	
	
}
