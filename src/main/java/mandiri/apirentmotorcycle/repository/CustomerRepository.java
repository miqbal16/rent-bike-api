package mandiri.apirentmotorcycle.repository;

import jakarta.transaction.Transactional;
import mandiri.apirentmotorcycle.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("SELECT c FROM Customer c INNER JOIN UserCredential uc ON c.userCredential.id = uc.id " +
            "WHERE c.userCredential.id = :credentialId")
    Optional<Customer> findByCredentialId(@Param("credentialId") String credentialId);

    @Query(value = "SELECT * FROM m_customer", nativeQuery = true)
    Optional<List<Customer>> findAllCustomers();

    @Query(value = "SELECT * FROM m_customer WHERE id = :id", nativeQuery = true)
    Optional<Customer> findCustomerById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE m_customer " +
            "SET customer_name = :customerName, address = :address, " +
            "phone = :phone, email = :email, identity_number = :identityNumber " +
            "WHERE id = :id", nativeQuery = true
    )
    void updateCustomer(
            @Param("id") String id,
            @Param("customerName") String customerName,
            @Param("address") String address,
            @Param("phone") String phone,
            @Param("email") String email,
            @Param("identityNumber") String identityNumber);
}
