package com.yuri.desafio.service.impl;

import com.yuri.desafio.dto.user.CreateUserDTO;
import com.yuri.desafio.dto.user.ResponseUserDTO;
import com.yuri.desafio.exceptions.BadRequestException;
import com.yuri.desafio.exceptions.UserNotFoundException;
import com.yuri.desafio.model.User;
import com.yuri.desafio.repository.UserRepository;
import com.yuri.desafio.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(CreateUserDTO userDTO) {
        Optional<User> userOptional = this.userRepository.findByUsername(userDTO.getUsername());

        if (userOptional.isPresent()){
            throw new BadRequestException("User already exists!");
        }

        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setTimeLimitDay(userDTO.getTimeLimitDay());
        user.setTasks(new ArrayList<>());

        this.userRepository.save(user);
    }

    @Override
    public ResponseUserDTO listUserData(Long id) {
        Optional<User> userAlreadyExists = this.userRepository.findById(id);

        if (!userAlreadyExists.isPresent()){
            throw new UserNotFoundException("User not found!");
        }

        User user = userAlreadyExists.get();

        return ResponseUserDTO.convertToDto(user);
    }
}
