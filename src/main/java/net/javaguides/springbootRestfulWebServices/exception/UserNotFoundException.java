package net.javaguides.springbootRestfulWebServices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	
	private String userName;
	private String fieldNAme;
	private Long fieldValue;
	
	public UserNotFoundException(String userName, String fieldNAme, Long fieldValue) {
		super(String.format(" %s not found with %s : %s", userName,fieldNAme, fieldValue ));
		this.userName = userName;
		this.fieldNAme = fieldNAme;
		this.fieldValue = fieldValue;
	}
	
	

}
