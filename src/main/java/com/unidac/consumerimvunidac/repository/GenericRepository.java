package com.unidac.consumerimvunidac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidac.consumerimvunidac.entities.Multiempresa;

public interface GenericRepository extends JpaRepository<Multiempresa, Integer>{

	boolean existsByCnpj(String cnpj);
	
}