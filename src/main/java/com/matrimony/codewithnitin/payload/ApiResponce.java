package com.matrimony.codewithnitin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponce {

	private String message;
	private boolean success;
	private int status;

//	    private String error;
//	 private LocalDateTime timestamp;

}