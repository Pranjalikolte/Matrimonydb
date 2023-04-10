package com.matrimony.codewithnitin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponce {

	private Integer profileId;
	private String message;
}
