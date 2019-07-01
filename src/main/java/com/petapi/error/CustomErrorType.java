package com.petapi.error;

public class CustomErrorType {
	
private String errorMessage;

public CustomErrorType(String errorMessage) {

	this.errorMessage = errorMessage;
}

public String getMessage() {
	return errorMessage;
}

}
