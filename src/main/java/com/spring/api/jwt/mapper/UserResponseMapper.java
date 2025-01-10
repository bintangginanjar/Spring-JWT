package com.spring.api.jwt.mapper;

import com.spring.api.jwt.entity.UserEntity;
import com.spring.api.jwt.model.UserResponse;

public class UserResponseMapper {

    public static UserResponse ToUserResponseMapper(UserEntity user) {
        return UserResponse.builder()
                .username(user.getUsername())                
                .build();
    }

}
