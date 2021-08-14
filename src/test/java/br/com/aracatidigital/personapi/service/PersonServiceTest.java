package br.com.aracatidigital.personapi.service;

import static br.com.aracatidigital.personapi.utils.PersonUtils.createFakeDTO;
import static br.com.aracatidigital.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.aracatidigital.personapi.dto.mapper.PersonMapper;
import br.com.aracatidigital.personapi.dto.request.PersonDTO;
import br.com.aracatidigital.personapi.dto.response.MessageResponseDTO;
import br.com.aracatidigital.personapi.entity.Person;
import br.com.aracatidigital.personapi.repository.PersonRepository;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;
	
	@Mock
    private PersonMapper personMapper;
	
	@InjectMocks
	private PersonService personService;
	
	@Test
    void testGivenPersonDTOThenReturnSuccessSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personMapper.toModel(personDTO)).thenReturn(expectedSavedPerson);
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedSuccessMessage(expectedSavedPerson.getId());
        MessageResponseDTO successMessage = personService.create(personDTO);

        assertEquals(expectedSuccessMessage, successMessage);
    }

    private MessageResponseDTO createExpectedSuccessMessage(Long savedPersonId) {
        return MessageResponseDTO.builder()
                .message("Person successfully created with ID " + savedPersonId)
                .build();
    }
}
