package com.company.springbootblogrestapi.service.impl;

import com.company.springbootblogrestapi.entity.Role;
import com.company.springbootblogrestapi.entity.User;
import com.company.springbootblogrestapi.exception.ApiException;
import com.company.springbootblogrestapi.payload.LoginDto;
import com.company.springbootblogrestapi.payload.RegisterDto;
import com.company.springbootblogrestapi.repository.RoleRepository;
import com.company.springbootblogrestapi.repository.UserRepository;
import com.company.springbootblogrestapi.security.JwtTokenProvider;
import com.company.springbootblogrestapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Username is already exists");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already exists");
        }

        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_USER").get();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        return "User registered";
    }
}
