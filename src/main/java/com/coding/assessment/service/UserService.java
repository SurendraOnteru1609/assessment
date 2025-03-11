package com.coding.assessment.service;


import com.coding.assessment.dto.UserDTO;
import com.coding.assessment.exception.UserServiceException;

public interface UserService {
    public Integer registerUser(UserDTO userDTO);
    public UserDTO getUserDetails(Integer userId) throws UserServiceException;
}
