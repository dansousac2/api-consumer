package com.unidac.consumerimvunidac.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidac.consumerimvunidac.dtos.CnpjAndDataListDto;
import com.unidac.consumerimvunidac.entities.DataToSend;
import com.unidac.consumerimvunidac.services.CnpjService;
import com.unidac.consumerimvunidac.services.TokenServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/apiconsumer")
public class ConsumerController {
	
	@Autowired
	private CnpjService cnpjService;
	
	@Autowired
	private TokenServiceImpl tokenService;
	
	// pegar request atual
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("/gettoken")
	public ResponseEntity<String> getToken(@RequestBody @Valid CnpjAndDataListDto dto) {
		try {
			cnpjService.isValidCnpj(dto.getCnpj());
			
			String token = tokenService.generate(dto.getCnpj(), dto.getDataList());
			
			return ResponseEntity.ok(token);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/senddata")
	public ResponseEntity<String> sendDataToken() {
		try {
			String token = tokenService.getFromHttpServlet(request);
			// lança exeção em caso negativo
			tokenService.isValidToken(token);
			
			// realizar algo com os dados presente no token
			List<DataToSend> dataList = tokenService.getDataList(token);
			
			return ResponseEntity.ok("Quantidade de novos registros enviados: " + dataList.size());
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	

}
