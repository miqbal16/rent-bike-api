package mandiri.apirentmotorcycle.entity;

import jakarta.persistence.*;
import lombok.*;
import mandiri.apirentmotorcycle.constant.BikeStatus;
import mandiri.apirentmotorcycle.constant.BikeType;
import mandiri.apirentmotorcycle.constant.TableEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = TableEntity.BIKE)
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "type", nullable = false)
    private BikeType type;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;

    @Column(name = "production_year", nullable = false)
    private int productionYear;

    @Column(name = "bike_status", nullable = false)
    private BikeStatus bikeStatus;

    @Column(name = "rent_bike_price")
    private Long rentBikePrice;
}
