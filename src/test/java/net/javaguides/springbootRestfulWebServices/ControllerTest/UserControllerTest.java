package net.javaguides.springbootRestfulWebServices.ControllerTest;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import net.javaguides.springbootRestfulWebServices.dto.UserDto;


import net.javaguides.springbootRestfulWebServices.controller.UserController;
import net.javaguides.springbootRestfulWebServices.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        UserDto userDto = new UserDto();
        // Set userDto properties

        when(userService.createUser(any(UserDto.class))).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.createUser(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto, response.getBody());
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        UserDto userDto = new UserDto();
        // Set userDto properties

        when(userService.getUserById(userId)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto, response.getBody());
    }

    @Test
    void testGetAllUsers() {
        List<UserDto> users = new ArrayList<>();
        // Add some sample users to the list

        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(users, response.getBody());
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;
        UserDto userDto = new UserDto();
        // Set userDto properties

        when(userService.updateUser(any(UserDto.class))).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.updateUser(userId, userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto, response.getBody());
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        ResponseEntity<String> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User successfully deleted!", response.getBody());

        verify(userService, times(1)).deleteUser(userId);
    }
}
