package com.cg.onlinewalletwithspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.onlinewalletwithspringboot.dto.WalletAccount;
import com.cg.onlinewalletwithspringboot.dto.WalletUser;

public interface OnlineWalletUserRepository extends JpaRepository<WalletUser,Integer> {
	public WalletUser findByUserId(Integer userID);
	public WalletUser findByAccount(WalletAccount account);
	public WalletUser findByLoginName(String loginName);
	public WalletUser findByPhoneNo(String phoneNo);
}
