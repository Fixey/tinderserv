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
    private Long id;
    private Long userId;
    private Long crushId;

    public PersToPers(Long userId, Long crushId) {
        this.userId = userId;
        this.crushId = crushId;
    }
}
