package com.unidac.consumerimvunidac.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidac.consumerimvunidac.dtos.CnpjAndDataListDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apiconsumer")
public class ConsumerController {
	
	@GetMapping("/gettoken")
	public ResponseEntity<String> getToken(@RequestBody @Valid CnpjAndDataListDto dto) {
		try {
			String token = "Token validado";
			return ResponseEntity.ok(token);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
