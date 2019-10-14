package com.cg.onlinewalletwithspringboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlinewalletwithspringboot.dto.Status;
import com.cg.onlinewalletwithspringboot.dto.WalletAccount;
import com.cg.onlinewalletwithspringboot.dto.WalletUser;
import com.cg.onlinewalletwithspringboot.exception.MyException;
import com.cg.onlinewalletwithspringboot.repository.OnlineWalletUserRepository;
import com.cg.onlinewalletwithspringboot.service.OnlineWalletService;


/**
 * Author:Utkarsh
 * Created: 12/10/19
 * Last modified: 12/10/19
 * Description: Spring Boot testing
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OnlinewalletwithspringbootApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	OnlineWalletService walletService;
	
	
	
	@Test
	public void checkViewHomePage() {
		String str= restTemplate.getForObject("/", String.class);
		assertThat(str.equals("home"));
	}
	
	@Test
	public void checkLoginPage() {
		String str= restTemplate.getForObject("/login", String.class);
		assertThat(str.equals("login"));
	}
	
	@Test
	public void checkRegistrationPage() {
		String str= restTemplate.getForObject("/getRegistrationDetails", String.class);
		assertThat(str.equals("registration"));
	}

	

	@Test
	public void checkUserFunctionalitiesPage() {
		String str= restTemplate.getForObject("/userPage", String.class);
		assertThat(str.equals("UserFunctionalitiesPage"));
	}
	
	@Test
	public void checkAdminFunctionalitiesPage() {
		String str= restTemplate.getForObject("/getApproveAccountNo", String.class);
		assertThat(str.equals("AdminFunctionalitiesPage"));
	}
	
	@Test
	public void checkAddAmountPage() {
		String str= restTemplate.getForObject("/addAmount", String.class);
		assertThat(str.equals("addAmount"));
	}
	
	@Test
	public void checkApproveAccountPage() {
		String str= restTemplate.getForObject("/approveAccount", String.class);
		assertThat(str.equals("approveAccount"));
	}
	
	/**Service Layer
	 * Author: Utkarsh
	 * Created: 12/10/19
	 * Last modified: 12/10/19
	 * Description: Spring Boot testing
	 * **/
	@Test
	public void checkAddWalletUserName() throws MyException {
	
//		WalletUser user = new WalletUser();
//		user.setUserName("Surya");
//		user.setUserPassword("12345678");
//		user.setPhoneNo("9003432137");
		
		
		assertEquals("Surya", walletService.findByPhoneNo("9003432137").getUserName());
		//assertNotNull(walletService.addWalletUser(user));
	}
	

	@Test
	public void checkAddWalletUserPassword() throws MyException {
		
		
		
		assertEquals("12345678", walletService.findByPhoneNo("9003432137").getUserPassword());
	}
	
	
	@Test
	public void checkAddWalletUserPhoneNo() throws MyException {
		
		
		assertEquals("Surya", walletService.findByPhoneNo("9003432137").getUserName());
	}
	
	@Test
	public void checkApproveAccount() throws MyException {
		
		WalletUser user = walletService.findByPhoneNo("8319780328");
		
		assertEquals(user.getAccount().getAccountStatus(),Status.Approved);
		
	}
	@Test
	public void checkAddAmount()  throws MyException {
		//WalletAccount account=new WalletAccount();
		Double amount = walletService.findByPhoneNo("8639052501").getAccount().getBalance();
		
		walletService.addAmount(1, 100.00);
		System.out.println(amount);
		Double amount2 = walletService.findByPhoneNo("8639052501").getAccount().getBalance()- 100.00;
		System.out.println(amount2);
		assertEquals(amount,amount2);
		
	}
	
	@Test
	public void checkTransferAmount()  throws MyException {
		//WalletAccount account=new WalletAccount();
		//WalletAccount account=new WalletAccount();
		Double amount = walletService.findByPhoneNo("8319780328").getAccount().getBalance();
		walletService.transferAmount(1,"8319780328", 100.00);
		Double amount2 = walletService.findByPhoneNo("8319780328").getAccount().getBalance()-100.00;
		assertEquals(amount,amount2);
	}
	

	
	
}
