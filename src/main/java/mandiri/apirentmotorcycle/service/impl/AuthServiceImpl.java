package mandiri.apirentmotorcycle.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mandiri.apirentmotorcycle.constant.ERole;
import mandiri.apirentmotorcycle.constant.Status;
import mandiri.apirentmotorcycle.dto.request.AuthRequest;
import mandiri.apirentmotorcycle.dto.request.LoginRequest;
import mandiri.apirentmotorcycle.dto.response.LoginResponse;
import mandiri.apirentmotorcycle.dto.response.RegisterResponse;
import mandiri.apirentmotorcycle.entity.*;
import mandiri.apirentmotorcycle.repository.UserCredentialRepository;
import mandiri.apirentmotorcycle.security.JwtUtil;
import mandiri.apirentmotorcycle.service.AuthService;
import mandiri.apirentmotorcycle.service.CustomerService;
import mandiri.apirentmotorcycle.service.RoleService;
import mandiri.apirentmotorcycle.util.map.ResponseMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserCredentialRepository userCredentialRepository;
    private final CustomerService customerService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerCustomer(AuthRequest authRequest) {
        try {
            Role role = Role.builder()
                    .name(ERole.CUSTOMER)
                    .build();

            Role aRole = roleService.getOrSave(role);

            UserCredential userCredential = UserCredential.builder()
                    .userName(authRequest.getUsername())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .role(aRole)
                    .build();

            userCredentialRepository.saveAndFlush(userCredential);

            Customer customer = Customer.builder()
                    .customerName(authRequest.getCustomerName())
                    .address(authRequest.getAddress())
                    .email(authRequest.getEmail())
                    .phone(authRequest.getPhone())
                    .identityNumber(authRequest.getIdentityNumber())
                    .userCredential(userCredential)
                    .customerStatus(Status.ACTIVE)
                    .build();

            customerService.create(customer);

            return ResponseMapper.registerResponse(userCredential);

        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already Exit");
        }
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserCredential userCredential = userCredentialRepository.findByUserName(loginRequest.getUsername()).orElseThrow();
        Customer customer = customerService.findByCredentialId(userCredential.getId());

        if (customer.getCustomerStatus() == Status.NOT_ACTIVE) {
            throw new RuntimeException("Username or password is wrong");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername().toLowerCase(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return ResponseMapper.loginResponse(token, appUser);
    }
}
