package tinder.tindesrv.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinder.tindesrv.enums.CrushTypeEnum;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
public class Person {
    @Id
    private Long id;
    private String fullName;
    private LocalDate birthdate;
    @Enumerated(EnumType.STRING)
    private CrushTypeEnum crush;
    @Enumerated(EnumType.STRING)
    private CrushTypeEnum gender;
    private String description;
}