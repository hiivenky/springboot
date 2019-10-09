package com.cg.onlinewalletwithspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.onlinewalletwithspringboot.dto.TransactionHistory;

public interface OnlineWalletTransactionHistoryRepository extends JpaRepository<TransactionHistory,Integer>{

}
