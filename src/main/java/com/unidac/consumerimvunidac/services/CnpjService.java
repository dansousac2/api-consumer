package com.unidac.consumerimvunidac.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidac.consumerimvunidac.exceptions.CnpjNotFoundException;
import com.unidac.consumerimvunidac.repository.GenericRepository;

@Service
public class CnpjService {

	@Autowired
	private GenericRepository repository;
	
	public void isValidCnpj(String cnpj) throws CnpjNotFoundException {
		if(repository.existsByCnpj(cnpj)) {
			return;
		} else {
			throw new CnpjNotFoundException("Cnpj não encontrado: " + cnpj);
		}
	}
}
