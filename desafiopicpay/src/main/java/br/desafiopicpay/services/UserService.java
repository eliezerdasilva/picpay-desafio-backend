package br.desafiopicpay.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.desafiopicpay.dtos.UserDTO;
import br.desafiopicpay.enums.UserType;
import br.desafiopicpay.models.User;
import br.desafiopicpay.repositories.UserRepository;
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void validationTransaction(User sender, BigDecimal amount) throws Exception {
		if(sender.getUserType()  == UserType.MERCHANT) {
			throw new Exception("O cliente Lojista não esta autorizado para realizar a transação");
		}
		if(sender.getBalance().compareTo(amount)<0) {
			throw new Exception("Saldo insuficiente");
		}
		
	}
	public User findUserById(Long id) throws Exception{
		return this.userRepository.findUserById(id).orElseThrow(()-> new Exception("Usuário não encontrado"));
	}
	public User createUser(UserDTO user) {
		User newUser = new User(user);
		this.saveUser(newUser);
		return newUser;
	}
	
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	public void saveUser(User user) {
		this.userRepository.save(user);
	}
}
