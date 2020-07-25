package com.work.test.config;

import com.work.test.service.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyAccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.csrf().disable()
				.authorizeRequests()
					.antMatchers("/mvc/author/**").hasAnyRole("ADMIN")
					.antMatchers("/mvc/book/**").hasAnyRole("ADMIN")
					.antMatchers("/mvc/customer/**").hasAnyRole("ADMIN")
					.antMatchers("/mvc/order/**").hasAnyRole("ADMIN")
					.antMatchers("/mvc/report/**").hasAnyRole("USER", "ADMIN")
					.anyRequest().authenticated()
				.and()
					.formLogin().loginPage("/mvc/login").permitAll()
				.and()
					.logout().permitAll()
				.and()
					.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
					.withUser("user")
					.password(passwordEncoder().encode("user"))
					.roles("USER")
				.and()
					.withUser("admin")
					.password(passwordEncoder().encode("admin"))
					.roles("ADMIN");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
