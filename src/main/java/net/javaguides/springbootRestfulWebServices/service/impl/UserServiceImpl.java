package net.javaguides.springbootRestfulWebServices.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.springbootRestfulWebServices.dto.UserDto;
import net.javaguides.springbootRestfulWebServices.entity.User;
import net.javaguides.springbootRestfulWebServices.exception.EmailAlreadyExistsException;
import net.javaguides.springbootRestfulWebServices.exception.UserNotFoundException;
import net.javaguides.springbootRestfulWebServices.mapper.AutoUserMapper;
import net.javaguides.springbootRestfulWebServices.mapper.UserMapper;
import net.javaguides.springbootRestfulWebServices.repository.UserRepository;
import net.javaguides.springbootRestfulWebServices.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private ModelMapper modelMapper;
	
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		//Converting UserDto into User JPA Entity
	   // User user = UserMapper.mapToUser(userDto);
		
		//User user = modelMapper.map(userDto, User.class);
		
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
		
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists for a user!!");
		}
		
		User user = AutoUserMapper.MAPPER.mapToUser(userDto);
		
		User savedUser = userRepository.save(user);
		
		
		//Converting User JPA Entity into UserDto
		//UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
		
		//UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
		
		UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long userId) {
		
		User user = userRepository.findById(userId).orElseThrow(
				()->  new UserNotFoundException("User", "id", userId)
				
				);
		
		//return UserMapper.mapToUserDto(user);
		
		//return modelMapper.map(user,UserDto.class);
		
		return AutoUserMapper.MAPPER.mapToUserDto(user);
				
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users =  userRepository.findAll();
		
		//return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
		
		//return users.stream().map((user)->modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		
		return users.stream().map((user)-> AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());

	}

	@Override
	public UserDto updateUser(UserDto user) {
		
		
		User existingUser = userRepository.findById(user.getId()).orElseThrow(
				
				()-> new UserNotFoundException("User", "id", user.getId())
				
				);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepository.save(existingUser);

		//return UserMapper.mapToUserDto(updatedUser);
		
		//return modelMapper.map(updatedUser,UserDto.class);
		
		return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);

	}

	@Override
	public void deleteUser(Long userId) {
		
		User existingUser = userRepository.findById(userId).orElseThrow(
				
				()-> new UserNotFoundException("User", "id", userId)
				
				);
		
		userRepository.deleteById(userId);
		
	}

}
