package com.example.demo.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.EmailNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import com.example.demo.ui.ErrorResponse;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountApi {

	private final AccountService accountService;
	@ExceptionHandler
	public ErrorResponse handleException(AccountNotFoundException e) {
		ErrorResponse response = new ErrorResponse();
		response.setMessage(e.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setToOfError(System.currentTimeMillis());
		return response;
	}
	
	@ExceptionHandler
	public ErrorResponse handleException(EmailNotFoundException e) {
		ErrorResponse response = new ErrorResponse();
		response.setMessage(e.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setToOfError(System.currentTimeMillis());
		return response;
	}
	
	@PostMapping
	public Account createAccount(@RequestBody Account account)
	{
		return accountService.createAccount(account);
	}
	
	@GetMapping
	public List<Account> listAccounts()
	{
		return accountService.getAllAccounts();
	}
	
	@GetMapping("/{accountNumber}")
	public Account findAccount(@PathVariable("accountNumber") String accountNumber) throws AccountNotFoundException {
		return accountService.findAccountByAccNo(accountNumber);
	}
	
	@PutMapping("/{accountNumber}")
	public Account updateAccount(@PathVariable("accountNumber") String accountNumber, @RequestBody Account account) throws AccountNotFoundException{
		return accountService.updateAccount(accountNumber, account);
	}
	
	@DeleteMapping("/{accountNumber}")
	public void deleteAccount(@PathVariable("accountNumber") String accountNumber) throws AccountNotFoundException{
		accountService.deleteAccount(accountNumber);
	}
	
	@GetMapping("/email/{accountEmail}")
	public Account findAccountByEmail(@PathVariable("accountEmail") String accountEmail) throws EmailNotFoundException{
		return accountService.findAccountByEmail(accountEmail);
	}
}