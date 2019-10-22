package com.cg.onlinewalletwithspringbootrest.service;

import java.time.LocalDateTime;
import java.util.List;

import com.cg.onlinewalletwithspringbootrest.dto.TransactionHistory;
import com.cg.onlinewalletwithspringbootrest.dto.WalletAccount;
import com.cg.onlinewalletwithspringbootrest.dto.WalletUser;
import com.cg.onlinewalletwithspringbootrest.exception.MyException;

/*
*author: Venkatesh,Utkarsh
*Description : 
*created Date: 09/10/2019
*last modified : 14/10/2019            
*/


public interface OnlineWalletService {
	public WalletUser addWalletUser(WalletUser
			user) throws MyException;
	public WalletAccount approveAccount(Integer accountNo) throws MyException;
	public boolean validateLoginCredentials(String userName, String password);
	public WalletUser getUser(String userName) throws MyException;
	public Double addAmount(Integer accountId, Double amount) throws MyException;
	public Double transferAmount(Integer userId, String phoneNumber, Double amount) throws MyException;
	public List<TransactionHistory> getTransactions(Integer accountId,LocalDateTime fromDate,
			LocalDateTime toDate);
	public List<WalletAccount> getAccountsToApprove();
	public WalletUser findByPhoneNo(String phoneNo);
}
