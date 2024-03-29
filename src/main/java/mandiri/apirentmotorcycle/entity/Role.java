package mandiri.apirentmotorcycle.entity;

import jakarta.persistence.*;
import lombok.*;
import mandiri.apirentmotorcycle.constant.ERole;
import mandiri.apirentmotorcycle.constant.TableEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = TableEntity.ROLE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ERole name;

}