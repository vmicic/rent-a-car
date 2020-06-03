package com.rentacar.advertisementservice.config;

import com.rentacar.advertisementservice.client.UserServiceClient;
import com.rentacar.advertisementservice.filter.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceClient userServiceClient;

    private static final String[] AUTH_WHITELIST = {
            "/webjars/**",
            "/health",
            "/h2-console/**",
            "/error"
    };


    public AppSecurityConfig(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Bean
    public TokenAuthenticationFilter authenticationFilter() throws Exception {
        TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter(userServiceClient);

        tokenAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return tokenAuthenticationFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()

                .anyRequest().authenticated().and()

                .cors().and()

                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);


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
