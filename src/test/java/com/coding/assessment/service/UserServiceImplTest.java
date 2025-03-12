package com.coding.assessment.service;

import com.coding.assessment.dto.UserDTO;
import com.coding.assessment.entity.User;
import com.coding.assessment.exception.UserServiceException;
import com.coding.assessment.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setName("TestUser");
        userDTO.setAge(25);
        userDTO.setCountry("France");
        userDTO.setGender("Male");
        userDTO.setUserType("Advanced");
        user = new User();
        user.setId(1);
        user.setName("TestUser");
        user.setAge(25);
        user.setCountry("France");
        user.setGender("Male");
        user.setUserType("Advanced");
    }

    @Test
    void testRegisterUser_Success() throws UserServiceException {
        when(userRepository.save(any(User.class))).thenReturn(user);
        Integer userId = userService.registerUser(userDTO);
        assertNotNull(userId);
        assertEquals(1, userId);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserDetails_Success() throws UserServiceException {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        UserDTO retrievedUserDTO = userService.getUserDetails(1);
        assertNotNull(retrievedUserDTO);
        assertEquals(user.getId(), retrievedUserDTO.getId());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testGetUserDetails_UserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        UserServiceException exception = assertThrows(UserServiceException.class, () -> {
            userService.getUserDetails(1);
        });
        assertEquals("User does not exist", exception.getMessage());
        verify(userRepository, times(1)).findById(1);
    }
}

