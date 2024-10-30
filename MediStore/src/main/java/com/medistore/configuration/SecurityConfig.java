package com.medistore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.medistore.filter.JWTFilter;

/**
 * shift+alt+j for customizing security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private JWTFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(customizer -> customizer.disable());
		http.authorizeHttpRequests(request -> request
				.requestMatchers("users/register","users/login")
				.permitAll()
				.anyRequest().authenticated());
		//for enabling form login(browser)
		//http.formLogin(Customizer.withDefaults());
		//for enabling postman login
		http.httpBasic(Customizer.withDefaults());
		//for making session stateless,so all the time we need to use username and password,not suitable for formlogin, so disable it
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	/**
	 * for fetching username and password from db
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
		//simple encoder
		//provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		//implementing bcrypt password decoder
		provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
		provider.setUserDetailsService(userDetailService);
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		//user our own custom users
//		UserDetails user1 =User
//				.withDefaultPasswordEncoder()
//				.username("vivek")
//				.password("v123")
//				.roles("USER")
//				.build();
//		UserDetails user2 =User
//				.withDefaultPasswordEncoder()
//				.username("liji")
//				.password("l123")
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user1,user2);
		
//	}

}
