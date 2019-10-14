package com.cg.onlinewalletwithspringboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
/**
 *author: Venkatesh  
 *created Date: 08/10/2019
 *last modified : 11/10/2019     
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class OnlineWalletConfiguration {
	/**
	 *author: Venkatesh
	 *Description : this method is used to return the annotated fields value with
	 *              custom values    
	 *created Date: 08/10/2019
	 *last modified : 11/10/2019     
	 *output : AuditorAwareImpl Object      
	 */
	    @Bean
	    public AuditorAware<String> auditorProvider() {
	        return new AuditorAwareImpl();
	    }
}
