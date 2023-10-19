package org.java.app.mvc.auth.config;

import org.java.app.mvc.auth.db.serv.UserServ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
public class AuthConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)
		throws Exception {

			http.csrf().disable()
				.authorizeHttpRequests()			
				.requestMatchers("/api/photos/**").permitAll()
				.requestMatchers("/api/emails/**").permitAll()
				.requestMatchers("/**").hasAuthority("ADMIN")
		        .and().formLogin().defaultSuccessUrl("/")
		        .and().logout();

			return http.build();
	}

	@Bean
	UserServ userDetailsService() {
		return new UserServ();
	}

    @Bean
    PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());

      return authProvider;
    }

}
