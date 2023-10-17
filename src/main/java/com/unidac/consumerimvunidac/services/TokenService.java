package com.unidac.consumerimvunidac.services;

import java.util.List;

import com.unidac.consumerimvunidac.entities.DataToSend;
import com.unidac.consumerimvunidac.exceptions.InvalidTokenException;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

public interface TokenService {

	String generate(String cnpj, List<DataToSend> dataList);
	Claims getClaims(String token);
	boolean isValidToken(String token) throws InvalidTokenException;
	String getCnpj(String token);
	List<DataToSend> getDataList(String token);
	String getFromHttpServlet(HttpServletRequest request);
}
