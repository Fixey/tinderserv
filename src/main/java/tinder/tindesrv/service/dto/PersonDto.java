package tinder.tindesrv.service.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private int id;
    private String fullName;
    private LocalDate birthday;
    private String crush;
    private String gender;
    private String description;
}
