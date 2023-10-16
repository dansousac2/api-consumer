package com.unidac.consumerimvunidac.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataToSend {

	private Integer id;
	private String description;
	private String ProcessMeType;
	private String status;
	private Integer referenceCode;
}
