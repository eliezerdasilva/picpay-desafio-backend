package br.desafiopicpay.dtos;

import java.math.BigDecimal;

import br.desafiopicpay.enums.UserType;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance,String email, String password, UserType userType) {

}
