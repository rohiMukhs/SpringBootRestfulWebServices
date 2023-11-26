package net.javaguides.springbootRestfulWebServices.ServiceImplTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import net.javaguides.springbootRestfulWebServices.dto.UserDto;
import net.javaguides.springbootRestfulWebServices.entity.User;
import net.javaguides.springbootRestfulWebServices.repository.UserRepository;
import net.javaguides.springbootRestfulWebServices.service.impl.UserServiceImpl;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

	public UserServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        // Create a sample UserDto
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john@example.com");

        // Mock the userRepository.save() method
        User user = new User();
        user.setId(1L);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Call the createUser method
        UserDto savedUser = userService.createUser(userDto);

        // Verify that userRepository.save() was called
        verify(userRepository, times(1)).save(any(User.class));

        // Assertions
        assertNotNull(savedUser);
        assertEquals(user.getId(), savedUser.getId());
        assertEquals(userDto.getFirstName(), savedUser.getFirstName());
        assertEquals(userDto.getLastName(), savedUser.getLastName());
        assertEquals(userDto.getEmail(), savedUser.getEmail());
    }

    

        // Test for getUserById
        @Test
        void testGetUserById() {
            // Mocking userRepository.findById() method
            User user = new User();
            user.setId(1L);
            user.setFirstName("Test");
            user.setLastName("User");
            user.setEmail("test@example.com");
            Optional<User> optionalUser = Optional.of(user);

            when(userRepository.findById(anyLong())).thenReturn(optionalUser);

            // Call the getUserById method
            UserDto foundUser = userService.getUserById(1L);

            // Assertions
            assertNotNull(foundUser);
            assertEquals(user.getId(), foundUser.getId());
            assertEquals(user.getFirstName(), foundUser.getFirstName());
            assertEquals(user.getLastName(), foundUser.getLastName());
            assertEquals(user.getEmail(), foundUser.getEmail());
        }

        // Test for getAllUsers
        @Test
        void testGetAllUsers() {
            // Mocking userRepository.findAll() method
            List<User> users = new ArrayList<>();
            User user1 = new User();
            user1.setId(1L);
            user1.setFirstName("User");
            user1.setLastName("One");
            user1.setEmail("user1@example.com");

            User user2 = new User();
            user2.setId(2L);
            user2.setFirstName("User");
            user2.setLastName("Two");
            user2.setEmail("user2@example.com");

            users.add(user1);
            users.add(user2);

            when(userRepository.findAll()).thenReturn(users);

            // Call the getAllUsers method
            List<UserDto> foundUsers = userService.getAllUsers();

            // Assertions
            assertNotNull(foundUsers);
            assertEquals(2, foundUsers.size());
            assertEquals(users.get(0).getId(), foundUsers.get(0).getId());
            assertEquals(users.get(1).getId(), foundUsers.get(1).getId());
            // Add more assertions as needed to match other fields
        }

        // Test for updateUser
        @Test
        void testUpdateUser() {
            // Sample UserDto with updated information
            UserDto updatedUserDto = new UserDto();
            updatedUserDto.setId(1L);
            updatedUserDto.setFirstName("Updated");
            updatedUserDto.setLastName("User");
            updatedUserDto.setEmail("updated@example.com");

            // Mocking userRepository.findById() and userRepository.save() methods
            User existingUser = new User();
            existingUser.setId(1L);
            existingUser.setFirstName("Initial");
            existingUser.setLastName("User");
            existingUser.setEmail("initial@example.com");
            Optional<User> optionalUser = Optional.of(existingUser);

            when(userRepository.findById(anyLong())).thenReturn(optionalUser);
            when(userRepository.save(any(User.class))).thenReturn(existingUser);

            // Call the updateUser method
            UserDto updatedUser = userService.updateUser(updatedUserDto);

            // Assertions
            assertNotNull(updatedUser);
            assertEquals(updatedUserDto.getId(), updatedUser.getId());
            assertEquals(updatedUserDto.getFirstName(), updatedUser.getFirstName());
            assertEquals(updatedUserDto.getLastName(), updatedUser.getLastName());
            assertEquals(updatedUserDto.getEmail(), updatedUser.getEmail());
        }
        
        // Write tests for deleteUser and other edge cases as needed
    


    @Test
    void testDeleteUser() {
        // Mocking the deleteById method
        doNothing().when(userRepository).deleteById(anyLong());

        // Calling the deleteUser method
        userService.deleteUser(1L);

        // Verify that userRepository.deleteById() was called
        verify(userRepository, times(1)).deleteById(anyLong());
    }
}

