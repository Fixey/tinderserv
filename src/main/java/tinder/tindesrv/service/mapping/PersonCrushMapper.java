package tinder.tindesrv.service.mapping;

import org.mapstruct.Mapper;
import tinder.tindesrv.entity.PersonCrush;
import tinder.tindesrv.service.dto.PersonCrushDto;


@Mapper(componentModel = "spring")
public interface PersonCrushMapper {
    PersonCrushDto toDto(PersonCrush model);
}

