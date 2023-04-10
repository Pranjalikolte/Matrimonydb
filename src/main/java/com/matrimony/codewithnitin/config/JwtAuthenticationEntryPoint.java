package com.matrimony.codewithnitin.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException authException) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		response.sendError(401, "Unauthorized User");
//
//	}
	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Bearer realm=\"myrealm\"");
    }

}
	


