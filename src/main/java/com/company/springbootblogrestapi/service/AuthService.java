package com.company.springbootblogrestapi.service;

import com.company.springbootblogrestapi.payload.LoginDto;
import com.company.springbootblogrestapi.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
