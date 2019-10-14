package com.cg.onlinewalletwithspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.onlinewalletwithspringboot.dto.WalletAccount;
import com.cg.onlinewalletwithspringboot.dto.WalletUser;

public interface OnlineWalletUserRepository extends JpaRepository<WalletUser,Integer> {
	//This method is used get WalletUser for a particular userId 
	public WalletUser findByUserId(Integer userID);
	//This method is used get WalletUser for a particular account object
	public WalletUser findByAccount(WalletAccount account);
	//This method is used get WalletUser for a particular loginName
	public WalletUser findByLoginName(String loginName);
	//This method is used get WalletUser for a particular phoneNo
	public WalletUser findByPhoneNo(String phoneNo);
}
