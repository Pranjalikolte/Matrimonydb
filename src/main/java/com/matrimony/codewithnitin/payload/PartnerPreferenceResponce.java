package com.matrimony.codewithnitin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerPreferenceResponce {

	private Integer preferenceId;
	private String message;
}
