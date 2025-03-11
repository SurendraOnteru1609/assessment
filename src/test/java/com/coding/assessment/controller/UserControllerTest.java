package com.coding.assessment.controller;

import com.coding.assessment.dto.UserDTO;
import com.coding.assessment.service.UserService;
import com.coding.assessment.utils.UserConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
        userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setName("TestUser");
        userDTO.setAge(25);
        userDTO.setCountry("France");
        userDTO.setGender("Male");
        userDTO.setUserType("Regular");
    }

    @Test
    void testAddCustomer_WithExplicitUserType() throws Exception {
        when(userService.registerUser(any(UserDTO.class))).thenReturn(1);
        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO))
                        .param("userType", "Advanced"))
                .andExpect(status().isCreated())
                .andExpect(content().string(UserConstants.CONTROLLER_REGISTRATION_SUCCESS + 1));
        verify(userService, times(1)).registerUser(any(UserDTO.class));
    }

    @Test
    void testAddCustomer_WithDefaultUserType() throws Exception {
        when(userService.registerUser(any(UserDTO.class))).thenReturn(1);
        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string(UserConstants.CONTROLLER_REGISTRATION_SUCCESS + 1));
        verify(userService, times(1)).registerUser(any(UserDTO.class));
    }

    @Test
    void testAddCustomer_ValidationFailure() throws Exception {
        userDTO.setName(null);
        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetCustomer_Success() throws Exception {
        when(userService.getUserDetails(1)).thenReturn(userDTO);
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
        verify(userService, times(1)).getUserDetails(1);
    }
}
