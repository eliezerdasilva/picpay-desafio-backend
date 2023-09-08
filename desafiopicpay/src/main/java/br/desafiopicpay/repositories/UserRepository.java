package br.desafiopicpay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.desafiopicpay.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User>  findUserByDocument(String document);
	
	Optional<User> findUserById(Long id);

}
