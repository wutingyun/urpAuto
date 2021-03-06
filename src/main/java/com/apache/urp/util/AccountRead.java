package com.apache.urp.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class AccountRead {
	static String path;
	
	static{
		path = new AccountRead().getClass().getResource("").toString();
	}
	
	public static String getExcelPath(){
		
		path = path.replace("file:/", "");
		String basePath = StringUtils.substringBeforeLast(path, "target");
		
		return basePath+ "data/account.xls";
		
	}
	
	public static List<Account> getAll() {
		List<Account> accounts = new ArrayList<Account>();
		List<Object[]> list = CommonExcelUtil.getDataFromExcel(new File(
				getExcelPath()), 1);
		for (Object[] objects : list) {
			String username = (String) objects[0];
			String password = (String) objects[1];
			Account account = new Account();
			account.setUsername(username);
			account.setPassword(password);
			accounts.add(account);
		}
		return accounts;
	}
	
	public static Account getIndex(String excelFile,int index) {
		List<Account> accounts = new ArrayList<Account>();
		List<Object[]> list = CommonExcelUtil.getDataFromExcel(new File(
				excelFile), 1);
		for (Object[] objects : list) {
			String username = (String) objects[0];
			String password = (String) objects[1];
			Account account = new Account();
			account.setUsername(username);
			account.setPassword(password);
			accounts.add(account);
		}
		return accounts.get(index);
	}
	public static void main(String[] args) {
		System.out.println(new AccountRead().getExcelPath());;
	}

}
