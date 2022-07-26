package tinder.tindesrv.mapping;

import org.mapstruct.Mapper;
import tinder.tindesrv.dto.PersonDto;
import tinder.tindesrv.entity.Person;

@Mapper
public interface PersonMapper {
    PersonDto toDto(Person model);

    Person toEntity(PersonDto model);
}
