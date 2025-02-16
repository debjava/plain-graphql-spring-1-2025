package com.ddlab.rnd.dto;

import java.util.List;

import com.ddlab.rnd.entity.MaritalStatus;

import lombok.Data;

@Data
public class AppUserInput {

	private String firstName;
	private String lastName;
	private MaritalStatus mStatus;
	private boolean status;
	private int salary;
	private String cityName;
	private String pinCode;
	private List<String> phoneNos;
	private String bankName;
	private String accountNo;
}
