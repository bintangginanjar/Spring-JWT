package com.spring.api.jwt.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.spring.api.jwt.entity.RoleEntity;
import com.spring.api.jwt.entity.UserEntity;
import com.spring.api.jwt.mapper.UserResponseMapper;
import com.spring.api.jwt.model.RegisterUserRequest;
import com.spring.api.jwt.model.UserResponse;
import com.spring.api.jwt.repository.RoleRepository;
import com.spring.api.jwt.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private ValidationService validationService;    

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
            ValidationService validationService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.validationService = validationService;
    }

    @Transactional
    public UserResponse register(RegisterUserRequest request) {
        validationService.validate(request);

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        RoleEntity role = roleRepository.findByName("USER").orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Roles not found"));

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        //user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        return UserResponseMapper.ToUserResponseMapper(user);
    }

}
