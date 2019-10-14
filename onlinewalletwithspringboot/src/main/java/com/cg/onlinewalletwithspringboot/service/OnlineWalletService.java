package com.cg.onlinewalletwithspringboot.service;

import java.time.LocalDateTime;
import java.util.List;

import com.cg.onlinewalletwithspringboot.dto.TransactionHistory;
import com.cg.onlinewalletwithspringboot.dto.WalletAccount;
import com.cg.onlinewalletwithspringboot.dto.WalletUser;
import com.cg.onlinewalletwithspringboot.exception.MyException;

public interface OnlineWalletService {
	public WalletUser addWalletUser(WalletUser user) throws MyException;
	public WalletAccount approveAccount(Integer accountNo) throws MyException;
	public boolean validateLoginCredentials(String userName, String password);
	public WalletUser getUser(String userName) throws MyException;
	Double addAmount(Integer accountId, Double amount) throws MyException;
	Double transferAmount(Integer userId, String phoneNumber, Double amount) throws MyException;
	public List<TransactionHistory> getTransactions(Integer accountId,LocalDateTime fromDate,
			LocalDateTime toDate);
	public List<WalletAccount> getAccountsToApprove();
	public WalletUser findByPhoneNo(String phoneNo);
}
