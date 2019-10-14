package com.cg.onlinewalletwithspringboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.cg.onlinewalletwithspringboot.dto.WalletUrlAuthenticationSuccessHandler;
import com.cg.onlinewalletwithspringboot.service.WalletUserDetailsService;

/**
 *author: Venkatesh,Utkarsh
 *Description : This class is used for overriding the defaults for spring security 
 *created Date: 11/10/2019
 *last modified : 12/10/2019            
 */
@Configuration
@EnableWebSecurity
public class OnlineWalletSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	WalletUserDetailsService userDetailsService;
	@Autowired
	private WalletUrlAuthenticationSuccessHandler successHandler;

	
	/**
	 *author: Venkatesh
	 *Description : This method is used to initialize the Authentication manager builder 
	 *created Date: 11/10/2019
	 *last modified : 13/10/2019     
	 *Input : void
	 *Output : AuthenticationManager      
	 */
	@Bean("authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	/**
	 *author: Utkarsh
	 *Description : This method is used to initialize the Authentication manager builder 
	 *created Date: 11/10/2019
	 *last modified : 13/10/2019     
	 *Input : void
	 *Output : AuthenticationManager      
	 */
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new WalletUrlAuthenticationSuccessHandler();
	}
	/**
	 *author: Venkatesh
	 *Description : This method is used to initialize the Authentication manager builder 
	 *created Date: 11/10/2019
	 *last modified : 13/10/2019     
	 *Input : void
	 *Output : AuthenticationManager Object     
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("Inside configure auth");
		auth.userDetailsService(userDetailsService);
	}
	/**
	 *author: Utkarsh
	 *Description : This method is used override the default configuration for spring security 
	 *created Date: 11/10/2019
	 *last modified : 13/10/2019     
	 *Input : HttpSecurity Object
	 *Output : void      
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("welcome configure");
		http.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        http.authorizeRequests().antMatchers("/userPage").hasAnyRole("CUSTOMER","ADMIN")
		.antMatchers("/approveAccount").hasRole("ADMIN").antMatchers("/getApproveAccountNo").hasRole("ADMIN")
		.antMatchers("/addAmount").hasRole("CUSTOMER")
		.antMatchers("/redirectAfterTransaction").hasRole("CUSTOMER")
		.antMatchers("/getAmount").hasRole("CUSTOMER")
		.antMatchers("/transferAmount").hasRole("CUSTOMER")
		.antMatchers("/viewAccountsToBeApproved").hasRole("ADMIN")
		.antMatchers("/getTransactionsPage").hasRole("CUSTOMER")
		.antMatchers("/").permitAll()
		.and().formLogin()
		.loginPage("/login").permitAll().failureUrl("/login")
		.successHandler(successHandler)
		.and().logout().logoutSuccessUrl("/")
		.and().csrf().disable();
	}
	/**
	 *author: Utkarsh
	 *Description : This method is used to encrypt the password  
	 *created Date: 11/10/2019
	 *last modified : 13/10/2019     
	 *Input : void
	 *Output : PasswordEncoder Object      
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
