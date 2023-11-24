package net.javaguides.springbootRestfulWebServices.service;

import java.util.List;

import net.javaguides.springbootRestfulWebServices.dto.UserDto;
import net.javaguides.springbootRestfulWebServices.entity.User;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	UserDto getUserById(Long userId);
	
	List<UserDto> getAllUsers();
	
	UserDto updateUser(UserDto user);
	
	void deleteUser(Long userId);
}
