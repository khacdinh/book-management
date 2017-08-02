package com.bookmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bookmanagement.model.JbossEjb;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http

				.authorizeRequests().antMatchers("/resources/**", "/registration", "/api/v1/bookRest/**").permitAll()
				//                    .antMatchers("/api/v1/bookRest/**").authenticated()
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/404").and().csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean(name = "ejbCazh")
	@ConfigurationProperties(prefix = "jboss.ejb.cazh")
	public JbossEjb jbossEjbCazh() {
		return new JbossEjb();
	}

	@Bean(name = "ejbFpxAdmin")
	@ConfigurationProperties(prefix = "jboss.ejb.fpxadmin")
	public JbossEjb jbossEjbFpxAdmin() {
		return new JbossEjb();
	}

	@Bean(name = "ejbFpxAdminController")
	@ConfigurationProperties(prefix = "jboss.ejb.fpxadmincontroller")
	public JbossEjb jbossEjbFpxAdminController() {
		return new JbossEjb();
	}

}