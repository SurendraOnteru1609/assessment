package com.coding.assessment.controller;

import com.coding.assessment.dto.UserDTO;
import com.coding.assessment.exception.UserServiceException;
import com.coding.assessment.service.UserService;
import com.coding.assessment.utils.UserConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(value="/register")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody UserDTO userDTO,
                                              @RequestParam(value = "userType", defaultValue = "Regular") String userType) throws UserServiceException {
        userDTO.setUserType(userType);
        Integer id = userService.registerUser(userDTO);
        return new ResponseEntity<>(UserConstants.CONTROLLER_REGISTRATION_SUCCESS + id, HttpStatus.CREATED);
    }
    @GetMapping(value="/{user_id}")
    public ResponseEntity<UserDTO> getCustomer(@PathVariable int user_id) throws UserServiceException{
        return new ResponseEntity<>(userService.getUserDetails(user_id),HttpStatus.OK);
    }
}
