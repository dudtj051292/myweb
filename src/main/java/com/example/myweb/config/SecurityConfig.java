package com.example.myweb.config;

import javax.sql.DataSource;

import com.example.handler.CustomLoginFailHandler;
import com.example.handler.CustomLoginSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler(){
		return new CustomLoginSuccessHandler();
	}

	@Bean
	public AuthenticationFailureHandler loginFailHandler(){
		return new CustomLoginFailHandler();
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/**","/css/**", "/js/**", "/account/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/account/login")
				.successHandler(loginSuccessHandler())
				.failureHandler(loginFailHandler())
				.permitAll()
				.and()
			.logout()
				.permitAll()
				.logoutSuccessUrl("/");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 
	throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder())
		.usersByUsernameQuery("SELECT ST_ID AS username, ST_PASSWORD as password ,ENABLED "	//인증처리
			+ "FROM BLD_USER "
			+ "WHERE ST_ID = ?")
		.authoritiesByUsernameQuery("SELECT A.ST_ID AS username, C.ST_ROLE "   //권한처리
			+ "FROM BLD_USER A, BLD_USERROLE B, BLD_ROLE C  "
			+ "WHERE A.ST_ID = B.ST_ID  "
			+ "  AND c.ST_ROLE = B.ST_ROLE "
			+ "  AND A.ST_ID = ? ");
			/*
			Authentication 로그인
			Authroization 권한
			*/
	}
}
