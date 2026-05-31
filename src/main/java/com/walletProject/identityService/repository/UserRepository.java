package com.walletProject.identityService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walletProject.identityService.models.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

	
	
 
	Optional<Users> findByEmail(String email);

    boolean existsByTcKimlik(String tcKimlik);
	
	boolean existsByEmail(String email);

	Optional<Users> findByTcKimlik(String tcKimlik);

}
