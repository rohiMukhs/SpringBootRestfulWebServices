package net.javaguides.springbootRestfulWebServices.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.javaguides.springbootRestfulWebServices.dto.UserDto;
import net.javaguides.springbootRestfulWebServices.entity.User;

@Mapper
public interface AutoUserMapper {
	
	
	AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
	
	
	UserDto mapToUserDto(User user);
	
	User mapToUser(UserDto userDto);

}
