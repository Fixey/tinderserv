package tinder.tindesrv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinder.tindesrv.enums.CrushTypeEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Long id;
    private String fullName;
    private LocalDate birthdate;
    @Enumerated(EnumType.STRING)
    private CrushTypeEnum crush;
    @Enumerated(EnumType.STRING)
    private CrushTypeEnum gender;
    private String description;
}
