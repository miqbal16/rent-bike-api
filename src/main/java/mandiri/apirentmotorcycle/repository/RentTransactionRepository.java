package mandiri.apirentmotorcycle.repository;

import mandiri.apirentmotorcycle.entity.RentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentTransactionRepository extends JpaRepository<RentTransaction, String> {
    @Query(value = "SELECT * FROM t_rent_transaction WHERE rent_id = :rentId", nativeQuery = true)
    Optional<RentTransaction> findRentTransactionByRentId(@Param("rentId") String rentId);
}
