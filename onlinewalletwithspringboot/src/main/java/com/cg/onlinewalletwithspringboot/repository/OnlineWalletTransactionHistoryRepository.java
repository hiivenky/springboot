package com.cg.onlinewalletwithspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.onlinewalletwithspringboot.dto.TransactionHistory;
import com.cg.onlinewalletwithspringboot.dto.WalletAccount;

public interface OnlineWalletTransactionHistoryRepository extends JpaRepository<TransactionHistory,Integer>{
	public List<TransactionHistory> findByWalletAccount(WalletAccount walletAccount);
}
