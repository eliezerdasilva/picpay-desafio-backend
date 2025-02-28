package br.desafiopicpay.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.desafiopicpay.dtos.TransactionDTO;
import br.desafiopicpay.models.Transaction;
import br.desafiopicpay.models.User;
import br.desafiopicpay.repositories.TransactionRepository;
@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private NotificationService notificationService;
	
	public Transaction createTransaction(TransactionDTO transaction) throws Exception {
		User sender = this.userService.findUserById(transaction.senderId());
		User receiver = this.userService.findUserById(transaction.receiverId());
	
		userService.validationTransaction(sender, transaction.value());
		
		boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
		if(!isAuthorized) {
			throw new Exception("Transação não autorizada");
		}
		Transaction newtransaction = new Transaction();
		newtransaction.setAmount(transaction.value());
		newtransaction.setSender(sender);
		newtransaction.setReceiver(receiver);
		newtransaction.setTimestamp(LocalDateTime.now());
		
		
		sender.setBalance(sender.getBalance().subtract(transaction.value()));
		receiver.setBalance(receiver.getBalance().add(transaction.value()));
		
		this.transactionRepository.save(newtransaction);
		this.userService.saveUser(sender);
		this.userService.saveUser(receiver);
		this.notificationService.sendNotification(sender, "Transação realizada com sucesso");
		this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");
		
		
		return newtransaction;
	}
	public boolean authorizeTransaction(User sender, BigDecimal value) {
		ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",Map.class);
		
		System.out.println(authorizationResponse);
		System.out.println(authorizationResponse.getBody().get("message"));
		if(authorizationResponse.getStatusCode()== HttpStatus.OK){
			String message = (String) authorizationResponse.getBody().get("message"); 
			System.out.println("Entrei");
			return "Autorizado".equalsIgnoreCase(message);
		}else
		return false; 
	}
	
}
