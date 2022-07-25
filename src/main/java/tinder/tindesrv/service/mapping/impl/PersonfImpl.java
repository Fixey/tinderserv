//package tinder.tindesrv.service.mapping.impl;
//
//import org.springframework.stereotype.Component;
//import tinder.tindesrv.entity.Person;
//import tinder.tindesrv.service.dto.PersonDto;
//import tinder.tindesrv.service.mapping.PersonMapper;
//
//import javax.annotation.processing.Generated;
//
//@Generated(
//        value = "org.mapstruct.ap.MappingProcessor",
//        date = "2022-07-21T14:07:27+0300",
//        comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
//)
//@Component
//public class PersonfImpl implements PersonMapper {
//
//    @Override
//    public PersonDto toDto(Person model) {
//        if (model == null) {
//            return null;
//        }
//
//        PersonDto.PersonDtoBuilder exchangeRateDto = PersonDto.builder();
//
//        exchangeRateDto.id(model.getId());
//        exchangeRateDto.birthday(model.getBirthday());
//        exchangeRateDto.crush(model.getCrush());
//        exchangeRateDto.description(model.getDescription());
//        exchangeRateDto.fullName(model.getFullName());
//        exchangeRateDto.gender(model.getGender());
//
//        return exchangeRateDto.build();
//    }
//}