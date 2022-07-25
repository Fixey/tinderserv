package tinder.tindesrv.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name = "id")
    private int id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "crush")
    private String crush;
    @Column(name = "gender")
    private String gender;
    @Column(name = "description")
    private String description;
}