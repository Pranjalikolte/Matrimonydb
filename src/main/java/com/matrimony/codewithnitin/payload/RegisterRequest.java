package com.matrimony.codewithnitin.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	private int userId;

	private String firstName;

	private String middleName;

	private String lastName;

	private String emailId;

	private String password;

	private String mobileNo;

	private String displayName;

//  private String role = "USER";
}
