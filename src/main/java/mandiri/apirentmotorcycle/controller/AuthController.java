package mandiri.apirentmotorcycle.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mandiri.apirentmotorcycle.constant.AppPath;
import mandiri.apirentmotorcycle.dto.request.AuthRequest;
import mandiri.apirentmotorcycle.dto.request.LoginRequest;
import mandiri.apirentmotorcycle.dto.response.LoginResponse;
import mandiri.apirentmotorcycle.dto.response.RegisterResponse;
import mandiri.apirentmotorcycle.service.AuthService;
import mandiri.apirentmotorcycle.util.map.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.AUTH)
public class AuthController {

    private final AuthService authService;

    @PostMapping(AppPath.REGISTER_CUSTOMER)
    public ResponseEntity<?> registerCustomer(
            @Valid @RequestBody AuthRequest authRequest
    ) {
        RegisterResponse register = authService.registerCustomer(authRequest);

        return ResponseMapper.commonResponse(register,
                "Successfully register customer",
                HttpStatus.CREATED);
    }

    @PostMapping(AppPath.LOGIN_CUSTOMER)
    public ResponseEntity<?> loginCustomer(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        LoginResponse login = authService.login(loginRequest);

        return ResponseMapper.commonResponse(login,
                "Successfully login customer",
                HttpStatus.OK);
    }
}
