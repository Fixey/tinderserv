package tinder.tindesrv.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonCrushDto {
    private Long id;
    private Long userId;
    private Long crushId;

    public PersonCrushDto(Long userId, Long crushId) {
        this.userId = userId;
        this.crushId = crushId;
    }
}
