package net.javaguides.springbootRestfulWebServices.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.springbootRestfulWebServices.dto.UserDto;
import net.javaguides.springbootRestfulWebServices.entity.User;
import net.javaguides.springbootRestfulWebServices.mapper.UserMapper;
import net.javaguides.springbootRestfulWebServices.repository.UserRepository;
import net.javaguides.springbootRestfulWebServices.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		//Converting UserDto into User JPA Entity
		
		User user = UserMapper.mapToUser(userDto);
		
		User savedUser = userRepository.save(user);
		
		
		//Converting User JPA Entity into UserDto
		
		UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
		
		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long userId) {
		
		Optional<User> optUser = userRepository.findById(userId);
		
		User user = optUser.get();
		
		return UserMapper.mapToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users =  userRepository.findAll();
		
		return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		
		
		User existingUser = userRepository.findById(user.getId()).get();
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepository.save(existingUser);

		return UserMapper.mapToUserDto(updatedUser);
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
		
	}

}
