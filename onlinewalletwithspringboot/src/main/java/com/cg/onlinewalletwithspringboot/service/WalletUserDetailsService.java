package com.cg.onlinewalletwithspringboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.onlinewalletwithspringboot.dto.WalletUser;
import com.cg.onlinewalletwithspringboot.dto.WalletUserDetails;
import com.cg.onlinewalletwithspringboot.exception.MyException;
import com.cg.onlinewalletwithspringboot.repository.OnlineWalletUserRepository;
@Service("walletuserdetailsservice")
public class WalletUserDetailsService implements  UserDetailsService {
	
	@Autowired
	OnlineWalletService service = new OnlineWalletServiceImpl();

	@Override
	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<WalletUser> user;
		try {
			System.out.println(loginName);
			user = Optional.of(service.getUser(loginName));
			System.out.println(user.get().getPhoneNo());
			user.orElseThrow(() -> new UsernameNotFoundException("Not Found: "+loginName));
			return user.map(WalletUserDetails::new).get();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
		
		
	}

}
