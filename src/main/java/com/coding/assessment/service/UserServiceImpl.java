package com.coding.assessment.service;

import com.coding.assessment.dto.UserDTO;
import com.coding.assessment.entity.User;
import com.coding.assessment.exception.UserServiceException;
import com.coding.assessment.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the UserService interface for registering and fetching user details.
 */
@Service(value="currencyExchangeService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * This method registers a new user in the User table.
     *
     * @param userDTO Contains user details to be registered.
     * @return The ID of the new user.
     * @throws UserServiceException If user registration fails.
     */
    @Override
    public Integer registerUser(UserDTO userDTO) throws UserServiceException {
        try{
            User newUser = userRepository.save(userDTO.toEntity());
            System.out.println(newUser);
            return newUser.getId();
        }
        catch(Exception e){
            throw new UserServiceException("Unable to register user");
        }
    }

    /**
     * This method fetches user details based on the user ID.
     *
     * @param userId Contains the User ID of the user to fetch details.
     * @return UserDTO contains the fetched user details.
     * @throws UserServiceException If the user is not found.
     */
    @Override
    public UserDTO getUserDetails(Integer userId) throws UserServiceException {
        Optional<User> opt=userRepository.findById(userId);
        if(opt.isPresent()) {
            User user=opt.get();
            return user.toDTO();
        } else {
            throw new UserServiceException("User does not exist");
        }
    }
}
