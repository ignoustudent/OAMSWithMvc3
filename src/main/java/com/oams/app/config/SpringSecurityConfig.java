/**
 * 
 */
package com.oams.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.oams.app.service.UserCredService;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserCredService userCredService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userCredService)
		.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		////.antMatchers("/app/secure/**").hasAnyRole("ADMIN","USER")
		
		http.authorizeRequests()
		.antMatchers("/notification/**","/","/login/**","/patient/registration","/uploadimage","/patient/verifyemail","/patient/validateotp","/patient/home","/getCountries","/stateByCountryID","/cityByStateId","/getNotifications","/appointment/availability","/forgot/password","/otp/send").permitAll()
		.anyRequest().authenticated()
		/*.anyRequest().fullyAuthenticated()*/
		.and().formLogin()  //login configuration
                .loginPage("/login").permitAll()
                /*.loginProcessingUrl("/login")*/
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/secure/dashboard")	
		.and().logout()    //logout configuration
		.logoutUrl("/logout") 
		.logoutSuccessUrl("/login")
		.and().exceptionHandling() //exception handling configuration
		.accessDeniedPage("/error");
		
		/*http.authorizeRequests()
		.antMatchers("/notification/**","/","/login","/patient/registration","/uploadimage","/patient/verifyemail","/patient/validateotp","/patient/home","/getCountries","/stateByCountryID","/cityByStateId","/getNotifications").permitAll()
		.antMatchers("/users/**").hasAuthority("USER")
		.antMatchers("/admin/**").hasAuthority("ADMIN")
		.anyRequest().fullyAuthenticated()
		.and()
		.formLogin().loginPage("/login")
		.failureUrl("/login?error")
		.defaultSuccessUrl("/login/success")
		.usernameParameter("email")
		.permitAll()
		.and()
		.logout()
		.logoutUrl("/login")
		.logoutSuccessUrl("/login")
		.permitAll().disable().csrf();*/
		
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring()
		.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
}
