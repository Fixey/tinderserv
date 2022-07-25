package tinder.tindesrv.service.mapping;

import org.mapstruct.Mapper;
import tinder.tindesrv.entity.PersToPers;
import tinder.tindesrv.service.dto.PersToPersDto;


@Mapper(componentModel = "spring")
public interface PersToPersMapper {
    PersToPersDto toDto(PersToPers model);
}

