package tinder.tindesrv.service.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonCrushDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long crushId;

    public PersonCrushDto(Long userId, Long crushId) {
        this.userId = userId;
        this.crushId = crushId;
    }
}
