package mandiri.apirentmotorcycle.entity;

import jakarta.persistence.*;
import lombok.*;
import mandiri.apirentmotorcycle.constant.ERole;
import mandiri.apirentmotorcycle.constant.TableEntity;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = TableEntity.USER_CREDENTIAL)
public class UserCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
