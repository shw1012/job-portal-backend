package com.nisi.jobportalbackend.service;

import com.nisi.jobportalbackend.dto.CreatedUserRequestDto;
import com.nisi.jobportalbackend.dto.CreatedUserResponseDto;
import com.nisi.jobportalbackend.entity.User;
import com.nisi.jobportalbackend.exception.DuplicateResourceException;
import com.nisi.jobportalbackend.mapper.UserMapper;
import com.nisi.jobportalbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserMapper userMapper;

    public CreatedUserResponseDto registerUser(CreatedUserRequestDto userReqDto){

        User user = userMapper.toEntity(userReqDto);
        if(userRepo.findByEmail(user.getEmail()).isPresent()){
            throw new DuplicateResourceException("User with email " + user.getEmail() + " already exists. " );
        }
        user.setPassword(encoder.encode(user.getPassword()));

        User UserResp=userRepo.save(user);
        return userMapper.toResponseDTO(UserResp);
    }
}
