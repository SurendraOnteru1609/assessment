package com.coding.assessment.service;

import com.coding.assessment.dto.UserDTO;
import com.coding.assessment.entity.User;
import com.coding.assessment.exception.UserServiceException;
import com.coding.assessment.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value="currencyExchangeService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Integer registerUser(UserDTO userDTO) {
        User newUser = userRepository.save(userDTO.toEntity());
        System.out.println(newUser);
        return newUser.getId();
    }

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
