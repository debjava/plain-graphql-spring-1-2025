package com.ddlab.rnd.entity;

import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class AppUser {
	
	private long id;
	private String firstName;
	private String lastName;
	private MaritalStatus mStatus;
	private boolean status;
	private int salary;
	private Address adrs;
	private List<String> phoneNos;
	private Set<BankAccount> bankAccounts; 

}
