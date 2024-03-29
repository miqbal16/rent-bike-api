package mandiri.apirentmotorcycle.repository;

import mandiri.apirentmotorcycle.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends JpaRepository<Rent,String> {
    @Query(value = "SELECT * FROM t_rent WHERE id = :id", nativeQuery = true)
    Optional<Rent> getRentById(@Param("id") String id);

    @Query(value = "SELECT * FROM t_rent", nativeQuery = true)
    Optional<List<Rent>> findAllRents();

}
