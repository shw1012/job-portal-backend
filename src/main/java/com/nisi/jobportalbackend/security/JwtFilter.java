package com.nisi.jobportalbackend.security;

import com.nisi.jobportalbackend.service.JwtService;
import com.nisi.jobportalbackend.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
// Term: Authorization :it is the HTTP header that carries the token
// Term:JWT header :it is the first part of the JWT token itself

// But where do we get authorities from? We need to load the user from the database to get their roles.so that we inject userdetailsservice
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException ,NullPointerException{

        System.out.println("JwtFilter running for: " + request.getRequestURI());
        String authHeader = request.getHeader("Authorization");
        System.out.println("Auth header: " + authHeader);
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;//it said here my work is done as there is no valid token i need to go to next filter
        }

        String token  = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        if(username==null) {
            filterChain.doFilter(request,response);
            return;
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
//  token — to check signature and expiry ,username — to check the token actually belongs to this user
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

//Inside this block ,the most important part of the whole filter — setting the authentication in Spring's SecurityContext.
//To do that, Spring Security needs a UsernamePasswordAuthenticationToken object. It takes 3 things:
           UsernamePasswordAuthenticationToken authToken =
                   new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

           SecurityContextHolder.getContext().setAuthentication(authToken);
//  "For this current request,we said to springsecurity trust that this user is authenticated. Their username is X and their roles are Y."
//   Spring Security then allows the request to pass through to the controller — because it now knows who the user is.
        }
//        One important thing to understand:
//        SecurityContextHolder only holds this information for the duration of the current request. Once the request is done, it is cleared automatically.
//                That is why JwtFilter runs on every single request — because every request starts fresh with no authentication, and JwtFilter re-establishes it each time by reading the token.
//                This is the core meaning of stateless — no session, no memory between requests. The token carries the identity, and the filter rebuilds the authentication on every reques


        filterChain.doFilter(request, response);
    }


}

//What we built here:
//1. Read Authorization header
//2. If missing or wrong format → pass forward, stop
//3. Extract token from "Bearer <token>"
//        4. Extract username from token via JwtService
//5. If username null → stop, invalid token
//6. If not already authenticated:
//        → load user from DB
//      → validate token
//      → set user as authenticated in SecurityContext
//7. Pass request forward to next filter


