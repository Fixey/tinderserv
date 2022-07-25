package tinder.tindesrv.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
public class Person {
    @Id
    private Long id;
    private String fullName;
    private LocalDate birthday;
    private String crush;
    private String gender;
    private String description;
}