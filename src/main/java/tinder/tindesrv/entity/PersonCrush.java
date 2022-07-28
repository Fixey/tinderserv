package tinder.tindesrv.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
