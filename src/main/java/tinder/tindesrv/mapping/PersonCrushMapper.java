package tinder.tindesrv.mapping;

import org.mapstruct.Mapper;
import tinder.tindesrv.dto.PersonCrushDto;
import tinder.tindesrv.entity.PersonCrush;

@Mapper
public interface PersonCrushMapper {
    PersonCrushDto toDto(PersonCrush model);

    PersonCrush toEntity(PersonCrushDto model);
}

