package tinder.tindesrv.service.mapping;

import org.mapstruct.Mapper;
import tinder.tindesrv.entity.Person;
import tinder.tindesrv.service.dto.PersonDto;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDto toDto(Person model);
}
