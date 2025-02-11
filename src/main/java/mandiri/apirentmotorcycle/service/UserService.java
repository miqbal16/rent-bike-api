package mandiri.apirentmotorcycle.service;

import mandiri.apirentmotorcycle.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);

}
