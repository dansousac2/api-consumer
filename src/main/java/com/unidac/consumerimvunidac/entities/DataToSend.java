package com.unidac.consumerimvunidac.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataToSend {

	private Integer id;
	private String description;
	private String ProcessMeType;
	private String status;
	private Integer referenceCode;
}
