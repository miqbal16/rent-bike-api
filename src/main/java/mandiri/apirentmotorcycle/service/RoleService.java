package mandiri.apirentmotorcycle.service;

import mandiri.apirentmotorcycle.entity.Role;

public interface RoleService {
    Role getOrSave(Role role);
}
