package br.com.aracatidigital.personapi.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

import br.com.aracatidigital.personapi.dto.request.PersonDTO;
import br.com.aracatidigital.personapi.entity.Person;

@Mapper
@MapperConfig(componentModel = "spring")
public interface PersonMapper{
	
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO dto);

    PersonDTO toDTO(Person dto);
}
