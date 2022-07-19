package tinder.tindesrv.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "persons_to_persons")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PersToPers {
    @Id
    @SequenceGenerator(name = "personsToPersonsIdSeq", sequenceName = "persons_to_persons_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personsToPersonsIdSeq")
    @Column(name = "id")
    private int id;
    @Column(name = "par_id")
    private int parId;
    @Column(name = "par_id_crush")
    private int parIdCrush;
    @Column(name = "likes")
    private boolean likes;
}
