package net.javaguides.springbootRestfulWebServices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Schema(description="UserDto Model Info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	
	private Long id;
	
	@Schema(description="User first name")
	@NotEmpty(message = "user first name must not be null or empty")
	private String firstName;
	
	@Schema(description="User last name")
	@NotEmpty(message = "user last name must not be null or empty")
	private String lastName;
	
	@Schema(description="User email address")
	@NotEmpty(message = "user email must not be null or empty")
	@Email(message = "email address should be a valid one")
	private String email;
}
