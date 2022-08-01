package tinder.tindesrv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinder.tindesrv.enums.CrushTypeEnum;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Long id;
    private String fullName;
    private LocalDate birthdate;
    private CrushTypeEnum crush;
    private CrushTypeEnum gender;
    private String description;
}
