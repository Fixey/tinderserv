package tinder.tindesrv.mapping;

import org.mapstruct.Mapper;
import tinder.tindesrv.dto.PersonCrushDto;
import tinder.tindesrv.entity.PersonCrush;


@Mapper(componentModel = "spring")
public interface PersonCrushMapper {
    PersonCrushDto toDto(PersonCrush model);

    PersonCrush fromDto(PersonCrushDto model);
}

