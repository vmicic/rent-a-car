package com.rentacar.administratorservice.filter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Security;

@Component
public class FeignClientInterceptor  implements RequestInterceptor {

    private static Logger logger = LoggerFactory.getLogger(FeignClientInterceptor.class);

    private static final String AUTHORIZATION_HEADER="Authorization";
    private static final String TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        TokenBasedAuthentification authentication = (TokenBasedAuthentification) SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null) {
            requestTemplate.header(AUTHORIZATION_HEADER, TOKEN_TYPE + " " + authentication.getToken());
        }
    }
}
