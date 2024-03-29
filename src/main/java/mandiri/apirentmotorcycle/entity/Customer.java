package mandiri.apirentmotorcycle.entity;

import jakarta.persistence.*;
import lombok.*;
import mandiri.apirentmotorcycle.constant.Status;
import mandiri.apirentmotorcycle.constant.TableEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = TableEntity.CUSTOMER)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "identity_number", unique = true, nullable = false)
    private String identityNumber;

    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "is_active")
    private Status customerStatus;
}
