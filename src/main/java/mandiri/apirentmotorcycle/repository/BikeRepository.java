package mandiri.apirentmotorcycle.repository;

import jakarta.transaction.Transactional;
import mandiri.apirentmotorcycle.constant.BikeStatus;
import mandiri.apirentmotorcycle.constant.BikeType;
import mandiri.apirentmotorcycle.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BikeRepository extends JpaRepository<Bike, String> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO m_bike (id, brand, type, color, license_plate, production_year, bike_status, rent_bike_price) VALUES (:id, :brand, :type, :color, :licensePlate, :productionYear, :bikeStatus, :rentBikePrice)", nativeQuery = true)
    void createBike(@Param("id") String id, @Param("brand") String brand, @Param("type") BikeType type, @Param("color") String color, @Param("productionYear") int productionYear , @Param("licensePlate") String licensePlate, @Param("bikeStatus") BikeStatus bikeStatus, @Param("rentBikePrice") Long rentBikePrice);

    @Query(value = "SELECT * FROM m_bike", nativeQuery = true)
    Optional<List<Bike>> findAllBikes();

    @Query(value = "SELECT * FROM m_bike WHERE id = :id", nativeQuery = true)
    Optional<Bike> findBikeById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE m_bike " +
            "SET brand = :brand, type = :type, color = :color, " +
            "license_plate = :licensePlate, production_year = :productionYear, " +
            "bike_status = :bikeStatus, rent_bike_price = :rentBikePrice WHERE id = :id",
            nativeQuery = true
    )
    void updateBike(@Param("id") String id, @Param("brand") String brand, @Param("type") BikeType type, @Param("color") String color, @Param("productionYear") int productionYear , @Param("licensePlate") String licensePlate, @Param("bikeStatus") BikeStatus bikeStatus, @Param("rentBikePrice") Long rentBikePrice);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM m_bike WHERE id = :id", nativeQuery = true)
    void removeBike(@Param("id") String id);

}
