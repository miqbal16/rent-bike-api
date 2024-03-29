package mandiri.apirentmotorcycle.entity;

import jakarta.persistence.*;
import lombok.*;
import mandiri.apirentmotorcycle.constant.TableEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = TableEntity.RENT_TRANSACTION)
public class RentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "rent_id")
    private Rent rent;

    @ManyToOne
    @JoinColumn(name = "bike_id")
    private Bike bike;

    @Column(name = "end_date_rent")
    private LocalDateTime endDateRent;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "has_return_guarantee")
    private boolean hasReturnGuarantee;

    @Column(name = "late_return_fines")
    private Long lateReturnFines;

}
