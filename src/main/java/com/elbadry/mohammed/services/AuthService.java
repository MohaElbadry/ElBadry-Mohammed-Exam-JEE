package com.elbadry.mohammed.services;

import com.elbadry.mohammed.dtos.AuthRequestDTO;
import com.elbadry.mohammed.dtos.AuthResponseDTO;
import com.elbadry.mohammed.dtos.RegisterRequestDTO;

public interface AuthService {
    AuthResponseDTO login(AuthRequestDTO authRequest);
    AuthResponseDTO register(RegisterRequestDTO registerRequest);
}
