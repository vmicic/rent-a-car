package com.rentacar.searchservice.filter;

import com.rentacar.searchservice.client.UserServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    private final UserServiceClient userServiceClient;

    public TokenAuthenticationFilter(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if (authorizationHeader != null) {

            String token = authorizationHeader.substring(7);

            String role = userServiceClient.getRole(token);
            String username = userServiceClient.getUsername(token);

            logger.info("Username: " + username + ", role: " + role);

            Set<SimpleGrantedAuthority> authorities = new HashSet<>();

            authorities.add(new SimpleGrantedAuthority(role));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);

    }

}
