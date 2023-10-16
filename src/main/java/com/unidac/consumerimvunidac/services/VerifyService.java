package com.unidac.consumerimvunidac.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unidac.consumerimvunidac.dtos.CnpjAndDataListDto;
import com.unidac.consumerimvunidac.entities.DataToSend;

import jakarta.validation.Valid;

@Service
public class VerifyService {
	
	@Valid
	private CnpjAndDataListDto dto;
	
	public CnpjAndDataListDto verifyCnpjAndBody(String cnpj, List<DataToSend> dataList) {
		this.dto = new CnpjAndDataListDto(cnpj, dataList);
		return dto;
	}

	
}
