package com.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Cross Origin Resource Sharing
		http.cors().and()
			.authorizeRequests()
			//ignoring guest URLs
			.antMatchers("/resources/*","/error","/service/**").permitAll()
			//authenticate all remaining URLs
			.anyRequest().fullyAuthenticated()
			.and()
			.logout().permitAll()
			.logoutRequestMatcher(new AntPathRequestMatcher("service/logout"))//Post method not taken
			.and()
			//login Form URLs
			.formLogin().loginPage("/service/login").and()
			//enable basic header authentication
			.httpBasic().and()
			//cross-site request forgery
			.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//we can reach Authentication Manager of the app by overriding this configure method
		//Here only we provide where userdatils lies like LDAP server or third party DB or you own userdetail DB or even In Memory
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	//CORS configuration we will allow all origins
	//A Good example of Singleton - as in spring default instance creation is singleton.So it will be created one time and we reach it throughout application
	//since @Bean is creating new instances and default instances are singleton.
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
		
	}
	
	
	
	

}
