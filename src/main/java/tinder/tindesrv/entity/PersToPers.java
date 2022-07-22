package tinder.tindesrv.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PersToPers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "crush_id")
    private int crushId;

    public PersToPers(int userId, int crushId) {
        this.userId = userId;
        this.crushId = crushId;
    }
}
