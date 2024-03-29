package mandiri.apirentmotorcycle.entity;

import jakarta.persistence.*;
import lombok.*;
import mandiri.apirentmotorcycle.constant.Guarantee;
import mandiri.apirentmotorcycle.constant.TableEntity;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = TableEntity.RENT)
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "date_rent")
    private LocalDateTime dateRent;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "daily_rental")
    private int dailyRental;

    @Column(name = "guarantee")
    private Guarantee guarantee;

    @OneToMany(mappedBy = "rent", cascade = CascadeType.PERSIST)
    private List<RentTransaction> rentTransactions;
}
