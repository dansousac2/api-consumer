package com.unidac.consumerimvunidac.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.unidac.consumerimvunidac.entities.DataToSend;
import com.unidac.consumerimvunidac.exceptions.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenServiceImpl implements TokenService {

	private static final String CLAIM_CNPJ = "cnpj";
	private static final String CLAIM_DATA_LIST = "dateList";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private String expiration;

	@Override
	public String generate(String cnpj, List<DataToSend> dataList) {
		long expiration = Long.valueOf(this.expiration);

		LocalDateTime expDateTime = LocalDateTime.now().plusMinutes(expiration);
		Instant expiInstant = expDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date expiDate = Date.from(expiInstant);

		String token = Jwts.builder()
						.setExpiration(expiDate)
						.setSubject(cnpj)
						.claim(CLAIM_CNPJ, cnpj)
						.claim(CLAIM_DATA_LIST, dataList)
						.signWith(SignatureAlgorithm.HS512, secret)
						.compact();

		return token;
	}

	@Override
	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
	}

	@Override
	public boolean isValidToken(String token) throws InvalidTokenException {
		if(token == null) {
			return false;
		}

		try {
			Claims claims = getClaims(token);
			
			LocalDateTime expirationLDT = claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			
			return expirationLDT.isAfter(LocalDateTime.now());
		} catch(Exception e) {
			throw new InvalidTokenException("O Token fornecido não é válido - " + e.getMessage());
		}
	}

	@Override
	public String getCnpj(String token) {
		return (String) getClaims(token).get(CLAIM_CNPJ);
	}

	@Override
	public List<DataToSend> getDataList(String token) {
		return (List<DataToSend>) getClaims(token).get(CLAIM_DATA_LIST);
	}

	@Override
	public String getFromHttpServlet(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if(authorization == null || !authorization.startsWith("Bearer")) {
			return null;
		}
		
		return authorization.split(" ")[1];
	}

}
