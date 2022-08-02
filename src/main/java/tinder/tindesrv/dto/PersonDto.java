package tinder.tindesrv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinder.tindesrv.enums.CrushTypeEnum;
import tinder.tindesrv.enums.GenderTypeEnum;

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
    private GenderTypeEnum gender;
    private String description;
}
