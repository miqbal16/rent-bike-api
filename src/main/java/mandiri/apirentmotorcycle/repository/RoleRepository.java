package mandiri.apirentmotorcycle.repository;

import mandiri.apirentmotorcycle.constant.ERole;
import mandiri.apirentmotorcycle.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
