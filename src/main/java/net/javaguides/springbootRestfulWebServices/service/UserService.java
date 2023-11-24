package net.javaguides.springbootRestfulWebServices.service;

import java.util.List;

import net.javaguides.springbootRestfulWebServices.entity.User;

public interface UserService {
	
	User createUser(User user);
	
	User getUserById(Long userId);
	
	List<User> getAllUsers();
	
	User updateUser(User user);
	
	void deleteUser(Long userId);
}
