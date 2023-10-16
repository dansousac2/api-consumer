package com.unidac.consumerimvunidac.dtos;

import java.util.List;

import org.hibernate.validator.constraints.br.CNPJ;

import com.unidac.consumerimvunidac.entities.DataToSend;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CnpjAndDataListDto {

	@CNPJ(message = "CNPJ encontra-se com formato incorreto")
	private String cnpj;
	
	@NotEmpty(message = "A lista de dados encontra-se vazia")
	private List<DataToSend> dataList;
}
