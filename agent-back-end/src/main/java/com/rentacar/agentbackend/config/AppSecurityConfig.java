package com.rentacar.agentbackend.config;

import com.rentacar.agentbackend.security.TokenUtils;
import com.rentacar.agentbackend.security.auth.RestAuthenticationEntryPoint;
import com.rentacar.agentbackend.security.auth.TokenAuthenticationFilter;
import com.rentacar.agentbackend.service.impl.MyUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_WHITELIST = { "/webjars/**", "/health", "/h2-console/**", "auth/**", "/error" };

	private final MyUserDetailService myUserDetailsService;
	private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	private final TokenUtils tokenUtils;

	public AppSecurityConfig(MyUserDetailService myUserDetailsService,
			RestAuthenticationEntryPoint restAuthenticationEntryPoint, TokenUtils tokenUtils) {
		this.myUserDetailsService = myUserDetailsService;
		this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
		this.tokenUtils = tokenUtils;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
	}

	// Implementing PasswordEncoder with BCrypt hashing function
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)

				.and()

				.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()

				.anyRequest().authenticated().and()

				.cors().and()

				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, myUserDetailsService),
						BasicAuthenticationFilter.class);

		http.headers().frameOptions().sameOrigin();
		http.csrf().disable();

	}

	// Generalna bezbednost aplikacije
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/**");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js", "/auth/**");
	}
}
