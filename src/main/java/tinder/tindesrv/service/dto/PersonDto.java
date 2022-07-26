package tinder.tindesrv.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Long id;
    private String fullName;
    private LocalDate birthdate;
    private String crush;
    private String gender;
    private String description;
}
