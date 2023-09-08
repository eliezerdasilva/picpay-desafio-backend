package br.desafiopicpay.models;

import java.math.BigDecimal;

import br.desafiopicpay.dtos.UserDTO;
import br.desafiopicpay.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class User {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String document;

	@Column(unique = true)
	private String email;

	private String password;

	private BigDecimal balance;

	@Enumerated(EnumType.STRING)
	private UserType userType;

	public  User(UserDTO userDTO) {
		this.firstName = userDTO.firstName();
		this.lastName = userDTO.lastName();
		this.balance = userDTO.balance();
		this.userType = userDTO.userType();
		this.document = userDTO.document();
		this.email = userDTO.email();
	}

}
