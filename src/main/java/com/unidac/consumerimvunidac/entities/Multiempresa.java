package com.unidac.consumerimvunidac.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa o objeto de comprovação presente no banco
 * No qual iremos comparar algum atributo, aqui no caso o cnpj
 */
@Entity
@Table(name = "MULTI_EMPRESAS", schema = "DBAMV")
public class Multiempresa {

	@Id
	@Column(name = "CD_MULTI_EMPRESA")
	private Integer code;
	
	@Column(name = "NR_CNPJCPF_REPRES_LEGAL")
	private String cnpj;
}
