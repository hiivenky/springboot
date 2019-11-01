package com.cg.onlinewalletwithangular;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlinewalletwithspringbootrest.service.OnlineWalletService;


/**
 * Author:Utkarsh
 * Created: 12/10/19
 * Last modified: 12/10/19
 * Description: Spring Boot testing in Controller
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OnlinewalletwithangularApplicationTests {

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
}
