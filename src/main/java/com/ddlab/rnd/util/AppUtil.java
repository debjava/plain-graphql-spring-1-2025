package com.ddlab.rnd.util;

import java.util.List;
import java.util.Set;

import com.ddlab.rnd.entity.Address;
import com.ddlab.rnd.entity.AppUser;
import com.ddlab.rnd.entity.BankAccount;
import com.ddlab.rnd.entity.MaritalStatus;

public class AppUtil {
	
	public static AppUser getDefaultUser(Long id) {
		AppUser appUser = new AppUser();
		if( id == null) {
			appUser.setId(123L);
		} else {
			appUser.setId(id);
		}
		appUser.setFirstName("John");
		appUser.setLastName("Abraham");
		appUser.setMStatus(MaritalStatus.SINGLE);
		Address adrs = new Address();
		adrs.setCityName("Bangalore");
		adrs.setPinCode("123456");
		appUser.setAdrs(adrs);
		appUser.setStatus(true);
		appUser.setSalary(2345);
		
		appUser.setPhoneNos(List.of("PhoneNo-1", "Phone-No-2"));
		Set<BankAccount> banks = Set.of(
				new BankAccount("HDFC", "ActNo-1"), 
				new BankAccount("SBI", "ActNo-2") );

		appUser.setBankAccounts(banks);
				
		return appUser;
	}

}
