package com.cg.onlinewalletwithspringboot.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class WalletUser {
	@Id
	@GeneratedValue()
	private  Integer userId; 
	private String userName;
	private String userPassword;
	private String phoneNo;
	private String loginName;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_no",referencedColumnName = "accountNo")
	WalletAccount account;
	
	public WalletUser() {
		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public WalletAccount getAccount() {
		return account;
	}

	public void setAccount(WalletAccount account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "WalletUser [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", phoneNo=" + phoneNo + ", loginName=" + loginName + ", account=" + account + "]";
	}

}
