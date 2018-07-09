package com.apache.urpcase;

import java.util.List;

import org.junit.Test;

import com.apache.urp.util.Account;
import com.apache.urp.util.AccountRead;

public class DataTest {
	
	List<Account> accounts;

	@Test
	public void tt() {
		accounts = AccountRead.getAll();
		System.out.println(accounts.size());
		for (Account account : accounts) {
			System.out.println(account.getUsername()+" = "+account.getPassword());
		}
		
	
	}

}
