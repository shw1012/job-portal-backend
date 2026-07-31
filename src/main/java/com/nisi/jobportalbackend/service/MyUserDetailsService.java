package com.nisi.jobportalbackend.service;

import com.nisi.jobportalbackend.entity.User;
import com.nisi.jobportalbackend.exception.ResourceNotFoundException;
import com.nisi.jobportalbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

        @Override
    public UserDetails loadUserByUsername(String email){
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with this " + email+ " not found."));
        System.out.println(email);
        return user;
    }
}