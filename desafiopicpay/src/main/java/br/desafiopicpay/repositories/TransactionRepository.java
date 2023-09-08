package br.desafiopicpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.desafiopicpay.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	
}
