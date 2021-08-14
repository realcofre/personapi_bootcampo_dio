package br.com.aracatidigital.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aracatidigital.personapi.dto.mapper.PersonMapper;
import br.com.aracatidigital.personapi.dto.request.PersonDTO;
import br.com.aracatidigital.personapi.dto.response.MessageResponseDTO;
import br.com.aracatidigital.personapi.entity.Person;
import br.com.aracatidigital.personapi.exception.PersonNotFoundException;
import br.com.aracatidigital.personapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService{

    
	private final PersonRepository personRepository;  
    
    
    private final PersonMapper personMapper;

    public MessageResponseDTO create(PersonDTO personDTO) {
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);       

        return createMessageResponse("Person successfully created with ID ", savedPerson.getId());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }


    public List<PersonDTO> listAll() {
        List<Person> people = personRepository.findAll();
        return people.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MessageResponseDTO update(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person updatedPerson = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(updatedPerson);

        MessageResponseDTO messageResponse = createMessageResponse("Person successfully updated with ID ", savedPerson.getId());

        return messageResponse;
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        personRepository.deleteById(id);
    }

    private MessageResponseDTO createMessageResponse(String s, Long id2) {
        return MessageResponseDTO.builder()
                .message(s + id2)
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
	}
	
}