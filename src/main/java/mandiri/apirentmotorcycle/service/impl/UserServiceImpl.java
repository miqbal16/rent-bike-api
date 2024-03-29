package mandiri.apirentmotorcycle.service.impl;

import lombok.RequiredArgsConstructor;
import mandiri.apirentmotorcycle.entity.AppUser;
import mandiri.apirentmotorcycle.entity.UserCredential;
import mandiri.apirentmotorcycle.repository.UserCredentialRepository;
import mandiri.apirentmotorcycle.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserCredentialRepository userCredentialRepository;

    @Override
    public AppUser loadUserByUserId(String id) {
        UserCredential userCredential = userCredentialRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Credential"));

        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUserName())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Credential"));
        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUserName())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }
}
