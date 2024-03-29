package mandiri.apirentmotorcycle.service.impl;

import lombok.RequiredArgsConstructor;
import mandiri.apirentmotorcycle.entity.Role;
import mandiri.apirentmotorcycle.repository.RoleRepository;
import mandiri.apirentmotorcycle.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(Role role) {
        Optional<Role> optRole = roleRepository.findByName(role.getName());
        return optRole.orElseGet(() -> roleRepository.saveAndFlush(role));
    }
}
