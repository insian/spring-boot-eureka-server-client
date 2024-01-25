package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "account_table")
public class Account {
	@Id
	@Column(name = "account_number")
	private String accountNumber;
	@Column(name = "account_holder_name")
	private String accountHolderName;
	@Column(name = "account_holder_address")
	private String accountHolderAddress;
	@Column(name = "account_holder_email",unique = true)
	private String accountHolderEmail;
	public Account(String accountHolderName, String accountHolderAddress, String accountHolderEmail) {
		super();
		this.accountHolderName = accountHolderName;
		this.accountHolderAddress = accountHolderAddress;
		this.accountHolderEmail = accountHolderEmail;
	}

	
}