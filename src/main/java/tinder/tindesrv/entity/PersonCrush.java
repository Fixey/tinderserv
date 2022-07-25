package tinder.tindesrv.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "persons_to_persons")
public class PersonCrush {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long crushId;

    public PersonCrush(Long userId, Long crushId) {
        this.userId = userId;
        this.crushId = crushId;
    }
}
