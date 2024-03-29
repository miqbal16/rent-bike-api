package mandiri.apirentmotorcycle.service;

import mandiri.apirentmotorcycle.dto.request.AuthRequest;
import mandiri.apirentmotorcycle.dto.request.LoginRequest;
import mandiri.apirentmotorcycle.dto.response.LoginResponse;
import mandiri.apirentmotorcycle.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(AuthRequest authRequest);
    LoginResponse login(LoginRequest loginRequest);
}
