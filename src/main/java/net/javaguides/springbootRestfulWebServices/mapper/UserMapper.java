package net.javaguides.springbootRestfulWebServices.mapper;

import net.javaguides.springbootRestfulWebServices.dto.UserDto;
import net.javaguides.springbootRestfulWebServices.entity.User;

public class UserMapper {
	
	//Converting User JPA Entity into UserDto
	public static UserDto mapToUserDto (User user) {
		
		UserDto userDto = new UserDto(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail()
				);
		
		return userDto;
	}
	
	//Converting UserDto into User JPA Entity
	public static User mapToUser (UserDto userDto) {
		
		User user = new User(
				userDto.getId(),
				userDto.getFirstName(),
				userDto.getLastName(),
				userDto.getEmail()
				);
		
		return user;
	}
	
	

}
