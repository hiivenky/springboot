package com.cg.onlinewalletwithspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.onlinewalletwithspringboot.dto.WalletAccount;

public interface OnlineWalletAccountRepository extends JpaRepository<WalletAccount,Integer>{
	public WalletAccount findByAccountNo(Integer accountNo);
	
}
