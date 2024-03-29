package mandiri.apirentmotorcycle.repository;

import mandiri.apirentmotorcycle.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {
    Optional<UserCredential> findByUserName(String username);
    @Query("SELECT uc FROM UserCredential uc INNER JOIN Customer c ON " +
            "uc.id = c.userCredential.id WHERE c.id = :customerId")
    Optional<UserCredential> findByCustomerId(@Param("customerId") String customerId);

}
