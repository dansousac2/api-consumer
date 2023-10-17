package com.unidac.consumerimvunidac.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.unidac.consumerimvunidac.services.TokenServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * Nas configurações so SpringSecurity este filtro está sendo chamado antes do de autenticação
 */
@Component
public class TokenFilter extends OncePerRequestFilter {

	@Autowired
	private TokenServiceImpl tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		System.out.println("TokenFilter - " + requestURI);
		if (!requestURI.equals("/apiconsumer/gettoken")) {
			if(tokenService.getFromHttpServlet(request) == null) {
				throw new IllegalArgumentException("Token nulo ou inválido");
			}
			System.out.println("Token encontrado. Proseguindo com requisição.");
		}
		
		filterChain.doFilter(request, response);
	}

}
