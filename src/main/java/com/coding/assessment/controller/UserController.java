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

/**
 * Controller for registering a new User and fetching already existing user details
 */
@CrossOrigin
@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * This Rest API Registers a new user.
     *
     * @param userDTO  Contains user details to be registered.
     * @param userType Specifies the type of user (default is "Regular", if not provided).
     * @return ResponseEntity with success message and user ID of the new user.
     * @throws UserServiceException If user registration fails.
     */
    @PostMapping(value="/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO,
                                              @RequestParam(value = "userType", defaultValue = "Regular") String userType) throws UserServiceException {
        userDTO.setUserType(userType);
        Integer id = userService.registerUser(userDTO);
        return new ResponseEntity<>(UserConstants.CONTROLLER_REGISTRATION_SUCCESS + id, HttpStatus.CREATED);
    }

    /**
     * This Rest API fetches user details based on the user ID.
     *
     * @param user_id Contains the User ID of the user to fetch details.
     * @return ResponseEntity contains the fetched user details.
     * @throws UserServiceException If the user is not found.
     */
    @GetMapping(value="/{user_id}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable int user_id) throws UserServiceException{
        return new ResponseEntity<>(userService.getUserDetails(user_id),HttpStatus.OK);
    }
}
