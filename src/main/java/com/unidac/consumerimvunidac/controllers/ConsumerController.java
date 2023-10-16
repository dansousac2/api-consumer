package com.unidac.consumerimvunidac.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unidac.consumerimvunidac.dtos.CnpjAndDataListDto;
import com.unidac.consumerimvunidac.entities.DataToSend;
import com.unidac.consumerimvunidac.services.VerifyService;

@RestController
@RequestMapping("/apiconsumer")
public class ConsumerController {
	
	@Autowired
	private VerifyService verifyer;
	
	@GetMapping("/gettoken")
	public ResponseEntity<String> getToken(@RequestParam String cnpj, @RequestBody List<DataToSend> dataList) {
		try {
			CnpjAndDataListDto verifyCnpjAndBody = verifyer.verifyCnpjAndBody(cnpj, dataList);
			String token = "Token validado";
			return ResponseEntity.ok(token);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
